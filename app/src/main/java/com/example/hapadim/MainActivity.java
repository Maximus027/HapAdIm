package com.example.hapadim;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hapadim.database.Badge;
import com.example.hapadim.database.DBOperations;
import com.example.hapadim.database.Places;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getPreferences(0);
        setUpRealm();


    }

    private void setUpRealm() {

        if (!isRealmInit()) {

            Realm.init(getApplicationContext());

            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm realm = Realm.getDefaultInstance();// getInstance(config);
            DBOperations dbOperations = DBOperations.getInstance();

            Badge badgeSol1 = new Badge();
            badgeSol1.setName("Journey To Freedom Badge");
            badgeSol1.setDescription("blabla");
            badgeSol1.setBadgeImg(1);

            Badge badgeSol2 = new Badge();
            badgeSol2.setName(" Victory Statue Badg");
            badgeSol2.setDescription("blabla");
            badgeSol2.setBadgeImg(2);

            RealmList<Badge> badgesOfSOF = new RealmList<>();
            badgesOfSOF.add(badgeSol1);
            badgesOfSOF.add(badgeSol2);

            dbOperations.insertPlace(realm, 1, "monuments", "Statue of Liberty ", 354, "Short", "https://www.youtube.com/watch?v=HDe0WErg5UQ", null, badgesOfSOF);
            Places place = realm.where(Places.class).equalTo("placeId", 1).findFirst();
            Log.d("MAIN ACTIVITY", place.getPlaceName().concat(place.getCategory().concat(place.getDistance().concat(String.valueOf(place.getStepNumber()).concat(place.getBadges().toString())))));

            realm.close();
            preferences.edit().putBoolean("realm_init", true).apply();
        }

    }

    private boolean isRealmInit() {
        return preferences.getBoolean("realm_init", false);
    }


}