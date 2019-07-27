package com.app.auptsoft.homeenergyplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.auptsoft.homeenergyplanner.model.Load;

/**
 * Created by Administrator on 12/9/2018.
 */

public class AddLoadActivity extends AppCompatActivity {
    EditText newLoadItemNameEdit;
    Button addNewItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_load);

        newLoadItemNameEdit = (EditText)findViewById(R.id.new_load_name_id);
        addNewItem = (Button)findViewById(R.id.add_new_action_id);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = newLoadItemNameEdit.getText().toString();
                Load load = new Load(0, newName, 0, 0, 0, 0, "");

                Load.insert(getBaseContext(), load);
                Toast.makeText(getBaseContext(), "New item added", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
