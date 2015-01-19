package org.example.jt.realapp2.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jt on 1/19/15.
 */
public class ParseWeatherJSON extends ParseJSON {

    private ArrayList<Weather> mWeather;
    private JSONObject mJSONObject;

    public ParseWeatherJSON(JSONObject mJSONObject) {
        super(mJSONObject);
        mWeather = new ArrayList<>();
        processJSON();
    }

    protected boolean processJSON(){
        boolean operationStatus = false;

        final String NAME = "name";
        final String WEATHER = "weather";
        final String MAIN = "main";
        final String DESCRIPTION = "description";

        try{
            String city = mJsonObject.getString(NAME);
            JSONArray weatherArray = mJsonObject.getJSONArray(WEATHER);
            int JSONArraylength = weatherArray.length();
            for(int i=0; i < JSONArraylength; i++){
                JSONObject weatherJSONObject = weatherArray.getJSONObject(i);
                String main = weatherJSONObject.getString(MAIN);
                String description = weatherJSONObject.getString(DESCRIPTION);

                Weather weatherObject = new Weather(city, main, description);
                this.mWeather.add(weatherObject);
                Log.d("DEBUG", this.mWeather.toString());
                operationStatus = true;
            }

        }catch (JSONException e){
            Log.e("Exception", e.toString());
            operationStatus = false;
        }
        return operationStatus;
    }

    public ArrayList<Weather> getMWeather(){
        return mWeather;
    }

}
