package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RenderEffect;
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

import com.google.android.material.card.MaterialCardView;

public class Settings extends AppCompatActivity {
    private SwitchCompat switchTheme;
    private boolean switchThemeCheck;
    private boolean switchTemperatureCheck;
    private SwitchCompat switchTemperature;
    public boolean temperatureMode;
    private ImageView bgview;
    private MaterialCardView savebutton;

    //SHARED PREFERENCES AKA SETTINGS SAVER//
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCHTHEME = "switch_thememode";
    public static final String TEMPSWITCH = "switch_temp";
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

        TinyDB tinydb = new TinyDB(getApplicationContext());

        //Declarations
        switchTheme = findViewById(R.id.switch_thememode);
        bgview = findViewById(R.id.imageview_bg);
        switchTemperature = findViewById(R.id.switch_temp);
        savebutton = findViewById(R.id.savebutton);

        //loads settings
        switchTheme.setChecked(tinydb.getBoolean(SWITCHTHEME));
        switchTemperature.setChecked(tinydb.getBoolean(TEMPSWITCH));

        //sets theme initially
        if (tinydb.getBoolean(SWITCHTHEME)) bgview.setImageResource(R.drawable.image_bglight);
        else bgview.setImageResource(R.drawable.image_bgdark);

        //save button (restarts the program)
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //yes, just in case you are confused, this doesn't do anything except restart the application!
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //onClickListener for theme switch
        switchTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(SWITCHTHEME, switchTheme.isChecked());
                //sets theme on switch, not initially
                if (tinydb.getBoolean(SWITCHTHEME)) bgview.setImageResource(R.drawable.image_bglight);
                else bgview.setImageResource(R.drawable.image_bgdark);
            }
        });

        //onClickListener for temperature switch
        switchTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(TEMPSWITCH, switchTemperature.isChecked());
                //Sets temp mode
                if (switchTemperature.isChecked()) {

                    temperatureMode = true;
                }
                else {
                    temperatureMode = false;
                }
            }
        });
    }
}