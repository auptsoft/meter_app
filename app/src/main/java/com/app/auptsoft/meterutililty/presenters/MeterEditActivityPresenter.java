package com.app.auptsoft.meterutililty.presenters;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.app.auptsoft.meterutililty.BR;
import com.app.auptsoft.meterutililty.model.Meter;

public class MeterEditActivityPresenter extends BaseObservable {
    private Meter meter;

    private String planerIp;
    private String meterIp;

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    @Bindable
    public String getPlanerIp() {
        return planerIp;
    }


    public void setPlanerIp(String planerIp) {
        this.planerIp = planerIp;
        notifyChange();
    }

    @Bindable
    public String getMeterIp() {
        return meterIp;
    }

    public void setMeterIp(String meterIp) {
        this.meterIp = meterIp;
        notifyChange();
    }
}
