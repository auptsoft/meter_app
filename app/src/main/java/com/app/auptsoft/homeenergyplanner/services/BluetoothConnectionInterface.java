package com.app.auptsoft.homeenergyplanner.services;

import android.bluetooth.BluetoothSocket;

/**
 * Created by Andrew on 08/04/2018.
 */

public interface BluetoothConnectionInterface {
    public void onConnected(BluetoothSocket bluetoothSocket);
    public void onError(String errorMessage);
}
