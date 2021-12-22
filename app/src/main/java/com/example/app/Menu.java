package com.example.app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Menu extends AppCompatActivity {
    private TextView txtWeather;
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=Fort%20Collins&appid=dcb45342047b8ec7d3da562f53e8688f";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        txtWeather = (TextView) findViewById(R.id.textView3);


        getWeatherDetails();
    }

    public void getWeatherDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double temp_min = jsonObjectMain.getDouble("temp_min") - 273.15;
                    double temp_max = jsonObjectMain.getDouble("temp_max") - 273.15;
                    double temp_feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
//                    txtWeather2.setText("Feels like: " + df.format(temp_feelsLike) + " °C");
                    txtWeather.setText("Temperature: " + df.format(temp) + " °C \n" + "Feels like: " + df.format(temp_feelsLike) + " °C");
                    temp_min = Math.round(temp_min * 100.0) / 100.0;
                    temp_max = Math.round(temp_max * 100.0) / 100.0;
//                    boundTemp.setText("Max: " + temp_min + " °C | Min: " + temp_max + " °C");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

