package com.example.hapadim;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hapadim.adapters.DrawerAdapter;
import com.example.hapadim.adapters.LandMarksAdapter;
import com.example.hapadim.complexsharedprefs.ComplexPreferences;
import com.example.hapadim.models.Badge;
import com.example.hapadim.models.JsonEndPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class LandingPageActivity extends AppCompatActivity {


    private RecyclerView mountainsRV, monumentsRV, longDistancesRV, drawerRV;
    private JsonEndPoint endPoint;

    private static final String VIEWALL = "view all";
    private static final String CATEGORY_KEY = "category_key";

    private LandMarksAdapter mountainsAdapater;
    private LandMarksAdapter monumentsAdapter;
    private LandMarksAdapter longDistAdapter;
    private DrawerAdapter drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JsonEndPoint.getInstance().populateLocations(this);

        setContentView(R.layout.landingpage);
        setUpNotification();

        mountainsRV = (RecyclerView) findViewById(R.id.rv_mountains);
        monumentsRV = (RecyclerView) findViewById(R.id.rv_monuments);
        longDistancesRV = (RecyclerView) findViewById(R.id.rv_long_distances);
        drawerRV = (RecyclerView) findViewById(R.id.rv_drawer);

        endPoint = JsonEndPoint.getInstance();

        Log.d("json", "monumentsAdapter size b4: " + endPoint.getMonuments().size());
        Log.d("json", "long dist size b4: " + endPoint.getLongDistance().size());
        Log.d("json", "mountains size b4: " + endPoint.getMountains().size());

        Activity activity = (Activity)this;

        mountainsAdapater = new LandMarksAdapter(endPoint.getMountains(), activity);
        monumentsAdapter = new LandMarksAdapter(endPoint.getMonuments(), activity);
        longDistAdapter = new LandMarksAdapter(endPoint.getLongDistance(),activity);

        List<Badge> badgesEarnedByUser = getEarnedBadges();

        mountainsAdapater = new LandMarksAdapter(endPoint.getMountains(), activity);
        monumentsAdapter = new LandMarksAdapter(endPoint.getMonuments(), activity);
        longDistAdapter = new LandMarksAdapter(endPoint.getLongDistance(), activity);
        // Need to be changed the entry data - this is only for testing purpose

        setUpMountainsAdapter();
        setUpMonumentsAdapter();
        setUpLongDistancesAdapter();
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

    private void setUpDrawerAdapter() {
        drawerRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        List<Badge> demoBadges = new ArrayList<>();
        demoBadges.add(endPoint.getMonuments().get(0).getBadges().get(0));
        demoBadges.add(endPoint.getMonuments().get(0).getBadges().get(1));
        drawerAdapter = new DrawerAdapter(demoBadges);
        drawerRV.setAdapter(drawerAdapter);
//        drawerAdapter.notifyDataSetChanged();
    }


    private void setUpNotification() {
        Resources resources = getResources();
        NotificationBuilder notificationBuilder =
                new NotificationBuilder(R.drawable.unlockicon,
                        resources.getString(R.string.notiAchievementTitles),
                        resources.getString(R.string.everest1000));
        notificationBuilder.makeNotification(getApplicationContext());
    }


    public void setUpMountainsAdapter() {
        mountainsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mountainsRV.setAdapter(mountainsAdapater);
        mountainsAdapater.notifyDataSetChanged();
        Log.d("Adapters Mountains: ", endPoint.getMountains().size() + "");
    }

    public void setUpMonumentsAdapter() {
        monumentsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        monumentsRV.setAdapter(monumentsAdapter);
        monumentsAdapter.notifyDataSetChanged();
        Log.e("Adapters Monuments: ", endPoint.getMountains().size() + "");
    }

    public void setUpLongDistancesAdapter() {
        longDistancesRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        longDistancesRV.setAdapter(longDistAdapter);
        longDistAdapter.notifyDataSetChanged();
        Log.e("Adapters Long Dist: ", endPoint.getLongDistance().size() + "");
    }

    public void btnViewAllClick(View view) {
        Intent viewAll = new Intent(this, ViewAllActivity.class);
        viewAll.putExtra(CATEGORY_KEY, VIEWALL);
        this.startActivity(viewAll);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public List<Badge> getEarnedBadges() {
        ComplexPreferences complexPreferences = ComplexPreferences
                .getComplexPreferences(this, Constants.SHARED_PREFS_KEY, MODE_PRIVATE);
        ListComplexBadge complexObject = complexPreferences
                .getObject(Constants.SHARED_PREFS_BADGES_KEY, ListComplexBadge.class);

        List<Badge> userEarnedBadges = new ArrayList<>();
        if (complexObject != null) {
            for (Badge item : complexObject.getBadges()) {
                userEarnedBadges.add(item);
            }
        }
        return userEarnedBadges;
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpDrawerAdapter();
//        List<Badge> badges = getEarnedBadges();
//        drawerAdapter.setNewData(badges);
    }
}