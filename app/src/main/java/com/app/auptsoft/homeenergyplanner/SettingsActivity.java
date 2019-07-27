package com.app.auptsoft.homeenergyplanner;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.app.auptsoft.homeenergyplanner.databinding.ActivitySettingsBinding;
import com.app.auptsoft.homeenergyplanner.view_models.SettingsViewModel;

/**
 * Created by Andrew on 21.3.19.
 */

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        SettingsViewModel settingsViewModel = new SettingsViewModel(this, AppState.alwaysRequestForPassword, "", "");
        binding.setViewModel(settingsViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
