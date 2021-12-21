package com.example.app;

public class StaticClasses {
    public static int randomIntFrom(int minimum, int maximum) {
        return 1; //temp
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
