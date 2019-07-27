package com.app.auptsoft.homeenergyplanner;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.auptsoft.homeenergyplanner.fragments.LoginFragment;

/**
 * Created by Andrew on 21.3.19.
 */

public class AuthencateActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        getSupportFragmentManager().beginTransaction().replace(R.id.authenticate_main_frame, new LoginFragment()).commit();
    }
}
