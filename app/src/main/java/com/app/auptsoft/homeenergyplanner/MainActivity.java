package com.app.auptsoft.homeenergyplanner;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.*;
import android.widget.Toast;

import com.app.auptsoft.homeenergyplanner.fragments.MeterFragment;
import com.app.auptsoft.homeenergyplanner.fragments.TimeLoadFragment;
import com.app.auptsoft.homeenergyplanner.services.BluetoothConnectionInterface;
import com.app.auptsoft.homeenergyplanner.services.BluetoothManager;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppState.preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        AppState.alwaysRequestForPassword = AppState.preferences.getBoolean(AppState.ALWAYS_REQUEST_FOR_PASSWORD_PREF, true);
        AppState.username = AppState.preferences.getString(AppState.USERNAME_PREF, "user");
        AppState.password = AppState.preferences.getString(AppState.PASSWORD_PREF, "pass");
        AppState.baseStationHost = AppState.preferences.getString(AppState.BASE_STATION_HOST_PREF, "http://192.168.43.32");

        if(AppState.alwaysRequestForPassword && !AppState.authenticated) {
            Intent intent = new Intent(this, AuthencateActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);



        AppState.bluetoothManager = new BluetoothManager(this, true);

        navigationView = (BottomNavigationView)findViewById(R.id.main_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new TimeLoadFragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.time_load_nav_menu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new TimeLoadFragment()).commit();
                        break;

                    case R.id.meter_nav_menu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MeterFragment()).commit();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem connectMenu = menu.add(1, 1, 1, "Connect");
        connectMenu.setIcon(R.drawable.ic_bluetooth_connected_black_24dp);
        connectMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem showDevices = menu.add(2, 2, 2, "Show Devices");

        MenuItem editLoadMenu = menu.add(3, 3, 3, "Edit Loads");

        MenuItem settingsMenu = menu.add(4, 4, 4, "Settings");

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
        }
        return super.onOptionsItemSelected(item);
    }

}