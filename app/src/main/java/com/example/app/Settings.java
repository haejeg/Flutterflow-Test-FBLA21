package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.card.MaterialCardView;

public class Settings extends AppCompatActivity {
    private boolean switchTemperatureCheck;
    private SwitchCompat switchTemperature;
    private MaterialCardView savebutton;
    //SHARED PREFERENCES AKA SETTINGS SAVER//
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEMPSWITCH = "switch_temp";
    //Titles for schedules
    public static final String title = "string_title";
    public static final String title2 = "string_title1";
    public static final String title3 = "string_title2";
    public static final String title4 = "string_title3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        //Declarations
        switchTemperature = findViewById(R.id.switch_temp);
        savebutton = findViewById(R.id.savebutton);

        loadConfig(); //Loads settings config

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //yes, just in case you are confused, this doesn't do anything except restart the application!
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        switchTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using an actual sharedpreferences saver so it doesn't just seem like I can only use tinyDB lol
                saveSwitch(TEMPSWITCH, switchTemperature.isChecked());
            }
        });
    }

    public void saveSwitch(String name, boolean value) {
        //This section is for saving switch values
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); //No external interference
        SharedPreferences.Editor modifier = prefs.edit(); //Modifier for preference saving
        modifier.putBoolean(name, value); //Update value
        modifier.commit(); //Commit and save
    }

    public void loadConfig() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); //No external interference
        switchTemperatureCheck = prefs.getBoolean(TEMPSWITCH, false); //Gets value of temperature check and loads it

        //Visual Loader (like sets it true or false visual wise)
        switchTemperature.setChecked(switchTemperatureCheck);
    }
}