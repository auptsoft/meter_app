package com.app.auptsoft.meterutililty.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.R;
import com.app.auptsoft.meterutililty.databinding.FragmentMeterBinding;
import com.app.auptsoft.meterutililty.model.MeterStatus;
import com.app.auptsoft.meterutililty.model.NMeterStatus;
import com.app.auptsoft.meterutililty.presenters.MeterFragmentPresenter;
import com.app.auptsoft.meterutililty.services.Gateway;
import com.app.auptsoft.meterutililty.services.IOCommunication;
import com.app.auptsoft.meterutililty.view_models.MeterViewModel;

import es.dmoral.toasty.Toasty;


/**
 * Created by Andrew on 19.3.19.
 */

public class MeterFragment extends DialogFragment implements View.OnClickListener {
    FragmentMeterBinding binding;
    MeterViewModel meterViewModel;
    MeterFragmentPresenter presenter;
    IOCommunication ioCommunication;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meter, container, false);
        View view = binding.getRoot();

        MeterStatus meterModel = MeterStatus.getSendable("000000000000000000000000000");
        presenter = new MeterFragmentPresenter();

        binding.setPresenter(presenter);

        //getMeterUpdate();

        binding.statusAction.setOnClickListener(this);
        binding.rechargeAction.setOnClickListener(this);
        binding.changePhaseAction.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.statusAction)) {
            getMeterUpdate();
        }
    }

    void getMeterUpdate() {
        presenter.setLoading(true);
        Snackbar.make(binding.statusAction, "Retrieving Status. Please wait", Snackbar.LENGTH_INDEFINITE).show();
        Gateway.getMeterStatus(new Gateway.OnOperationCompleteListener() {
            @Override
            public void onOperationComplete(String url, String msg) {
                presenter.setLoading(false);
                Snackbar.make(binding.statusAction, "Completed", Snackbar.LENGTH_LONG).show();
                if (AppState.debug) {
                    Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
                }
                Toasty.success(getContext(), msg).show();

                NMeterStatus nMeterStatus = NMeterStatus.fromFormatedString(msg);
                if (nMeterStatus != null) {
                    AppState.nMeterStatus = nMeterStatus;
                    new NMeterStatusFragment().show(getChildFragmentManager(), "Meter status");
                } else {
                    Toasty.warning(getContext(), "Received data not well formatted").show();
                }
            }

            @Override
            public void onError(String url, String msg) {
                presenter.setLoading(false);
                Snackbar.make(binding.statusAction, "Error Occurred", Snackbar.LENGTH_LONG).show();
                if (AppState.debug) {
                    Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
                }
                Toasty.error(getContext(), msg).show();
            }
        });
    }
}
