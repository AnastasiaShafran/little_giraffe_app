package com.example.anastasia.myfirstactivityproject.weather;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Tal on 15/05/2016.
 */
public class RestClient {
    private static WeatherRestInterface weatherRestInterface;
    //private static String baseUrl = "https://api.github.com";

    private static final String BASE_ADDR = "http://api.openweathermap.org";


    // using OkHttp for authenticate user
    public static WeatherRestInterface getClient()
    {
        if(weatherRestInterface == null)
        {
            // get approve for user - Request -> Response
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });
            // .addConverterFactory(new ConverterClass())
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_ADDR)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherRestInterface = client.create(WeatherRestInterface.class);
        }
        return weatherRestInterface;
    }
}
