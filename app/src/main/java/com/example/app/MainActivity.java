package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView bgview;
    public static boolean theme;
    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Declarations
        bgview = findViewById(R.id.imageview_bg);

        //Load configurations
        SharedPreferences prefs = getSharedPreferences(Settings.SHARED_PREFS, MODE_PRIVATE); //No external interference
        theme = prefs.getBoolean(Settings.SWITCHTHEME, false); //Get value of switch

        //Loads the theme accordingly
        if (theme) {
            bgview.setImageResource(R.drawable.image_bglight);
        }
        else {
            bgview.setImageResource(R.drawable.image_bgdark);
        }

        //this is temporary lol
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, Menu.class);
                startActivity(intent2);
            }
        });
    }
}