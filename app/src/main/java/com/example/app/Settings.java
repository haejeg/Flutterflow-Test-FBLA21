package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class Settings extends AppCompatActivity {
    private SwitchCompat switchTheme;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCHTHEME = "switch_thememode";
    private boolean switchThemeCheck;
    public static boolean theme;
    private ImageView bgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        //Declarations
        switchTheme = (SwitchCompat) findViewById(R.id.switch_thememode);
        bgview = findViewById(R.id.imageview_bg);

        loadConfig(); //Loads settings config
        checkTheme(); //Initial check

        switchTheme.setOnClickListener(new View.OnClickListener() { //Switch onClickListener
            @Override
            public void onClick(View view) {
                saveSwitch(SWITCHTHEME, switchTheme.isChecked());
                if (switchTheme.isChecked()) { //Sets theme bg
                    bgview.setImageResource(R.drawable.image_bglight);
                    theme = true;
                }
                else {
                    bgview.setImageResource(R.drawable.image_bgdark);
                    theme = false;
                }
                checkTheme(); //Double check
            }
        });
    }

    public void checkTheme() { //Checks theme and initializes it properly
        StaticClasses.sleep(50);
        if (switchTheme.isChecked()) {
            bgview.setImageResource(R.drawable.image_bglight);
            theme = true;
        }
        else {
            bgview.setImageResource(R.drawable.image_bgdark);
            theme = false;
        }
    }

    public void saveSwitch(String name, boolean value) {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); //No external interference
        SharedPreferences.Editor modifier = prefs.edit(); //Modifier for preference saving
        modifier.putBoolean(name, value); //Update value
        modifier.commit(); //Commit and save
    }

    public void loadConfig() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); //No external interference
        switchThemeCheck = prefs.getBoolean(SWITCHTHEME, false); //Gets value of themecheck and loads it

        //Visual Loader
        switchTheme.setChecked(switchThemeCheck);
    }
}