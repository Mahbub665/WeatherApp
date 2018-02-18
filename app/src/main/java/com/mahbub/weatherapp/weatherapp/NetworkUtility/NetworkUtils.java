package com.mahbub.weatherapp.weatherapp.NetworkUtility;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mahbub on 2/18/2018.
 */

public class NetworkUtils {
    public static final String BASE_WEATHER_URL = "api.org/data/2.5/forecast?q=München,DE";
    public static final String QUERY_WEATHER_URL = "query_url";
    public static final String WEATHER_API_ID = "9a36da9b098df077d94ed3f341eaac4f";

    // create method to return url
    public static URL getWeatherUrl(String queryUrl) {
        Uri buildUri = Uri.parse(BASE_WEATHER_URL).buildUpon()
                .appendQueryParameter(QUERY_WEATHER_URL, queryUrl)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static JSONObject getJsonDataFromHttpUrl(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("x-api-key",WEATHER_API_ID);
        JSONObject jsonData = null;

        try {
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer buffer = new StringBuffer(1024);
            String temp = "";
            while ((temp = bfReader.readLine()) != null) {
                buffer.append(temp).append("\n");
                bfReader.close();
            }

            jsonData = new JSONObject(buffer.toString());
            if (jsonData.getInt("cod") != 200) {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;

    }
}