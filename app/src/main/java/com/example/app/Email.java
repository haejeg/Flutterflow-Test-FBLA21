package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnFocusChangeListener;

public class Email extends AppCompatActivity {
    private EditText title;
    private EditText receivers;
    private EditText message;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_email);
        getSupportActionBar().hide();

        //variable setter
        sendButton = findViewById(R.id.button2);
        title = findViewById(R.id.editTextTextPersonName);
        receivers = findViewById(R.id.editTextTextPersonName5);
        message = findViewById(R.id.editTextTextPersonName6);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setHint("");
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }

    private void send() {
        //Just some email stuff
        String recList = receivers.getText().toString();
        String[] r = recList.split(",");
        String t = title.getText().toString();
        String m = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, r);
        intent.putExtra(Intent.EXTRA_SUBJECT, t);
        intent.putExtra(Intent.EXTRA_TEXT, m);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
