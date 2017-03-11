package com.example.hapadim;

/**
 * Created by Nesada on 2/28/2017.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hapadim.adapters.LongDistancesAdapter;
import com.example.hapadim.adapters.MonumentsAdapter;
import com.example.hapadim.adapters.MountainAdapter;
import com.example.hapadim.models.Element;

import java.util.ArrayList;


public class LandingPageActivity extends AppCompatActivity {

    private RecyclerView mRvMountains, mRvMonuments, mRvLongDistances;
    private MountainAdapter mMountainAdapter;
    private MonumentsAdapter mMonumentsAdapter;
    private LongDistancesAdapter mLongDistancesAdapter;

    private static final String MOUNTAINS = "mountains";
    private static final String MONUMENTS = "monuments";
    private static final String LONGDISTANCES = "long distances";
    private static final String VIEWALL = "view all";
    private static final String ID = "id";
    private static final int SHOWITEM = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);
        setUpNotification();


        mRvMountains = (RecyclerView) findViewById(R.id.rv_mountains);
        mRvMonuments = (RecyclerView) findViewById(R.id.rv_monuments);
        mRvLongDistances = (RecyclerView) findViewById(R.id.rv_long_distances);

        mMountainAdapter = new MountainAdapter();
        mMonumentsAdapter = new MonumentsAdapter();
        mLongDistancesAdapter = new LongDistancesAdapter();

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

        mRvMountains.setAdapter(mMountainAdapter);
        mRvMountains.setHasFixedSize(true);
        mRvMountains.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mMountainAdapter.giveAdapterValue(elementArray(MOUNTAINS));
    }

    public void setUpMonumentsAdapter() {

        mRvMonuments.setAdapter(mMonumentsAdapter);
        mRvMonuments.setHasFixedSize(true);
        mRvMonuments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mMonumentsAdapter.giveAdapterValue(elementArray(MONUMENTS));
    }

    public void setUpLongDistancesAdapter() {

        mRvLongDistances.setAdapter(mLongDistancesAdapter);
        mRvLongDistances.setHasFixedSize(true);
        mRvLongDistances.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));

        mLongDistancesAdapter.giveAdapterValue(elementArray(LONGDISTANCES));
    }


    private ArrayList<Element> elementArray(String el) {
        ArrayList<Element> arrElement = new ArrayList<>();

        switch (el) {

            case MOUNTAINS:
                String[] mountainsNames = getResources().getStringArray(R.array.mountains);
                int[] elevation = getResources().getIntArray(R.array.mountainsElevation);
                int[] images = {R.drawable.meverest, R.drawable.mk2, R.drawable.mkangchenjunga, R.drawable.mnangaparbat, R.drawable.mlhotse, R.drawable.mmakalu, R.drawable.mmanaslu, R.drawable.mchooyu, R.drawable.mkilimanjaro, R.drawable.mdenali};


                for (int i = 0; i < SHOWITEM; i++) {
                    Element obj = new Element();
                    obj.setName(mountainsNames[i]);
                    obj.setElevation(elevation[i]);
                    obj.setImages(images[i]);
                    arrElement.add(obj);
                }
                break;
            case MONUMENTS:
                String[] monumentsNames = getResources().getStringArray(R.array.monumentsNames);
                int[] monumentsHeights = getResources().getIntArray(R.array.monumentsHeights);
                int[] monumentsImages = {R.drawable.statueofliberty, R.drawable.eiffeltower, R.drawable.christtheredeemer, R.drawable.greatpyramidofgiza, R.drawable.tajmahal, R.drawable.stonehenge, R.drawable.acropolisofathens, R.drawable.greatwallofchina, R.drawable.burjkhalifa, R.drawable.colosseum};

                for (int i = 0; i < SHOWITEM; i++) {
                    Element obj = new Element();
                    obj.setName(monumentsNames[i]);
                    obj.setElevation(monumentsHeights[i]);
                    obj.setImages(monumentsImages[i]);
                    arrElement.add(obj);
                }
                break;

            case LONGDISTANCES:
                String[] longDistancesNames = getResources().getStringArray(R.array.longDistancesNames);
                int[] longDistancesLengths = getResources().getIntArray(R.array.longDistancesLengths);
                int[] longDistancesImages = {R.drawable.atacamacrossingchile, R.drawable.marathonofthemidnightsunnorway, R.drawable.greatethiopianrunethiopia, R.drawable.comradesmarathonsouthafrica, R.drawable.junglemarathonbrazil, R.drawable.twooceansmarathonsouthafrica, R.drawable.bagantemplemarathonmyanmar, R.drawable.icemarathonantarctica, R.drawable.tenzinhillaryeverestmarathonnepal, R.drawable.fujisanmarathonjapan};

                for (int i = 0; i < SHOWITEM; i++) {
                    Element obj = new Element();
                    obj.setName(longDistancesNames[i]);
                    obj.setElevation(longDistancesLengths[i]);
                    obj.setImages(longDistancesImages[i]);
                    arrElement.add(obj);
                }
                break;
        }


        return arrElement;
    }

    public void tvClick(View view) {
        Intent viewAll = new Intent(this, ViewAllActivity.class);

        switch (view.getId()) {
            case R.id.tv_mountains:
                viewAll.putExtra(ID, MOUNTAINS);
                break;
            case R.id.tv_monuments:
                viewAll.putExtra(ID, MONUMENTS);
                break;
            case R.id.tv_long_distances:
                viewAll.putExtra(ID, LONGDISTANCES);
                break;
        }

        this.startActivity(viewAll);
    }

    public void btnViewAllClick(View view) {
        Intent viewAll = new Intent(this, ViewAllActivity.class);
        viewAll.putExtra(ID, VIEWALL);
        this.startActivity(viewAll);
    }
}