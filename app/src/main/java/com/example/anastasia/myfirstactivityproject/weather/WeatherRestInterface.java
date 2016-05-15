package com.example.anastasia.myfirstactivityproject.weather;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;


public interface WeatherRestInterface {
    // Get
    @GET("/data/2.5/weather/")
    Call<WeatherResponse> getCityWeather(@Query("q") String name, @Query("appid") String appId);

}
