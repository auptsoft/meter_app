package com.app.auptsoft.meterutililty.presenters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.BR;
import com.app.auptsoft.meterutililty.fragments.TimePickerFragment;
import com.app.auptsoft.meterutililty.model.Load;
import com.app.auptsoft.meterutililty.services.Gateway;

import java.util.Calendar;
import java.util.GregorianCalendar;

import es.dmoral.toasty.Toasty;

public class LoadFragmentPresenter extends BaseObservable {
    private Load load;

    private Activity activity;
    private int loadIndex;

    private boolean loading;

    //must be set to false when done
    private boolean debug = true;

    public LoadFragmentPresenter(Load load, Activity activity, int loadIndex) {
        this.activity = activity;
        this.load = load;
        this.load.setUseTiming(true);
        this. loadIndex = loadIndex;
    }

    @Bindable
    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
        notifyChange();
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public String getStartTime() {

        int hour = load.getStartHour()==0 ? 12 : (load.getStartHour()<=12 ? load.getStartHour() : load.getStartHour()-12);
        String am_pm = load.getStartHour()<=11? "am": "pm";

        if (load.isUseTiming())
            return hour+":"+load.getStartMinute()+am_pm;
        else return  "-- --";
    }

    @Bindable
    public String getEndTime() {
        int hour = load.getEndHour()==0 ? 12 : (load.getEndHour()<=12 ? load.getEndHour() : load.getEndHour()-12);
        String am_pm = load.getEndHour()<=11? "am": "pm";

        if (load.isUseTiming())
            return hour+":"+load.getEndMinute()+am_pm;
        else return  "-- --";
    }

    public void changeStartTime() {
        final TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(getLoad().getStartHour(), getLoad().getStartMinute());
        timePickerFragment.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                load.setStartHour(hour);
                load.setStartMinute(min);
                //setLoad(load);
                //Load.update(activity, load);
                timePickerFragment.dismiss();

                setLoading(true);

                Gateway.setLoadOnTime(load, new Gateway.OnOperationCompleteListener() {
                    @Override
                    public void onOperationComplete(String url, String msg) {
                        setLoading(false);
                        if (debug) showToast(url);
                        Toasty.success(activity, "Done").show();
                        setLoad(load);
                        Load.update(activity, load);
                    }

                    @Override
                    public void onError(String url, String msg) {
                        setLoading(false);
                        Toasty.error(activity, msg);
                    }
                });
            }
        });
        timePickerFragment.show(((AppCompatActivity)activity).getSupportFragmentManager(), "change Start time");
    }

    public void changeStopTime() {
        final TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(getLoad().getEndHour(), getLoad().getEndMinute());
        timePickerFragment.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                load.setEndHour(hour);
                load.setEndMinute(min);
                load.setUseTiming(true);
                //load.setOn(true);
                //setLoad(load);
                //Load.update(activity, load);
                timePickerFragment.dismiss();

                setLoading(true);

                Gateway.setLoadOffTime(load, new Gateway.OnOperationCompleteListener() {
                    @Override
                    public void onOperationComplete(String url, String msg) {
                        setLoading(false);
                        if (debug) showToast(url);
                        Toasty.success(activity, "Done").show();
                        setLoad(load);
                        Load.update(activity, load);
                    }

                    @Override
                    public void onError(String url, String msg) {
                        setLoading(false);
                        Toasty.error(activity, msg);
                    }
                });
            }
        });
        timePickerFragment.show(((AppCompatActivity)activity).getSupportFragmentManager(), "change End time");
    }

    public void useTiming() {
//        load.setUseTiming(true);
//        setLoad(load);

        setLoading(true);
        load.setUseTiming(true);
        AppState.allLoads.set(loadIndex, load);

        Gateway.setTiming(AppState.allLoads, new Gateway.OnOperationCompleteListener() {
            @Override
            public void onOperationComplete(String url, String msg) {
                setLoading(false);
                if (debug) showToast(url);
                Toasty.success(activity, "Done").show();
                setLoad(load);
                Load.update(activity, load);
            }

            @Override
            public void onError(String url, String msg) {
                setLoading(false);
                Toasty.error(activity, msg);
            }
        });
    }

    public void overideTiming() {
//        load.setUseTiming(false);
//        setLoad(load);

        setLoading(true);
        load.setUseTiming(false);
        AppState.allLoads.set(loadIndex, load);

        Gateway.setTiming(AppState.allLoads, new Gateway.OnOperationCompleteListener() {
            @Override
            public void onOperationComplete(String url, String msg) {
                setLoading(false);
                if (debug) showToast(url);
                Toasty.success(activity, "Done").show();
                setLoad(load);
                Load.update(activity, load);
            }

            @Override
            public void onError(String url, String msg) {
                setLoading(false);
                Toasty.error(activity, msg).show();
            }
        });
    }

    public void setOn() {
        //load.setOn(true);
        load.setUseTiming(false);
        //setLoad(load);

        Gateway.turnOnLoad(load, new Gateway.OnOperationCompleteListener() {
            @Override
            public void onOperationComplete(String url, String msg) {
                setLoading(false);
                if (debug) showToast(url);
                Toasty.success(activity, "Done").show();
                load.setOn(true);
                setLoad(load);
                Load.update(activity, load);
            }

            @Override
            public void onError(String url, String msg) {
                setLoading(false);
                Toasty.error(activity, msg);
            }
        });
    }

    public void setOff() {
        //load.setOn(false);
        load.setUseTiming(false);
        //setLoad(load);

        Gateway.turnOffLoad(load, new Gateway.OnOperationCompleteListener() {
            @Override
            public void onOperationComplete(String url, String msg) {
                setLoading(false);
                if (debug) showToast(url);
                Toasty.success(activity, "Done").show();
                load.setOn(false);
                setLoad(load);
                Load.update(activity, load);
            }

            @Override
            public void onError(String url, String msg) {
                setLoading(false);
                Toasty.error(activity, msg);
            }
        });
    }

    public boolean onVisible() {
        return !load.isOn() && !load.isUseTiming();
    }
    public boolean offVisible() {
        return load.isOn() && !load.isUseTiming();
    }
    @Bindable
    public String getDurationStatus() {
        int durationHours = load.getMinuteDifference()/60;
        int durationMinute = load.getMinuteDifference()%60;

        if (load.isUseTiming()) {
            return "On time: "+durationHours+" hours "+durationMinute+" minutes in a day";
        } else {
            if (load.isOn()) {
                return "ON: timing not applied";
            } else {
                return  "OFF: timing not applied";
            }
        }
    }

    public void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
