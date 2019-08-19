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
import com.app.auptsoft.meterutililty.databinding.FragmentNMeterStatusBinding;
import com.app.auptsoft.meterutililty.presenters.MeterStatusPresenter;
import com.app.auptsoft.meterutililty.presenters.NMeterStatusPresenter;


public class NMeterStatusFragment extends DialogFragment {
    FragmentNMeterStatusBinding binding;
    NMeterStatusPresenter presenter;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_n_meter_status, container, false);

        presenter = new NMeterStatusPresenter();

            presenter.setnMeterStatus(AppState.nMeterStatus);
            binding.setPresenter(presenter);


        return binding.getRoot();
    }
}
