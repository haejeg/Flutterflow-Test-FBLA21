package com.example.app;

import static com.example.app.StaticClasses.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //this is temporary lol
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
        return;
    }
}