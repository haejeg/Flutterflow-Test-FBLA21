package com.example.app;

import static com.example.app.StaticClasses.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView bgview;
    public static boolean theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        bgview = findViewById(R.id.imageview_bg);
        SharedPreferences prefs = getSharedPreferences(Settings.SHARED_PREFS, MODE_PRIVATE); //No external interference
        theme = prefs.getBoolean(Settings.SWITCHTHEME, false); //Get value of switch

        if (theme) { //Loads theme
            bgview.setImageResource(R.drawable.image_bglight);
        }
        else {
            bgview.setImageResource(R.drawable.image_bgdark);
        }

        //this is temporary lol
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
        return;
    }
}