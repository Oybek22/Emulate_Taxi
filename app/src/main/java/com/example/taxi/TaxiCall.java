package com.example.taxi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class TaxiCall extends AppCompatActivity {

    static final String FROM_CITY = "FROM_CITY";
    static final String FROM_STREET = "FROM_STREET";
    static final String FROM_HOUSE_NUMBER = "FROM_HOUSE_NUMBER";
    static final String TO_CITY = "TO_CITY";
    static final String TO_STREET = "TO_STREET";
    static final String TO_HOUSE_NUMBER = "TO_HOUSE_NUMBER";

    Toolbar toolbar;
    TextView name_and_surname;
    TextView telephone_number;
    TextView taxi_arrive_time;
    Button set_path;
    Button call_taxi;


    ActivityResultLauncher<Intent> getAddresses = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Random random = new Random();

                        Intent intent = result.getData();
                        String fromCity = intent.getStringExtra(FROM_CITY);
                        String fromStreet = intent.getStringExtra(FROM_STREET);
                        String fromHouseNumber = intent.getStringExtra(FROM_HOUSE_NUMBER);
                        String toCity = intent.getStringExtra(TO_CITY);
                        String toStreet = intent.getStringExtra(TO_STREET);
                        String toHouseNumber = intent.getStringExtra(TO_HOUSE_NUMBER);
                        taxi_arrive_time.setText(("Taxi will arrive at " + fromCity + ", " + fromStreet + ", " + fromHouseNumber + " in " + String.valueOf(random.nextInt(20) + 1) +
                                " minutes and take you in " + toCity + ", " + toStreet + ", " + toHouseNumber + ". If you agree click Call Taxi"));
                        call_taxi.setVisibility(View.VISIBLE);
                    } else {
                        call_taxi.setVisibility(View.INVISIBLE);
                        taxi_arrive_time.setText("");
                        Toast toast = Toast.makeText(getApplicationContext(), "Set path cancelled", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("MyLogs", "Set path cancelled");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_call);

        Log.d("MyLogs", "Create TaxiCall");

        initViews();
        setInformation();
        toolBarSet();

        set_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SetPath");
                getAddresses.launch(intent);
                Log.d("MyLogs", "Launch SetPath");
            }
        });

        call_taxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Wait for Taxi. Have a good trip!", Toast.LENGTH_SHORT);
                toast.show();
                Log.d("MyLogs", "Taxi called");
            }
        });
    }


    private void toolBarSet() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Call taxi");
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_TaxiCall);
        name_and_surname = (TextView) findViewById(R.id.name_and_surname_text_TaxiCall);
        telephone_number = (TextView) findViewById(R.id.tel_number_text_TaxiCall);
        set_path = (Button) findViewById(R.id.set_path_TaxiCall);
        taxi_arrive_time = (TextView) findViewById(R.id.taxi_arrive_time_TaxiCall);
        call_taxi = (Button) findViewById(R.id.call_taxi_TaxiCall);
        Log.d("MyLogs", "Work initViews()");
    }

    private void setInformation() {
        Intent intent = getIntent();
        name_and_surname.setText((intent.getStringExtra("Name") + " " + intent.getStringExtra("Surname")));
        telephone_number.setText(intent.getStringExtra("Telephone_number"));
        Log.d("MyLogs", "Work setInformation()");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLogs", "Start TaxiCall");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLogs", "Resume TaxiCall");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLogs", "Pause TaxiCall");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLogs", "Stop TaxiCall");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLogs", "Destroy TaxiCall");
    }
}