package com.app.auptsoft.meterutililty.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.R;
import com.app.auptsoft.meterutililty.databinding.FragmentMeterStatusBinding;
import com.app.auptsoft.meterutililty.presenters.MeterStatusPresenter;


public class MeterStatusFragment extends DialogFragment {
    FragmentMeterStatusBinding binding;
    MeterStatusPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meter_status, container, false);

        presenter = new MeterStatusPresenter();
        if (AppState.meter!=null && AppState.meterStatus != null) {
            presenter.setMeter(AppState.meter);
            presenter.setMeterStatus(AppState.meterStatus);
        }

        return binding.getRoot();
    }
}
