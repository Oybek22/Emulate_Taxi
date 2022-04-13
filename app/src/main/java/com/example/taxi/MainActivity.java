package com.example.taxi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import static android.text.TextUtils.substring;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;

    static final String APP_PREFERENCES = "mysettings";
    static final String APP_PREFERENCES_TELNUMBER = "telephone_number";
    static final String APP_PREFERENCES_NAME = "name";
    static final String APP_PREFERENCES_SURNAME = "surname";

    EditText telephone_number;
    EditText name;
    EditText surname;
    Button button;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MyLogs", "Create MainActivity");

        initViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration details");

        loadData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(telephone_number.getText().toString()) && !TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(surname.getText().toString())) {
                    String begin1 = telephone_number.getText().toString().substring(0, 3);
                    String begin2 = telephone_number.getText().toString().substring(0, 2);
                    Integer length = telephone_number.getText().length();

                    if ((begin1.equals("+79") && length == 12) || (begin2.equals("89") && length == 11)) {
                        Intent intent = new Intent("android.intent.action.TaxiCall");
                        intent.putExtra("Telephone_number", telephone_number.getText().toString());
                        intent.putExtra("Name", name.getText().toString());
                        intent.putExtra("Surname", surname.getText().toString());

                        if (intent.resolveActivity(getPackageManager()) != null) {
                            saveData();
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error, please report a bug", Toast.LENGTH_LONG);
                            toast.show();
                            Log.d("MyLogs", "Can't call 'TaxiCall' Activity");
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Wrong Telephone number", Toast.LENGTH_LONG);
                        toast.show();
                        Log.d("MyLogs", "Wrong or blank Telephone number");
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please, enter your details", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("MyLogs", "Blank details");
                }
            }
        });
    }

    private void initViews() {
        telephone_number = (EditText) findViewById(R.id.tel_number_value_MainActivity);
        name = (EditText) findViewById(R.id.name_value_MainActivity);
        surname = (EditText) findViewById(R.id.surname_value_MainActivity);
        button = (Button) findViewById(R.id.button_registration_MainActivity);
        toolbar = (Toolbar) findViewById(R.id.toolbar_MainActivity);
        Log.d("MyLogs", "Work initViews()");
    }

    private void loadData() {
        sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        if (sPref.contains(APP_PREFERENCES_TELNUMBER)) {
            telephone_number.setText(sPref.getString(APP_PREFERENCES_TELNUMBER, ""));
        }
        if (sPref.contains(APP_PREFERENCES_NAME)) {
            name.setText(sPref.getString(APP_PREFERENCES_NAME, ""));
        }
        if (sPref.contains(APP_PREFERENCES_SURNAME)) {
            surname.setText(sPref.getString(APP_PREFERENCES_SURNAME, ""));
        }
        if (sPref.contains(APP_PREFERENCES_TELNUMBER) && sPref.contains(APP_PREFERENCES_NAME) && sPref.contains(APP_PREFERENCES_SURNAME)) {
            button.setText("Log In");
        }
        getSupportActionBar().setTitle("Data confirmation");
        Toast toast = Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_SHORT);
        toast.show();
        Log.d("MyLogs", "Load contact details");

    }

    private void saveData() {
        sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(APP_PREFERENCES_TELNUMBER, telephone_number.getText().toString());
        editor.putString(APP_PREFERENCES_NAME, name.getText().toString());
        editor.putString(APP_PREFERENCES_SURNAME, surname.getText().toString());
        if (editor.commit()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Your information save", Toast.LENGTH_SHORT);
            toast.show();
            Log.d("MyLogs", "Successful save contact details");
            return;
        }
        Log.d("MyLogs", "Unsuccessful save contact details");
    }

//    Use

//    SharedPreferences.Editor editor = sPref.edit();
//    editor.clear().commit();
//
//    to reset data


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLogs", "Start MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLogs", "Resume MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLogs", "Pause MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLogs", "Stop MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLogs", "Destroy MainActivity");
    }
}