package com.example.taxi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetPath extends AppCompatActivity {

    Toolbar toolbar;
    EditText city_value_from;
    EditText street_value_from;
    EditText house_number_value_from;
    EditText city_value_to;
    EditText street_value_to;
    EditText house_number_value_to;
    Button confirm_addresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_path);

        Log.d("MyLogs", "Create SetPath");

        initViews();
        toolBarSet();

        confirm_addresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(city_value_from.getText().toString()) && !TextUtils.isEmpty(street_value_from.getText().toString()) && !TextUtils.isEmpty(house_number_value_from.getText().toString())
                        && !TextUtils.isEmpty(city_value_to.getText().toString()) && !TextUtils.isEmpty(street_value_to.getText().toString()) && !TextUtils.isEmpty(house_number_value_to.getText().toString())) {
                    Intent data = new Intent();
                    data.putExtra(TaxiCall.FROM_CITY, city_value_from.getText().toString());
                    data.putExtra(TaxiCall.FROM_STREET, street_value_from.getText().toString());
                    data.putExtra(TaxiCall.FROM_HOUSE_NUMBER, house_number_value_from.getText().toString());
                    data.putExtra(TaxiCall.TO_CITY, city_value_to.getText().toString());
                    data.putExtra(TaxiCall.TO_STREET, street_value_to.getText().toString());
                    data.putExtra(TaxiCall.TO_HOUSE_NUMBER, house_number_value_to.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please, enter addresses", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("MyLogs", "Blank addresses");
                }
            }
        });
    }

    private void toolBarSet() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cross_back_button);
        getSupportActionBar().setTitle("Call taxi");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        setResult(RESULT_CANCELED);
        return true;
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_SetPath);
        city_value_from = (EditText) findViewById(R.id.city_from_value_SetPath);
        street_value_from = (EditText) findViewById(R.id.street_from_value_SetPath);
        house_number_value_from = (EditText) findViewById(R.id.house_number_from_value_SetPath);
        city_value_to = (EditText) findViewById(R.id.city_to_value_SetPath);
        street_value_to = (EditText) findViewById(R.id.street_to_value_SetPath);
        house_number_value_to = (EditText) findViewById(R.id.house_number_to_value_SetPath);
        confirm_addresses = (Button) findViewById(R.id.confirm_addresses_SetPath);
        Log.d("MyLogs", "Work initViews()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLogs", "Start SetPath");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLogs", "Resume SetPath");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLogs", "Pause SetPath");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLogs", "Stop SetPath");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLogs", "Destroy SetPath");
    }
}