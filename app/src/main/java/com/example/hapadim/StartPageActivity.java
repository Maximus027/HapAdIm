package com.example.hapadim;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    RelativeLayout relativeLayout;
    StartPageActivity startPageActivity;
    LinearLayout dotsLayout;
    ViewPager viewPager;
    TextView locationName;
    TextView locationDesciption;
    TextView stepNum;
    ImageView catergoryIcon;
    ImageView panoImage;
    Button startBTN;
    CardView cardView;
    CardView cardView2;
    TextView[] dots;
    int[] layouts;


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
        locationDesciption = (TextView) findViewById(R.id.locationStartPageFactsInfo);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        relativeLayout = (RelativeLayout) findViewById(R.id.startLayout);
        stepNum = (TextView) findViewById(R.id.numberOfSteps);
        cardView = (CardView) findViewById(R.id.locationCard);
        cardView.setElevation(30);
        cardView2 = (CardView) findViewById(R.id.locationFactsCard);
        cardView2.setElevation(30);

        final Place example = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place"));
        List<Badge> badges = Parcels.unwrap(getIntent().getParcelableExtra("chosen_place_badges"));

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcelable placeParcel = Parcels.wrap(example);
                Intent intent = new Intent(getApplicationContext(), InProgressActivity.class);
                intent.putExtra("chosen_place", placeParcel);
                startActivity(intent);
            }
        });
        initializeAdapter(example);
        setLocationInformation(example);

        toolbarTransparent();

    }

    private void toolbarTransparent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initializeAdapter(Place example) {
        String[] array = {example.getUrlImg(), example.getUrlImg2(), example.getUrlImg3()};
        startPageAdapter = new StartPageAdapter(getApplicationContext(), array);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(startPageAdapter);
    }

    private void setLocationInformation(Place example) {
        int mountback = R.color.layoutmountBack;
        int longback = R.color.layoutlongBack;
        int monoback = R.color.layoutmonoBack;
        String placename = example.getPlaceName();
        String catergory = example.getCategory();
        if (catergory.equals("Mountain")) {
            Picasso.with(this).load(R.drawable.mountainicon).into(catergoryIcon);
            relativeLayout.setBackgroundColor(getResources().getColor(mountback));

        } else if (catergory.equals("Monument")) {
            Picasso.with(this).load(R.drawable.monumenticon).into(catergoryIcon);
            relativeLayout.setBackgroundColor(getResources().getColor(monoback));
        } else {
            relativeLayout.setBackgroundColor(getResources().getColor(longback));
            Picasso.with(this).load(R.drawable.walkicon).into(catergoryIcon);
        }

        String locationFacts = example.getDescription();
        int stepNumber = example.getStepNumber();
        stepNum.setText(String.valueOf(stepNumber));
        locationName.setText(placename);
        locationDesciption.setText("Location Facts : " + locationFacts);
        Picasso.with(getApplicationContext()).load(example.getPanoImg()).into(panoImage);

        addBottomDots(viewPager.getCurrentItem());
    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[3];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
}
