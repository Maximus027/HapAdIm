package com.example.hapadim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.adapters.StartPageAdapter;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import static com.example.hapadim.R.id.locationFacts;

/**
 * Created by meltemyildirim on 3/13/17.
 */

public class StartPageActivity extends AppCompatActivity {
    Intent startPage;
    StartPageAdapter startPageAdapter;
    StartPageActivity startPageActivity;
    ViewPager viewPager;
    TextView locationName;
    TextView locationDesciption;
    ImageView catergoryIcon;
    TextView stepNum;
    Intent pageIntent;


    private final static String TAG = StartPageActivity.class.getName();
    private final static String TAG3 = "Location";
    private final static String TAG4 = "Catergory";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        locationName = (TextView) findViewById(R.id.locationName);
        catergoryIcon = (ImageView) findViewById(R.id.catergoryIcon);
        locationDesciption = (TextView) findViewById(locationFacts);
        stepNum = (TextView) findViewById(R.id.numberOfSteps);
        pageIntent = getIntent();
        startPageAdapter = new StartPageAdapter(getApplicationContext(), getIntent());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(startPageAdapter);


        Place example = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place"));
//        List<Badge> badges = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place_badges"));

        Log.d(TAG, "onCreate: chosenName: " + example.getPlaceName());
        Log.d(TAG, "onCreate: firstBadge " + example.getBadges().get(0).getBadgedName());

        String placename = example.getPlaceName();
        String catergory = example.getCategory();
        if (catergory.equals("Mountain")) {
            Picasso.with(this).load(R.drawable.mountainicon).into(catergoryIcon);
        } else if (catergory.equals("Monument")) {
            Picasso.with(this).load(R.drawable.monumenticon).into(catergoryIcon);
        } else {
            Picasso.with(this).load(R.drawable.walkicon).into(catergoryIcon);
        }
        String locationFacts = example.getDescription();
        locationName.setText(placename);
        locationDesciption.setText(locationFacts);


    }
}
