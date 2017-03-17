package com.example.hapadim;


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
import com.example.hapadim.models.JsonEndPoint;

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
        drawerRV = (RecyclerView)findViewById(R.id.rv_drawer);

        endPoint = JsonEndPoint.getInstance();

        Log.d("json", "monumentsAdapter size b4: " + endPoint.getMonuments().size());
        Log.d("json", "long dist size b4: " + endPoint.getLongDistance().size());
        Log.d("json", "mountains size b4: " + endPoint.getMountains().size());

        mountainsAdapater = new LandMarksAdapter(endPoint.getMountains());
        monumentsAdapter = new LandMarksAdapter(endPoint.getMonuments());
        longDistAdapter = new LandMarksAdapter(endPoint.getLongDistance());
        drawerAdapter= new DrawerAdapter(endPoint.getMonuments()); // need to be changed the entry data - this is only for testing purpose

        setUpMountainsAdapter();
        setUpMonumentsAdapter();
        setUpLongDistancesAdapter();
        setUpDrawerAdapter();
        toolbarTransparent();

    }
    private void toolbarTransparent(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setUpDrawerAdapter() {
        drawerRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        drawerRV.setAdapter(drawerAdapter);
        drawerAdapter.notifyDataSetChanged();
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
    }
}