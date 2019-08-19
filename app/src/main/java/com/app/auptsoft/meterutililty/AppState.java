package com.app.auptsoft.meterutililty;

import android.content.SharedPreferences;

import com.app.auptsoft.meterutililty.model.Meter;
import com.app.auptsoft.meterutililty.model.MeterStatus;
import com.app.auptsoft.meterutililty.model.NMeterStatus;
import com.app.auptsoft.meterutililty.services.BluetoothManager;
import com.app.auptsoft.meterutililty.model.Load;

import java.util.ArrayList;

/**
 * Created by Administrator on 12/8/2018.
 */

public class AppState {
    public static boolean debug = true;

    public static final String METER_PREF = "meter_pref";
    public static final String IS_FIRST_TIME_PREF = "is_first_time_pref";
    public static ArrayList<Load> allLoads;
    public static SharedPreferences preferences;

    public static int appLaunchState;

    public static String BLUETOOTH_ADDRESS_PROPERTY = "BLUETOOTH_ADDRESS_PROPERTY";

    public static BluetoothManager bluetoothManager;

    public static String username;
    public static final String USERNAME_PREF = "username_pref";

    public static String password;
    public static final String PASSWORD_PREF = "password_pref";

    public static boolean alwaysRequestForPassword;
    public static final String ALWAYS_REQUEST_FOR_PASSWORD_PREF = "always_request_for_password_pref";

    public static String baseStationHost;
    public static final String BASE_STATION_HOST_PREF = "base_station_host_pref";

    public static boolean authenticated = false;

    public static String planerIp = "http://192.168.43.32:2000/";
    public static String meterIp = "http://192.168.43.32:2000/";

    public static final String PLANER_IP_PREF = "planer_ip";
    public static final String METER_IP_PREF = "meter_ip";

    public static Meter meter;
    public static MeterStatus meterStatus;
    public static boolean isFirstTime = true;
    public static NMeterStatus nMeterStatus;
}
