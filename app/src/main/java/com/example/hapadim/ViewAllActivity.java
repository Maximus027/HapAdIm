package com.example.hapadim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hapadim.adapters.ViewAllAdapter;
import com.example.hapadim.models.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView mRvViewAll;
    private ViewAllAdapter mViewAllAdapter;
    private Bundle bundle;

    private static final String MOUNTAINS = "mountains";
    private static final String MONUMENTS = "monuments";
    private static final String LONGDISTANCES = "long distances";
    private static final String VIEWALL = "view all";
    private static final String ID = "id";

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

//        mRvViewAll = (RecyclerView) findViewById(R.id.rv_view_all);
//        mMountainAdapter = new MountainAdapter();
//
//
//        mRvViewAll.setAdapter(mMountainAdapter);
//        mRvViewAll.setHasFixedSize(true);
//        mRvViewAll.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
//                LinearLayoutManager.VERTICAL,
//                false));
//
//        SecondActivity secondActivity = new SecondActivity();
//        mMountainAdapter.giveAdapterValue(secondActivity.giveValueToAdapter(1, 10));

        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mRvViewAll = (RecyclerView) findViewById(R.id.rv_view_all);
        mViewAllAdapter = new ViewAllAdapter();

        bundle = getIntent().getExtras();
        name = bundle.getString("id");
        setupViewAllAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        ArrayList<Element> arrEl = new ArrayList<>();
        arrEl = elementArray(name);

        switch (item.getItemId()) {

            case R.id.item_ft_asc:
                Collections.sort(arrEl, new Comparator<Element>() {
                    @Override
                    public int compare(Element element1, Element element2) {
                        return element1.getElevation() - element2.getElevation();
                    }
                });
                break;
            case R.id.item_ft_desc:

                Collections.sort(arrEl, new Comparator<Element>() {
                    @Override
                    public int compare(Element element1, Element element2) {
                        return element2.getElevation() - element1.getElevation();
                    }
                });

                break;
            case R.id.item_name_asc:

                Collections.sort(arrEl, new Comparator<Element>() {
                    @Override
                    public int compare(Element element1, Element element2) {
                        return element1.getName().compareTo(element2.getName());
                    }
                });

                break;
            case R.id.item_name_desc:
                Collections.sort(arrEl, new Comparator<Element>() {
                    @Override
                    public int compare(Element element1, Element element2) {
                        return element2.getName().compareTo(element1.getName());
                    }
                });
                break;
        }

        mViewAllAdapter.giveAdapterValue(arrEl);

        return super.onOptionsItemSelected(item);
    }

    private void setupViewAllAdapter() {

        mRvViewAll.setAdapter(mViewAllAdapter);
        mRvViewAll.setHasFixedSize(true);
        mRvViewAll.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));
        mViewAllAdapter.giveAdapterValue(elementArray(name));
    }

    private ArrayList<Element> elementArray(String el) {

        ArrayList<Element> arrElement = new ArrayList<>();

        String[] mountainsNames = getResources().getStringArray(R.array.mountains);
        int[] elevation = getResources().getIntArray(R.array.mountainsElevation);
        int[] images = {R.drawable.meverest, R.drawable.mk2, R.drawable.mkangchenjunga, R.drawable.mnangaparbat, R.drawable.mlhotse, R.drawable.mmakalu, R.drawable.mmanaslu, R.drawable.mchooyu, R.drawable.mkilimanjaro, R.drawable.mdenali};

        String[] monumentsNames = getResources().getStringArray(R.array.monumentsNames);
        int[] monumentsHeights = getResources().getIntArray(R.array.monumentsHeights);
        int[] monumentsImages = {R.drawable.statueofliberty, R.drawable.eiffeltower, R.drawable.christtheredeemer, R.drawable.greatpyramidofgiza, R.drawable.tajmahal, R.drawable.stonehenge, R.drawable.acropolisofathens, R.drawable.greatwallofchina, R.drawable.burjkhalifa, R.drawable.colosseum};

        String[] longDistancesNames = getResources().getStringArray(R.array.longDistancesNames);
        int[] longDistancesLengths = getResources().getIntArray(R.array.longDistancesLengths);
        int[] longDistancesImages = {R.drawable.atacamacrossingchile, R.drawable.marathonofthemidnightsunnorway, R.drawable.greatethiopianrunethiopia, R.drawable.comradesmarathonsouthafrica, R.drawable.junglemarathonbrazil, R.drawable.twooceansmarathonsouthafrica, R.drawable.bagantemplemarathonmyanmar, R.drawable.icemarathonantarctica, R.drawable.tenzinhillaryeverestmarathonnepal, R.drawable.fujisanmarathonjapan};

        switch (el) {

            case MOUNTAINS:
                for (int i = 0; i < mountainsNames.length; i++) {
                    Element obj = new Element();
                    obj.setName(mountainsNames[i]);
                    obj.setElevation(elevation[i]);
                    obj.setImages(images[i]);
                    arrElement.add(obj);
                }
                break;
            case MONUMENTS:
                for (int i = 0; i < monumentsNames.length; i++) {
                    Element obj = new Element();
                    obj.setName(monumentsNames[i]);
                    obj.setElevation(monumentsHeights[i]);
                    obj.setImages(monumentsImages[i]);
                    arrElement.add(obj);
                }
                break;

            case LONGDISTANCES:
                for (int i = 0; i < longDistancesNames.length; i++) {
                    Element obj = new Element();
                    obj.setName(longDistancesNames[i]);
                    obj.setElevation(longDistancesLengths[i]);
                    obj.setImages(longDistancesImages[i]);
                    arrElement.add(obj);
                }
                break;

            case VIEWALL:
                ArrayList<String> viewAllNames = new ArrayList<String>(Arrays.asList(mountainsNames));
                viewAllNames.addAll(Arrays.asList(monumentsNames));
                viewAllNames.addAll(Arrays.asList(longDistancesNames));

                ArrayList<Integer> viewAllHeights = new ArrayList<>();
                for (int mElevation : elevation) viewAllHeights.add(mElevation);
                for (int mMonumentsHeight : monumentsHeights) viewAllHeights.add(mMonumentsHeight);
                for (int mLongDistancesLength : longDistancesLengths)
                    viewAllHeights.add(mLongDistancesLength);

                ArrayList<Integer> viewAllImages = new ArrayList<>();
                for (int mImages : images) viewAllImages.add(mImages);
                for (int mMonumentsImages : monumentsImages) viewAllImages.add(mMonumentsImages);
                for (int mLongDistancesImages : longDistancesImages)
                    viewAllImages.add(mLongDistancesImages);

                for (int i = 0; i < viewAllNames.size(); i++) {
                    Element obj = new Element();
                    obj.setName(viewAllNames.get(i));
                    obj.setElevation(viewAllHeights.get(i));
                    obj.setImages(viewAllImages.get(i));
                    arrElement.add(obj);
                }
                break;
        }
        return arrElement;
    }
}