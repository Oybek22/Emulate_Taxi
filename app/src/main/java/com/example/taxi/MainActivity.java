package com.example.taxi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.text.TextUtils.substring;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;

    EditText telephone_number;
    EditText name;
    EditText surname;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String APP_PREFERENCES = "mysettings";
        final String APP_PREFERENCES_TELNUMBER = "telephone_number";
        final String APP_PREFERENCES_NAME = "name";
        final String APP_PREFERENCES_SURNAME = "surname";

        telephone_number = (EditText) findViewById(R.id.tel_number_value);
        name = (EditText) findViewById(R.id.name_value);
        surname = (EditText) findViewById(R.id.surname_value);
        button = (Button) findViewById(R.id.button_registration);

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
        if(sPref.contains(APP_PREFERENCES_TELNUMBER) && sPref.contains(APP_PREFERENCES_NAME) && sPref.contains(APP_PREFERENCES_SURNAME)){
            button.setText("Log In");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String begin1 = telephone_number.getText().toString().substring(0, 3);
                String begin2 = telephone_number.getText().toString().substring(0, 2);
                Integer length = telephone_number.getText().length();

                if (((begin1.equals("+79") && length == 12) || (begin2.equals("89") && length == 11)) && length > 0) {

                    if (!name.getText().toString().equals("") && !surname.getText().toString().equals("")) {
                        Intent intent = new Intent("android.intent.action.TaxiCall");
                        intent.putExtra("Telephone_number", telephone_number.getText().toString());
                        intent.putExtra("Name", name.getText().toString());
                        intent.putExtra("Surname", surname.getText().toString());

                        if (intent.resolveActivity(getPackageManager()) != null) {

                            sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sPref.edit();
                            editor.putString(APP_PREFERENCES_TELNUMBER, telephone_number.getText().toString());
                            editor.putString(APP_PREFERENCES_NAME, name.getText().toString());
                            editor.putString(APP_PREFERENCES_SURNAME, surname.getText().toString());
                            if (editor.commit()) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Your information save", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error, please report a bug", Toast.LENGTH_LONG);
                            toast.show();
                            Log.d("MyLogs", "Can't call 'TaxiCall' Activity");
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Fill in the Name and Surname", Toast.LENGTH_LONG);
                        toast.show();
                        Log.d("MyLogs", "Blank Name and Surname");
                    }

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong or blank Telephone number", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("MyLogs", "Wrong or blank Telephone number");
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();


    }
}