package com.example.app;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleAdder extends AppCompatActivity {
    private ImageView bgview;
    private Button addButton;
    private EditText title;
    private EditText title2;
    private EditText title3;
    private EditText title4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_scheduleadder);
        getSupportActionBar().hide();

        TinyDB tinydb = new TinyDB(getApplicationContext());

        //Declare ui elements
        bgview = findViewById(R.id.imageview_bg);
        addButton = findViewById(R.id.button);
        title = findViewById(R.id.editTextTextPersonName);
        title2 = findViewById(R.id.editTextTextPersonName2);
        title3 = findViewById(R.id.editTextTextPersonName3);
        title4 = findViewById(R.id.editTextTextPersonName4);

        title.setText(tinydb.getString(Settings.title));
        title2.setText(tinydb.getString(Settings.title2));
        title3.setText(tinydb.getString(Settings.title3));
        title4.setText(tinydb.getString(Settings.title4));

        //Theme check
        if (MainActivity.theme) {
            bgview.setImageResource(R.drawable.image_bglight);
        }
        else {
            bgview.setImageResource(R.drawable.image_bgdark);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is incorrect i think
                tinydb.putString(Settings.title, title.getText().toString());
                tinydb.putString(Settings.title2, title2.getText().toString());
                tinydb.putString(Settings.title3, title3.getText().toString());
                tinydb.putString(Settings.title4, title4.getText().toString());
                Intent intent = new Intent(ScheduleAdder.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
