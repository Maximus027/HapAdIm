package com.example.hapadim.models;

import android.content.Context;
import android.util.Log;

import com.example.hapadim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meltemyildirim on 3/8/17.
 */

public class JsonEndPoint {

    private ArrayList<Place> mountains;
    private ArrayList<Place> monuments;
    private ArrayList<Place> longDistance;
    public JSONArray badges;
    private final static String TAG = "json parser";
    private PopulatedListListener listener;

    private static JsonEndPoint instance;

    public static JsonEndPoint getInstance() {
        if (instance == null) {
            instance = new JsonEndPoint();
        }
        return instance;
    }

    private JsonEndPoint() {
        mountains = new ArrayList<>();
        monuments = new ArrayList<>();
        longDistance = new ArrayList<>();
    }

    public void setListener(PopulatedListListener listener) {
        this.listener = listener;
    }

    private String readFromJsonFile(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.places);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String result = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void populateLocations(Context context) {
        String jsonObject = readFromJsonFile(context);
        Log.d(TAG, "THIS IS OBJECT: " + jsonObject);
        try {
            Log.d(TAG, "YOU HAVE ENTERED TRY ");
            JSONArray jsonArray = new JSONArray(jsonObject);
            Log.d(TAG, "populateLocations: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                Place place = new Place();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                place.setCategory(jsonObject1.getString("category"));
                place.setPlaceName(jsonObject1.getString("placeName"));
                place.setUrlImg(jsonObject1.getString("urlImg"));
                place.setStepNumber(jsonObject1.getInt("stepNumber"));
                place.setUrlVR(jsonObject1.getString("urlVR"));
                badges = jsonObject1.getJSONArray("badges");

                for (int j = 0; j < badges.length(); j++) {
                    JSONObject getBadge = badges.getJSONObject(j);
                    Badge badge = new Badge();
                    badge.setBadgeDesc(getBadge.getString("badgeDesc"));
                    badge.setBadgedName(getBadge.getString("badgedName"));
                    badge.setBadgeImg(getBadge.getString("badgeImg"));
                }

                if (place.getCategory().equals("Mountain")) {
                    mountains.add(place);
                } else if (place.getCategory().equals("Monument")) {
                    monuments.add(place);
                } else if (place.getCategory().equals("LongDistance")){
                    longDistance.add(place);
                }
            }

            //listener.lists_are_populated();
        } catch (JSONException e) {
            Log.e(TAG, "YOU HAVE ENTERED CATCH" + e);
        }
    }


    public List<Place> getMountains() {
        return mountains;
    }

    public List<Place> getMonuments() {
        return monuments;
    }

    public List<Place> getLongDistance() {
        return longDistance;
    }

    public interface PopulatedListListener {
        void lists_are_populated();
    }

}
