package com.example.hapadim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hapadim.models.Place;

import org.parceler.Parcels;

/**
 * Created by meltemyildirim on 3/13/17.
 */

public class StartPageActivity extends AppCompatActivity {

    private final static String TAG = StartPageActivity.class.getName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        Place example = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place"));
//        List<Badge> badges = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place_badges"));

        Log.d(TAG, "onCreate: chosenName: "+ example.getPlaceName());
        Log.d(TAG, "onCreate: firstBadge " + example.getBadges().get(0).getBadgedName());

    }
}
