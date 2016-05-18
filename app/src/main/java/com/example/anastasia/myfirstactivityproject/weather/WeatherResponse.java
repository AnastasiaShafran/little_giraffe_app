package com.example.anastasia.myfirstactivityproject.weather;

import java.util.List;

/**
 * Created by Tal on 15/05/2016.
 */
public class WeatherResponse {
    final transient String DEGREE  = "\u00b0";
    private final String ICON_ADDRESS = "http://openweathermap.org/img/w/";


    static class Weather
    {
        String description;
        String icon;
    }
    static class Main
    {
        Double temp;
    }

    List<Weather> weather;
    Main main;
    String name;

    public String getTempraturCelsius()
    {
        Double temp = main.temp - 272f;


        return String.format("%.2f", temp) + DEGREE + "C" + "  " + "in";
    }


    public String getIconAddress() {
        return ICON_ADDRESS + weather.get(0).icon + ".png";
    }


    public String getDescription()
    {
        if (weather != null && weather.size() > 0)
            return weather.get(0).description;
        return null;
    }
}
