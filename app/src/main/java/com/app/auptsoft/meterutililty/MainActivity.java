package com.app.auptsoft.meterutililty;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.*;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.fragments.MeterFragment;
import com.app.auptsoft.meterutililty.fragments.TimeLoadFragment;
import com.app.auptsoft.meterutililty.fragments.TimePickerFragment;
import com.app.auptsoft.meterutililty.services.BluetoothConnectionInterface;
import com.app.auptsoft.meterutililty.services.BluetoothManager;
import com.app.auptsoft.meterutililty.services.Gateway;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
 BottomNavigationView navigationView;

    boolean debug = true;

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppState.preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        AppState.alwaysRequestForPassword = AppState.preferences.getBoolean(AppState.ALWAYS_REQUEST_FOR_PASSWORD_PREF, true);
        AppState.username = AppState.preferences.getString(AppState.USERNAME_PREF, "user");
        AppState.password = AppState.preferences.getString(AppState.PASSWORD_PREF, "pass");
        AppState.baseStationHost = AppState.preferences.getString(AppState.BASE_STATION_HOST_PREF, "http://192.168.43.32");

        AppState.planerIp = AppState.preferences.getString(AppState.PLANER_IP_PREF, "192.168.43.32");
        AppState.meterIp = AppState.preferences.getString(AppState.METER_IP_PREF, "192.168.43.32");

        AppState.isFirstTime = AppState.preferences.getBoolean(AppState.IS_FIRST_TIME_PREF, true);

        if(AppState.alwaysRequestForPassword && !AppState.authenticated) {
            Intent intent = new Intent(this, AuthencateActivity.class);
            startActivity(intent);
            finish();
        }

        if (AppState.isFirstTime) {
            Intent intent = new Intent(this, MeterEditActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        setSupportActionBar(toolbar);


        AppState.bluetoothManager = new BluetoothManager(this, false);

        navigationView = (BottomNavigationView) findViewById(R.id.main_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new TimeLoadFragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.time_load_nav_menu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new TimeLoadFragment()).commit();
                        break;

                    case R.id.meter_nav_menu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MeterFragment()).commit();
                        break;

                    case R.id.set_planer_time:
                        TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(0, 0);
                        timePickerFragment.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                                Snackbar.make(navigationView, "Setting Energy Planner Time", Snackbar.LENGTH_INDEFINITE).show();
                                Gateway.setPlanerTime(hour, min, 0, new Gateway.OnOperationCompleteListener() {
                                    @Override
                                    public void onOperationComplete(String url, String msg) {
                                        Snackbar.make(navigationView, "Completed Successfully", Snackbar.LENGTH_LONG).show();
                                        Toasty.success(getBaseContext(), "Done");

                                        if (debug) {
                                            Toast.makeText(getBaseContext(), url, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(String url, String msg) {
                                        Snackbar.make(navigationView, "Error occurred", Snackbar.LENGTH_LONG).show();
                                        if (debug) {
                                            Toast.makeText(getBaseContext(), url, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });

                        timePickerFragment.show(getSupportFragmentManager(), "Set planer time");
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem connectMenu = menu.add(1, 1, 1, "Connect");
//        connectMenu.setIcon(R.drawable.ic_bluetooth_connected_black_24dp);
//        connectMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

//        MenuItem showDevices = menu.add(2, 2, 2, "Show Devices");

        MenuItem editLoadMenu = menu.add(3, 3, 3, "Edit Loads");

        MenuItem settingsMenu = menu.add(4, 4, 4, "Settings");

        MenuItem setIPs = menu.add(5, 5, 5, "IP settings");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Snackbar.make(navigationView, "Connecting...", Snackbar.LENGTH_INDEFINITE).show();
                String address = AppState.preferences.getString(AppState.BLUETOOTH_ADDRESS_PROPERTY, "00:18:E5:04:B3:E5");
                AppState.bluetoothManager.connectToDeviceAsync(address, new BluetoothConnectionInterface() {
                    @Override
                    public void onConnected(BluetoothSocket bluetoothSocket) {
                        AppState.bluetoothManager.connectionState = true;
                        Toast.makeText(getBaseContext(), "Connected successfully", Toast.LENGTH_LONG).show();
                        Snackbar.make(navigationView, "Connected", Snackbar.LENGTH_LONG).show();
                        getSupportActionBar().setSubtitle("connected");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(getBaseContext(), "Could not connect", Toast.LENGTH_LONG).show();
                    }
                });
                break;

            case 2:
                new DeviceListFragment().show(getSupportFragmentManager(), "Show device");
                break;
            case 3:
                startActivity(new Intent(getBaseContext(), LoadEditActivity.class));
                finish();
                break;

            case 4:
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                //finish();
                break;

            case 5:
                Intent intent = new Intent(getBaseContext(), MeterEditActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}