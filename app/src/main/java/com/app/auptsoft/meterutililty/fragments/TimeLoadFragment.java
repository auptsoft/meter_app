package com.app.auptsoft.meterutililty.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.R;
import com.app.auptsoft.meterutililty.model.Load;
import com.app.auptsoft.meterutililty.adapters.LoadPagerAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Andrew on 19.3.19.
 */

public class TimeLoadFragment extends Fragment implements View.OnClickListener{
    ViewPager viewPager;
    android.support.design.widget.TabLayout tabLayout;
    FloatingActionButton setLoadTimeAction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppState.preferences.getInt("launchState", 0) == 0) {
            initializeData();
            AppState.preferences.edit().putInt("launchState", 1).apply();
        }

        AppState.allLoads = Load.getAll((AppCompatActivity)getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_load, container, false);

        setLoadTimeAction = (FloatingActionButton)view.findViewById(R.id.set_load_time_action_Id);
        setLoadTimeAction.setOnClickListener(this);

        viewPager = (ViewPager)view.findViewById(R.id.main_view_pager_id);
        viewPager.setAdapter(new LoadPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager()));

        tabLayout = (android.support.design.widget.TabLayout)view.findViewById(R.id.tab_id);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onClick(View view) {
        /*for (Load load : AppState.allLoads) {
            Load.update(getBaseContext(), load);
        } */  //Debug

        //Toast.makeText(getApplication(), getSendString(), Toast.LENGTH_LONG).show();

        if (AppState.bluetoothManager.connectionState) {

            String outString = getSendString();

            AppState.bluetoothManager.write(outString);

            Toast.makeText(getContext(), outString, Toast.LENGTH_LONG).show();   //debug

            if (AppState.bluetoothManager.writeState) {
                Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_LONG).show();
                for (Load load : AppState.allLoads) {
                    Load.update(getContext(), load);
                }
            } else {
                Toast.makeText(getContext(), "Could not send. Try connecting again", Toast.LENGTH_LONG).show();
                //getSupportActionBar().setSubtitle("not connected");
            }
        } else  {
            Toast.makeText(getContext(), "not connected", Toast.LENGTH_LONG).show();
        }
    }

    private String getSendString() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int hour = gregorianCalendar.get(Calendar.HOUR);
        int minute = gregorianCalendar.get(Calendar.MINUTE);
        int seconds = gregorianCalendar.get(Calendar.SECOND);

        int length = AppState.allLoads.size();

        String outString = ""+(char)seconds + (char)minute +  (char)hour +"00000" + Load.getFormatedTime(AppState.allLoads);

        return outString;
    }

    private void initializeData() {
        Load load1 = new Load(0, "Load1", 0, 1, 0, 0, "");
        Load load2 = new Load(0, "Load2", 11, 20, 0, 0, "");
        Load load3 = new Load(0, "Load3", 22, 40, 0, 0, "");
        Load load4 = new Load(0, "Load4", 20, 5, 0, 0, "");
        Load load5 = new Load(0, "Load5", 3, 20, 0, 0, "");
        Load load6 = new Load(0, "Load6", 2, 30, 0, 0, "");

        Load.insert(getContext(), load1);
        Load.insert(getContext(), load2);
        Load.insert(getContext(), load3);
        Load.insert(getContext(), load4);
        Load.insert(getContext(), load5);

        Toast.makeText(getContext(), Load.insert(getContext(), load6)+"", Toast.LENGTH_LONG).show();
    }
}
