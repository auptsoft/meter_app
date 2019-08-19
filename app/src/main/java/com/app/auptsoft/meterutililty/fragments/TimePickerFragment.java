package com.app.auptsoft.meterutililty.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private OnTimeSetListener onTimeSetListener;
    private int hour;
    private int minute;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hour = getArguments().getInt("hour");
        minute = getArguments().getInt("minute");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if (onTimeSetListener != null)onTimeSetListener.onTimeSet(timePicker, i, i1);
    }

    public OnTimeSetListener getOnTimeSetListener() {
        return onTimeSetListener;
    }

    public void setOnTimeSetListener(OnTimeSetListener onTimeSetListener) {
        this.onTimeSetListener = onTimeSetListener;
    }

    public static TimePickerFragment newInstance(int hour, int minute) {
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hour);
        bundle.putInt("minute", minute);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(bundle);

        return timePickerFragment;
    }

    public interface OnTimeSetListener{
        void onTimeSet(TimePicker timePicker, int hour, int min);
    }

}
