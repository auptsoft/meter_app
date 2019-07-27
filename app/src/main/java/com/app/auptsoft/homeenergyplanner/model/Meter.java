package com.app.auptsoft.homeenergyplanner.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.app.auptsoft.homeenergyplanner.BR;

/**
 * Created by Andrew on 19.3.19.
 */

public class Meter extends BaseObservable{
    private Context context;
    private String meterNumber;
    private String designation;
    private String capacity;    //value in watts
    private String availableAmount;
    private String currentPowerConsumed; //value in watts
    private String rechargePin;


    public Meter(Context context, String availableAmount, String currentPowerConsumed, String rechargePin) {
        this.context = context;
        this.availableAmount = availableAmount;
        this.currentPowerConsumed = currentPowerConsumed;
        this.rechargePin = rechargePin;
    }

    public void recharge(String rechargePin) {
        Toast.makeText(context, rechargePin, Toast.LENGTH_LONG).show();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Bindable
    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
        notifyPropertyChanged(BR.availableAmount);
    }

    @Bindable
    public String getCurrentPowerConsumed() {
        return currentPowerConsumed;
    }

    public void setCurrentPowerConsumed(String currentPowerConsumed) {
        this.currentPowerConsumed = currentPowerConsumed;
        notifyPropertyChanged(BR.currentPowerConsumed);
    }

    @Bindable
    public String getRechargePin() {
        return rechargePin;
    }

    public void setRechargePin(String rechargePin) {
        this.rechargePin = rechargePin;
        notifyPropertyChanged(BR.rechargePin);
    }
}
