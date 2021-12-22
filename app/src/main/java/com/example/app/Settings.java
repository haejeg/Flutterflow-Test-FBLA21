package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
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
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        //Declarations
        switchTheme = (SwitchCompat) findViewById(R.id.switch_thememode);
        bgview = findViewById(R.id.imageview_bg);

        loadConfig(); //Loads settings config
        checkTheme(); //Initial check

        //Switch onClickListener like if the switch was to get clicked, it would set this function off.
        switchTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSwitch(SWITCHTHEME, switchTheme.isChecked());
                //Sets theme bg
                if (switchTheme.isChecked()) {
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

        //Visual Loader (like sets it true or false visual wise)
        switchTheme.setChecked(switchThemeCheck);
    }
}