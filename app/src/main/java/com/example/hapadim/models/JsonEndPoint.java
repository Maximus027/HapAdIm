package com.example.hapadim.models;

import android.content.Context;
import android.util.Log;

import com.example.hapadim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

/**
 * Created by meltemyildirim on 3/8/17.
 */

public class JsonEndPoint {

    private ArrayList<Place> mountains;
    private ArrayList<Place> monuments;
    private ArrayList<Place> longDistance;
    public JSONArray badges;

    public String readFromJsonFile(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.places_json);
        StringBuffer sbJsonString = new StringBuffer();
        int character;

        try {
            while ((character = is.read()) != -1) {

                sbJsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbJsonString.toString();
    }

    public ArrayList<Place> getMountains() {
        return mountains;
    }

    public ArrayList<Place> getMonuments() {
        return monuments;
    }

    public ArrayList<Place> getLongDistance() {
        return longDistance;
    }

    public void populateLocations(String jsonObject) {
        Log.d(TAG, "THIS IS OBJECT: " + jsonObject);
        mountains = new ArrayList<>();
        monuments = new ArrayList<>();
        longDistance = new ArrayList<>();

        try {
            Log.d(TAG, "YOU HAVE ENTERED TRY ");
            JSONArray jsonArray = new JSONArray(jsonObject);
            Log.d(TAG, "populateLocations: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                Place place = new Place();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                place.setCategory(jsonObject1.getString("category"));
                place.setPlaceName(jsonObject1.getString("placeName"));
                place.setUrlIMG(jsonObject1.getString("urlImg"));
                place.setStepNumber(jsonObject1.getLong("stepNumber"));
                place.setUrlVR(jsonObject1.getString("urlVR"));
                for (int j = 0; j < badges.length(); j++) {
                    badges = jsonObject1.getJSONArray("badges");
                    JSONObject getBadge = badges.getJSONObject(i);
                    Badge badge = new Badge();
                    badge.setBadgeDesc(getBadge.getString("badgeDesc"));
                    badge.setBadgedName(getBadge.getString("badgedName"));
                    badge.setBadgeImg(getBadge.getString("badgeImg"));
                }
                if (place.getCategory().equals("Mountains")) {
                    mountains.add(place);
                } else if (place.getCategory().equals("Monuments")) {
                    monuments.add(place);
                } else {
                    longDistance.add(place);

                }
            }
        } catch (JSONException e) {
            Log.d(TAG, "YOU HAVE ENTERED CATCH"+ Arrays.toString(e.getStackTrace()));
        }


    }


}
