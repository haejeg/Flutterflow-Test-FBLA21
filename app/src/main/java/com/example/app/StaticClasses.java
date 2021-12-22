package com.example.app;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

public class StaticClasses {
    public static int randomIntFrom(int minimum, int maximum) {
        return (int) ((Math.random() * (maximum - minimum)) + minimum);
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
