package com.app.auptsoft.meterutililty.presenters;

import android.databinding.BaseObservable;

import com.app.auptsoft.meterutililty.model.Meter;
import com.app.auptsoft.meterutililty.model.NMeterStatus;


public class NMeterStatusPresenter extends BaseObservable {
    //private Meter meter;
    private NMeterStatus nMeterStatus;

    public String getStatusString() {
        if (nMeterStatus.getFaultStatus() == 1) {
            return "RUNNING! Your meter is working fine";
        } else if (nMeterStatus.getFaultStatus() == 2) {
            return "SHUTDOWN! Your meter has been tampered with. Contact admin";
        } else if (nMeterStatus.getFaultStatus() == 3) {
            return "INVALID STATE! Your meter is invalid state. Contact base Station";
        } else if (nMeterStatus.getFaultStatus() == 4) {
            return "SHUTDOWN! Meter overloaded. Kindly reduce your load to start meter";
        } else if (nMeterStatus.getFaultStatus() == 5) {
            return "SHUTDOWN! Meter shutdown by Admin";
        } else {
            return "UNKNOWN STATE! Your meter is unknown state. Contact base Station";
        }
    }

    public boolean getRedPhaseActive() {
        return nMeterStatus.getRedPhaseStatus() == 1;
    }

    public boolean getYellowPhaseActive() {
        return nMeterStatus.getYellowPhaseStatus() == 1;
    }

    public boolean getBluePhaseActive() {
        return nMeterStatus.getBluePhaseStatus() == 1;
    }

    public boolean getRedPhaseCurrent() {
        return nMeterStatus.getSelectedPhase() == 1;
    }

    public boolean getYellowPhaseCurrent() {
        return nMeterStatus.getSelectedPhase() == 2;
    }

    public boolean getBluePhaseCurrent() {
        return nMeterStatus.getSelectedPhase() == 3;
    }

    public NMeterStatus getNMeterStatus() {
        return nMeterStatus;
    }

    public void setnMeterStatus(NMeterStatus nMeterStatus) {
        this.nMeterStatus = nMeterStatus;
    }
}
