package com.app.auptsoft.homeenergyplanner.view_models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.app.auptsoft.homeenergyplanner.model.MeterModel;

/**
 * Created by Andrew on 21.3.19.
 */

public class MeterViewModel  extends BaseObservable{
    MeterModel meterModel;
    Context context;

    public MeterViewModel(MeterModel meterModel, Context context) {
        this.meterModel = meterModel;
        this.context = context;
    }

    @Bindable
    public MeterModel getMeterModel() {
        return meterModel;
    }

    public void setMeterModel(MeterModel meterModel) {
        this.meterModel = meterModel;
        notifyPropertyChanged(BR.userViewModel);
    }



    private void updateStatus() {

    }
}
