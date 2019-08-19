package com.app.auptsoft.meterutililty.view_models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.BR;
import com.app.auptsoft.meterutililty.SettingsActivity;

/**
 * Created by Andrew on 21.3.19.
 */

public class SettingsViewModel extends BaseObservable {
    private boolean alwaysRequirePassword;
    private String username;
    private String password;

    private String oldUsername;
    private String oldPassword;

    private Context context;

    private String baseStationHost;

    public SettingsViewModel(Context context, boolean alwaysRequirePassword, String username, String password) {
        this.oldUsername = "";
        this.oldPassword = "";
        this.context = context;
        this.alwaysRequirePassword = alwaysRequirePassword;
        this.username = username;
        this.password = password;

        baseStationHost = AppState.baseStationHost;
    }

    public void save() {
        if(!getOldUsername().equals(AppState.username)){
            Toast.makeText(context, "Incorrect old username", Toast.LENGTH_LONG).show();
            return;
        }
        if (!getOldPassword().equals(AppState.password)) {
            Toast.makeText(context, "Incorrect old password", Toast.LENGTH_LONG).show();
            return;
        }
        AppState.preferences.edit()
                .putBoolean(AppState.ALWAYS_REQUEST_FOR_PASSWORD_PREF, isAlwaysRequirePassword())
                .putString(AppState.USERNAME_PREF, getUsername())
                .putString(AppState.PASSWORD_PREF, getPassword())
                //.putString(AppState.BASE_STATION_HOST_PREF, getBaseStationHost())
                .apply();

        Toast.makeText(context, "New values saved successfully", Toast.LENGTH_LONG).show();
    }

    public void saveBaseStationHost() {
        AlertDialog.Builder builder = new AlertDialog.Builder((SettingsActivity)context);
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure you want to change this value? It should only be change by" +
                " an officer from the base station.");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppState.preferences.edit()
                        //.putBoolean(AppState.ALWAYS_REQUEST_FOR_PASSWORD_PREF, isAlwaysRequirePassword())
                        //.putString(AppState.USERNAME_PREF, getUsername())
                        //.putString(AppState.PASSWORD_PREF, getPassword())
                        .putString(AppState.BASE_STATION_HOST_PREF, getBaseStationHost())
                        .apply();

                Toast.makeText(context, "New value saved successfully", Toast.LENGTH_LONG).show();
            }
        }).show();

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @Bindable
    public boolean isAlwaysRequirePassword() {
        return alwaysRequirePassword;
    }

    public void setAlwaysRequirePassword(boolean alwaysRequirePassword) {
        this.alwaysRequirePassword = alwaysRequirePassword;
        notifyPropertyChanged(BR.alwaysRequirePassword);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Bindable
    public String getBaseStationHost() {
        return baseStationHost;
    }

    public void setBaseStationHost(String baseStationHost) {
        this.baseStationHost = baseStationHost;
        notifyPropertyChanged(BR.baseStationHost);
    }

    @Bindable
    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
        notifyPropertyChanged(BR.oldUsername);
    }

    @Bindable
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        notifyPropertyChanged(BR.oldPassword);
    }
}
