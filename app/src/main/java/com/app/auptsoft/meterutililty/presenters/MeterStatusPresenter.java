package com.app.auptsoft.meterutililty.presenters;

import android.databinding.BaseObservable;

import com.app.auptsoft.meterutililty.model.Meter;
import com.app.auptsoft.meterutililty.model.MeterStatus;


public class MeterStatusPresenter extends BaseObservable {
    private Meter meter;
    private MeterStatus meterStatus;

    public String getStatusString() {
        if (meterStatus.getReason().equals("1")) {
            return "SHUTDOWN! Your meter has been tampered with. Contact admin";
        } else if (meterStatus.getReason().equals("2")) {
            return "SHUTDOWN! No unit available";
        } else if (meterStatus.getReason().equals("3")) {
            return "SHUTDOWN! Current phase down. Change to any available phase";
        } else if (meterStatus.getReason().equals("4")) {
            return "SHUTDOWN! Meter overloaded. Kindly reduce your load to start meter";
        } else if (meterStatus.getReason().equals("5")) {
            return "SHUTDOWN! Meter shutdown by Admin";
        } else {
            return "RUNNING! Your meter is working currently";
        }
    }

    public boolean getRedPhaseActive() {
        return meterStatus.getRedPhaseActive().equals("1");
    }

    public boolean getYellowPhaseActive() {
        return meterStatus.getYellowPhaseActive().equals("1");
    }

    public boolean getBluePhaseActive() {
        return meterStatus.getBluePhaseActive().equals("1");
    }

    public boolean getRedPhaseCurrent() {
        return meterStatus.getCurrentPhase().equals("1");
    }

    public boolean getYellowPhaseCurrent() {
        return meterStatus.getCurrentPhase().equals("2");
    }

    public boolean getBluePhaseCurrent() {
        return meterStatus.getCurrentPhase().equals("3");
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public MeterStatus getMeterStatus() {
        return meterStatus;
    }

    public void setMeterStatus(MeterStatus meterStatus) {
        this.meterStatus = meterStatus;
    }
}
