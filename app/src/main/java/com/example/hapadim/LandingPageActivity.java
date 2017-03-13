package com.example.hapadim;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hapadim.adapters.LandMarksAdapter;
import com.example.hapadim.models.JsonEndPoint;

/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class LandingPageActivity extends AppCompatActivity {


    private RecyclerView mountainsRV, monumentsRV, longDistancesRV;
    private JsonEndPoint endPoint;

    private static final String VIEWALL = "view all";
    private static final String CATEGORY_KEY = "category_key";

    private LandMarksAdapter mountainsAdapater;
    private LandMarksAdapter monuments;
    private LandMarksAdapter longDist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JsonEndPoint.getInstance().populateLocations(this);

        setContentView(R.layout.landingpage);
        setUpNotification();

        mountainsRV = (RecyclerView) findViewById(R.id.rv_mountains);
        monumentsRV = (RecyclerView) findViewById(R.id.rv_monuments);
        longDistancesRV = (RecyclerView) findViewById(R.id.rv_long_distances);

        endPoint = JsonEndPoint.getInstance();

        Log.d("json", "monuments size b4: " + endPoint.getMonuments().size());
        Log.d("json", "long dist size b4: " + endPoint.getLongDistance().size());
        Log.d("json", "mountains size b4: " + endPoint.getMountains().size());

        mountainsAdapater = new LandMarksAdapter(endPoint.getMountains());
        monuments = new LandMarksAdapter(endPoint.getMonuments());
        longDist = new LandMarksAdapter(endPoint.getLongDistance());

        setUpMountainsAdapter();
        setUpMonumentsAdapter();
        setUpLongDistancesAdapter();
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
        monumentsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        monumentsRV.setAdapter(monuments);
        monuments.notifyDataSetChanged();
        Log.e("Adapters Monuments: ", endPoint.getMountains().size() + "");
    }

    public void setUpLongDistancesAdapter() {
        longDistancesRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        longDistancesRV.setAdapter(longDist);
        longDist.notifyDataSetChanged();
        Log.e("Adapters Long Dist: ", endPoint.getLongDistance().size() + "");
    }

    public void btnViewAllClick(View view) {
        Intent viewAll = new Intent(this, ViewAllActivity.class);
        viewAll.putExtra(CATEGORY_KEY, VIEWALL);
        this.startActivity(viewAll);
    }
}