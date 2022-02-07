package com.example.app;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SchoolSponsored extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_schoolsponsoredactivities);
        getSupportActionBar().hide();


    }
}
