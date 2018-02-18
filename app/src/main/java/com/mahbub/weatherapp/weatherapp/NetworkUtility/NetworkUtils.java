package com.mahbub.weatherapp.weatherapp.NetworkUtility;

import android.net.Uri;

import java.net.URL;

/**
 * Created by mahbub on 2/18/2018.
 */

public class NetworkUtils {
    public static final String BASE_WEATHER_URL = "";
    public static final String QUERY_WEATHER_URL = "";

    // create method to return url
    public static URL getWeatherUrl(String queryUrl){
        Uri buildUri = Uri.parse(BASE_WEATHER_URL).buildUpon()
                .appendQueryParameter(QUERY_WEATHER_URL,queryUrl)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }


}
