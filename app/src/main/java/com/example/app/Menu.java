package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.Calendar;

public class Menu extends AppCompatActivity {
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=Fort%20Collins&appid=dcb45342047b8ec7d3da562f53e8688f"; //string for weather. real time
    DecimalFormat df = new DecimalFormat("#.##"); //rounding

    //ui elements
    private TextView txtWeather, txtFood;
    private ImageView bgview;
    public boolean temperatureMode;
    private ImageButton settingsButton;
    private ImageView addScheduleButton;
    private TextView txtCalender;
    private TextView txtLunch;
    private ImageButton emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Stuff up here are defaults unless commented
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); //Fade in and out animation
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        TinyDB tinydb = new TinyDB(getApplicationContext());

        bgview = findViewById(R.id.imageview_bg);

        //declare ui elements here
        txtCalender = findViewById(R.id.textView5);
        txtWeather = findViewById(R.id.textView3);
        settingsButton = findViewById(R.id.imageButton);
        txtFood =  findViewById(R.id.textView4);
        addScheduleButton = findViewById(R.id.imageView);
        txtLunch = findViewById(R.id.textView7);
        emailButton = findViewById(R.id.imageButton2);

        //get calender stuff so lunch menu hyperlink stays updated and requires no manual stuff
        int year = Calendar.getInstance().get(Calendar.YEAR); //2022
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int date = Calendar.getInstance().get(Calendar.DATE);

        //set hyperlink for lunch menu
        txtLunch.setClickable(true);
        txtLunch.setMovementMethod(LinkMovementMethod.getInstance());
        txtLunch.setTextColor(Color.parseColor("#FFFFFF"));
        String text = "<a href='https://psdschools.nutrislice.com/menu/fossil-ridge/lunch/" + year + "-" + month + "-" + date + "'> Lunch Menu </a>";
        txtLunch.setText(Html.fromHtml(text));

        //get weather details, current temp and what it feels like (requires internet)
        getWeatherDetails();

        //sets schedule
        txtCalender.setText(tinydb.getString(Settings.title) + "\n" + tinydb.getString(Settings.title2) + "\n" + tinydb.getString(Settings.title3) + "\n" + tinydb.getString(Settings.title4));

        //onclicklistener to change schedules
        addScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ScheduleAdder.class);
                startActivity(intent);
            }
        });

        //onclicklistener for settings button.
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Settings.class);
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Email.class);
                startActivity(intent);
            }
        });
    }

    //used to scrape things off websites etc, probably not going to be used as it was originally planned for lunch menu display but lunch menu display at nutrislice sucks

    //        webscrape web = new webscrape();
//        web.execute();
//    private class webscrape extends AsyncTask<Void, Void, Void> {
//        String desc;
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Date time = Calendar.getInstance().getTime();
////            try {
////                String url = "https://psdschools.nutrislice.com/menu/fossil-ridge/lunch/" + time.getYear() + "-" + time.getMonth() + "-" + time.getDate();
////                String url = "https://psdschools.nutrislice.com/menu/fossil-ridge/lunch/2022-01-10";
////                Document doc = Jsoup.connect(url).get();
////                Elements data = doc.getElementsByAttribute("span.food-name");
////                Elements elements = doc.select("span.food-name");
////                String url = "https://www.google.com/search?q=mesozoic+era&rlz=1C1ASUM_enUS962US962&sxsrf=AOaemvKDheYKNsLYk_AAuZ8vq320XYyNLg%3A1640987939561&ei=I33PYbLdIcewqtsPl_WNsAU&oq=mesoera&gs_lcp=Cgdnd3Mtd2l6EAMYADIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yCAgAEAcQChAeMgYIABAHEB46BwgAEEcQsAM6BwgAELADEEM6CAgAEOQCELADOhAILhDHARCjAhDIAxCwAxBDOhAILhDHARDRAxDIAxCwAxBDOgoILhDIAxCwAxBDOg0ILhCxAxDHARDRAxBDOgUIABCABDoHCAAQsQMQQ0oECEEYAEoECEYYAVD7BliZCWDXEGgBcAJ4AIABaIgBgAOSAQMyLjKYAQCgAQHIARPAAQE&sclient=gws-wiz";
////                Document doc = Jsoup.connect(url).get();
////                Elements data = doc.getElementsByClass("hgKElc");
////                for (Element i : elements) {
////                    Toast.makeText(getApplication().getBaseContext(), i.text(), Toast.LENGTH_SHORT).show();
////                }
////                    txtFood.setText((CharSequence) data);
//
//
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
////            Toast.makeText(Menu.this, (String) desc, Toast.LENGTH_LONG).show();
////            txtFood.setText(desc);
//        }
//    }

    public void getWeatherDetails() {
        //Grab weather information off of *url* using dates
        TinyDB tinydb = new TinyDB(getApplicationContext());
        temperatureMode = tinydb.getBoolean(Settings.TEMPSWITCH);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    if (temperatureMode) { //if celsius else fahrenhite
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double temp_min = jsonObjectMain.getDouble("temp_min") - 273.15;
                        double temp_max = jsonObjectMain.getDouble("temp_max") - 273.15;
                        double temp_feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        txtWeather.setText("Temperature: " + df.format(temp) + " °C \n" + "Feels like: " + df.format(temp_feelsLike) + " °C");
                        temp_min = Math.round(temp_min * 100.0) / 100.0;
                        temp_max = Math.round(temp_max * 100.0) / 100.0;
                    }
                    else {
                        double temp = kelvinToFahrenhite(jsonObjectMain.getDouble("temp"));
                        double temp_min = kelvinToFahrenhite(jsonObjectMain.getDouble("temp_min"));
                        double temp_max = kelvinToFahrenhite(jsonObjectMain.getDouble("temp_max"));
                        double temp_feelsLike =+ kelvinToFahrenhite(jsonObjectMain.getDouble("feels_like"));
                        txtWeather.setText("Temperature: " + df.format(temp) + " °F \n" + "Feels like: " + df.format(temp_feelsLike) + " °F");
                    }
                }
                catch (JSONException e) {
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

    public double kelvinToFahrenhite(double temperature) {
        //why are the conversions so dumb
        return (temperature - 273.15) * 1.8 + 32.00;
    }
}

