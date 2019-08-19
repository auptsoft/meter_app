package com.app.auptsoft.meterutililty;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.databinding.ActivityMeterEditBinding;
import com.app.auptsoft.meterutililty.model.Meter;
import com.app.auptsoft.meterutililty.presenters.MeterEditActivityPresenter;
import com.google.gson.Gson;


public class MeterEditActivity extends AppCompatActivity implements View.OnClickListener {
    MeterEditActivityPresenter presenter;
    ActivityMeterEditBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_meter_edit);
        presenter = new MeterEditActivityPresenter();
        presenter.setMeter(AppState.meter);
        binding.setPresenter(presenter);
        presenter.setMeterIp(AppState.meterIp);
        presenter.setPlanerIp(AppState.planerIp);

        //setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Meter Info");

        binding.saveMeterInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.saveMeterInfo)) {
            String meterJson = new Gson().toJson(presenter.getMeter(), Meter.class);
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, meterJson, Toast.LENGTH_LONG).show();
            }
            saveMeter(meterJson);

            Intent intent = new Intent(this, MainActivity.class);
            if(AppState.isFirstTime) {
                startActivity(intent);
            }

            AppState.preferences.edit().putBoolean(AppState.IS_FIRST_TIME_PREF, false).apply();

            finish();
        }
    }

    void saveMeter(String meterJson) {
        //AppState.preferences.edit().putString(AppState.METER_PREF, meterJson).apply();

        AppState.preferences.edit()
                .putString(AppState.METER_IP_PREF, presenter.getMeterIp())
                .putString(AppState.PLANER_IP_PREF, presenter.getPlanerIp())
                .apply();

        AppState.meterIp = presenter.getMeterIp();
        AppState.planerIp = presenter.getPlanerIp();
    }
}
