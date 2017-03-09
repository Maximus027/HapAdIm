package com.example.hapadim.models;

import android.content.Context;
import com.example.hapadim.R;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by meltemyildirim on 3/8/17.
 */

public class JsonEndPoint {

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



}
