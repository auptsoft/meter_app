package com.app.auptsoft.homeenergyplanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.support.design.widget.FloatingActionButton;

import com.app.auptsoft.homeenergyplanner.AppState;
import com.app.auptsoft.homeenergyplanner.R;
import com.app.auptsoft.homeenergyplanner.model.Load;

/**
 * Created by Administrator on 12/8/2018.
 */

public class LoadFragment extends Fragment{
    int index;
    Load load;

    TextView loadName;
    TimePicker startTimePicker;
    TimePicker stopTimePicker;

    FloatingActionButton currentValuesButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("loadIndex");

        load = AppState.allLoads.get(index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.load_view, container, false);

        loadName = (TextView)view.findViewById(R.id.load_name_id);
        startTimePicker = (TimePicker)view.findViewById(R.id.start_time_picker_id);
        stopTimePicker = (TimePicker)view.findViewById(R.id.stop_time_picker_id);

        loadName.setText(AppState.allLoads.get(index).getName());

        startTimePicker.setHour(AppState.allLoads.get(index).getStartHour());
        startTimePicker.setMinute(AppState.allLoads.get(index).getStartMinute());

        stopTimePicker.setHour(AppState.allLoads.get(index).getEndHour());
        stopTimePicker.setMinute(AppState.allLoads.get(index).getEndMinute());
        //stopTimePicker.set

        startTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                AppState.allLoads.get(index).setStartHour(i);
                AppState.allLoads.get(index).setStartMinute(i1);
            }
        });

        stopTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                AppState.allLoads.get(index).setEndHour(i);
                AppState.allLoads.get(index).setEndMinute(i1);
            }
        });

        currentValuesButton = (FloatingActionButton)view.findViewById(R.id.current_values_id);
        currentValuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Load l = Load.get(getContext(), AppState.allLoads.get(index).getId());

                startTimePicker.setHour(l.getStartHour());
                startTimePicker.setMinute(l.getStartMinute());

                stopTimePicker.setHour(l.getEndHour());
                stopTimePicker.setMinute(l.getEndMinute());
            }
        });

        return view;
    }

    public static LoadFragment newInstance(int index){
        LoadFragment loadFragment = new LoadFragment();

        Bundle arg = new Bundle();
        arg.putInt("loadIndex", index);

        loadFragment.setArguments(arg);

        return loadFragment;
    }
}
