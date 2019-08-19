package com.app.auptsoft.meterutililty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.auptsoft.meterutililty.fragments.LoginFragment;

/**
 * Created by Andrew on 21.3.19.
 */

public class AuthencateActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.authenticate_main_frame, new LoginFragment()).commit();
    }
}
