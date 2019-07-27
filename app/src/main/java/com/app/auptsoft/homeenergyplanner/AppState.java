package com.app.auptsoft.homeenergyplanner;

import android.content.SharedPreferences;

import com.app.auptsoft.homeenergyplanner.services.BluetoothManager;
import com.app.auptsoft.homeenergyplanner.model.Load;

import java.util.ArrayList;

/**
 * Created by Administrator on 12/8/2018.
 */

public class AppState {
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
}
