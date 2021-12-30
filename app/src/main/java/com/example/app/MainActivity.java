package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

        handler();
    }

    private void handler() {
        final Handler handler = new Handler(Looper.getMainLooper());
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        a.reset();
        TextView fossilText = (TextView) findViewById(R.id.text_myfossil);
        TextView descText = (TextView) findViewById(R.id.textView2);
        descText.clearAnimation();
        descText.startAnimation(a);
        fossilText.clearAnimation();
        fossilText.startAnimation(a);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        }, 3000);
    }
}