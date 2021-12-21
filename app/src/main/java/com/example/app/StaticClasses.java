package com.example.app;

import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

public class StaticClasses {
    public static int randomIntFrom(int minimum, int maximum) {
        return (int) ((Math.random() * (maximum - minimum)) + minimum);
    }

    public static void sleep(int ms) {
        //Sleep function
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
