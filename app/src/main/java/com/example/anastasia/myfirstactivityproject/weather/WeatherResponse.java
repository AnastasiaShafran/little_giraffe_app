package com.example.anastasia.myfirstactivityproject.weather;

import java.util.List;

/**
 * Created by Tal on 15/05/2016.
 */
public class WeatherResponse {
    final transient String DEGREE  = "\u00b0";
    // Base address to get icon
    private final String ICON_ADDRESS = "http://openweathermap.org/img/w/";

    // inner classes
    static class Weather
    {
        String description;
        String icon;
    }
    static class Main
    {
        Double temp;
    }
    // Member variables of a class
    List<Weather> weather;
    Main main;
    String name;

    // Create a method that convert temp to Celsius
    public String getTempraturCelsius()
    {
        Double temp = main.temp - 272.15f;

        return String.format("%.2f", temp) + DEGREE;
    }

    // Create method that get the icon base on address
    public String getIconAddress() {
        return ICON_ADDRESS + weather.get(0).icon + ".png";
    }

    // Create method that get Description
    public String getDescription()
    {
        if (weather != null && weather.size() > 0)
            return weather.get(0).description;
        return null;
    }
}
