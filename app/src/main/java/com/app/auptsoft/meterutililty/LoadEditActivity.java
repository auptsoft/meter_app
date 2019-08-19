package com.app.auptsoft.meterutililty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.CardView;

import com.app.auptsoft.meterutililty.model.Load;

import java.util.ArrayList;

/**
 * Created by Administrator on 12/9/2018.
 */

public class LoadEditActivity extends AppCompatActivity {
    LinearLayout loadItemsLayout;
    LayoutInflater layoutInflater;

    ArrayList<Load> loadArrayList;

    Button addNewItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadItemsLayout = (LinearLayout) findViewById(R.id.load_item_layout_id);

        layoutInflater = getLayoutInflater();

        addNewItem = (Button) findViewById(R.id.add_new_load_id);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AddLoadActivity.class));
            }
        });

        //updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        super.onBackPressed();
    }

    private void updateView() {
        loadItemsLayout.removeAllViews();
        loadArrayList = Load.getAll(this);
        for (int index = 0; index < loadArrayList.size(); index++) {
            Load load = loadArrayList.get(index);
            CardView loadEditItem = (CardView) layoutInflater.inflate(R.layout.load_edit_item, null);
            final EditText loadNameEdit = (EditText) loadEditItem.findViewById(R.id.load_name_id);
            ImageButton updateButton = (ImageButton) loadEditItem.findViewById(R.id.update_load_item_id);
            ImageButton deleteButton = (ImageButton) loadEditItem.findViewById(R.id.delete_load_item_id);

            loadNameEdit.setText(load.getName());

            loadNameEdit.setTag(index);
            updateButton.setTag(index);
            deleteButton.setTag(index);

            //loadNameEdit.setOnFocusChangeListener(onNameEditFocusChange);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int) view.getTag();
                    //Toast.makeText(getBaseContext(), index+"", Toast.LENGTH_LONG).show();   //debug
                    loadArrayList.get(index).setName(loadNameEdit.getText().toString());
                    int a = Load.update(getBaseContext(), loadArrayList.get(index));
                    //Toast.makeText(getBaseContext(), a+"", Toast.LENGTH_LONG).show();  //debug
                    updateView();

                    Toast.makeText(getBaseContext(), "Item updated successfully", Toast.LENGTH_LONG).show();
                }
            });


            deleteButton.setOnClickListener(onClickDeleteLoadItem);

            loadItemsLayout.addView(loadEditItem);
        }
    }

    private View.OnFocusChangeListener onNameEditFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            EditText editText = (EditText) view;
            String newName = editText.getText().toString();

            int index = (int) editText.getTag();
            loadArrayList.get(index).setName(newName);
            Toast.makeText(getBaseContext(), newName, Toast.LENGTH_SHORT).show();
        }
    };

    public View.OnClickListener onClickDeleteLoadItem = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final int index = (int) view.getTag();

            Snackbar.make(addNewItem, "Delete " + loadArrayList.get(index).getName() + "?", Snackbar.LENGTH_LONG)
                    .setAction("Yes", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Load.delete(getBaseContext(), loadArrayList.get(index));
                            updateView();
                            Toast.makeText(getBaseContext(), "Item deleted successfully", Toast.LENGTH_LONG).show();
                        }
            }).show();

            /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getBaseContext());
            alertDialogBuilder.setTitle("Delete Confirmation");
            alertDialogBuilder.setMessage("Do you really want to delete this item?");
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Load.delete(getBaseContext(), loadArrayList.get(index));
                    updateView();
                    Toast.makeText(getBaseContext(), "Item deleted successfully", Toast.LENGTH_LONG).show();
                }
            });

            alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialogBuilder.show(); */
        }
    };
}