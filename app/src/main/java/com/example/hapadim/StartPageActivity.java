package com.example.hapadim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.adapters.StartPageAdapter;
import com.example.hapadim.models.Badge;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

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
    TextView stepNum;
    ImageView catergoryIcon;
    ImageView panoImage;
    Button startBTN;


    private final static String TAG = StartPageActivity.class.getName();
    private final static String TAG3 = "Location";
    private final static String TAG4 = "Catergory";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        locationName = (TextView) findViewById(R.id.locationName);
        catergoryIcon = (ImageView) findViewById(R.id.catergoryIcon);
        panoImage = (ImageView) findViewById(R.id.pano_view);
        locationDesciption = (TextView) findViewById(R.id.locationStartPageFactsInfo);
        stepNum = (TextView) findViewById(R.id.numberOfSteps);
        startBTN = (Button) findViewById(R.id.startButton);
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InProgressActivity.class);
                startActivity(intent);
            }
        });

        Place example = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place"));
        List<Badge> badges = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place_badges"));

        initializeAdapter(example);
        setLocationInformation(example);


    }

    private void initializeAdapter(Place example) {
        String[] array = {example.getUrlImg(), example.getUrlImg2(), example.getUrlImg3()};
        startPageAdapter = new StartPageAdapter(getApplicationContext(), array);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(startPageAdapter);
    }

    private void setLocationInformation(Place example) {
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
        int stepNumber = example.getStepNumber();
        stepNum.setText(String.valueOf(stepNumber));
        locationName.setText(placename);
        locationDesciption.setText(locationFacts);
        Picasso.with(getApplicationContext()).load(example.getPanoImg()).into(panoImage);
    }
}
