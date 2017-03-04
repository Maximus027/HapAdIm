package com.example.hapadim;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hapadim.database.DBOperations;
import com.example.hapadim.database.Places;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getPreferences(0);

        if (!isRealmInit()) {

            Realm.init(getApplicationContext());
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
            Realm realm = Realm.getDefaultInstance();
            DBOperations dbOperations = DBOperations.getInstance();

            dbOperations.insertPlace(realm, 1, "monuments", "Statue of Liberty ", 354, "Short", "https://www.youtube.com/watch?v=HDe0WErg5UQ", null, null);
            Places place = realm.where(Places.class).equalTo("placeId", 1).findFirst();

            Log.d("MAIN ACTIVITY", place.getPlaceName());
            realm.close();
            preferences.edit().putBoolean("realm_init", true).apply();
        }
    }

    private boolean isRealmInit() {
        SharedPreferences preferences = getPreferences(0);
        return preferences.getBoolean("realm_init", false);
    }


}