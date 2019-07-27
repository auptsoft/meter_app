package com.app.auptsoft.homeenergyplanner.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.auptsoft.homeenergyplanner.AppState;
import com.app.auptsoft.homeenergyplanner.R;
import com.app.auptsoft.homeenergyplanner.databinding.FragmentMeterBinding;
import com.app.auptsoft.homeenergyplanner.model.Meter;
import com.app.auptsoft.homeenergyplanner.model.MeterModel;
import com.app.auptsoft.homeenergyplanner.presenters.MeterFragmentPresenter;
import com.app.auptsoft.homeenergyplanner.services.IOCommunication;
import com.app.auptsoft.homeenergyplanner.view_models.MeterViewModel;

/**
 * Created by Andrew on 19.3.19.
 */

public class MeterFragment extends Fragment {
    FragmentMeterBinding binding;
    MeterViewModel meterViewModel;
    MeterFragmentPresenter presenter;
    IOCommunication ioCommunication;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meter, container, false);
        View view = binding.getRoot();

        MeterModel meterModel = MeterModel.getSendable("000000000000000000000000000");
        meterViewModel = new MeterViewModel(meterModel, getContext());
        presenter = new MeterFragmentPresenter();

        binding.setMeterViewModel(meterViewModel);
        binding.setPresenter(presenter);

        getMeterUpdate();

        return view;
    }

    void getMeterUpdate() {
        if (AppState.bluetoothManager != null && AppState.bluetoothManager.connectionState) {
            ioCommunication = new IOCommunication(AppState.bluetoothManager.getInputStream(), AppState.bluetoothManager.getOutputStream());
            ioCommunication.listenForMessage(new IOCommunication.OperationInterface() {
                @Override
                public void onReceive(String message) {
                    try {
                        MeterModel meterModel = MeterModel.getSendableFromSeperatedData(message);
                        binding.getMeterViewModel().setMeterModel(meterModel);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onError(IOCommunication.ErrorType errorType, String errorMessage) {
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onReceiveZeroLength() {
                    Toast.makeText(getContext(), "zero length received", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "not connected", Toast.LENGTH_LONG).show();
        }
    }
}
