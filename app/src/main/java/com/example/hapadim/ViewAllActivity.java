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
import com.example.hapadim.models.JsonEndPoint;
import com.example.hapadim.models.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView viewAllRV;
    private ViewAllAdapter viewAllAdapter;
    private Bundle bundle;
    String category;
    private List<Place> allPlaces;

    private List<Place> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Place> ld = JsonEndPoint.getInstance().getLongDistance();
        List<Place> monuments = JsonEndPoint.getInstance().getMonuments();
        List<Place> mountains = JsonEndPoint.getInstance().getMountains();


        allPlaces = new ArrayList<>();
        allPlaces.addAll(ld);
        allPlaces.addAll(monuments);
        allPlaces.addAll(mountains);

        viewAllRV = (RecyclerView) findViewById(R.id.rv_view_all);
        bundle = getIntent().getExtras();
        category = bundle.getString("category_key");

        if (category.equals("Monument")) {
            selectedList = monuments;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else if (category.equals("LongDistance")) {

            selectedList = ld;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else if (category.equals("Mountain")) {

            selectedList = monuments;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else {

            selectedList = allPlaces;
            viewAllAdapter = new ViewAllAdapter(selectedList);
        }

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

        List<Place> arrEl = getSelectedList();


        switch (item.getItemId()) {

            case R.id.item_ft_asc:
                Collections.sort(arrEl, new Comparator<Place>() {
                    @Override
                    public int compare(Place element1, Place element2) {
                        return element1.getStepNumber() - element2.getStepNumber();
                    }
                });
                break;
            case R.id.item_ft_desc:

                Collections.sort(arrEl, new Comparator<Place>() {
                    @Override
                    public int compare(Place element1, Place element2) {
                        return element2.getStepNumber() - element1.getStepNumber();
                    }
                });

                break;
            case R.id.item_name_asc:

                Collections.sort(arrEl, new Comparator<Place>() {
                    @Override
                    public int compare(Place element1, Place element2) {
                        return element1.getPlaceName().compareTo(element2.getPlaceName());
                    }
                });

                break;
            case R.id.item_name_desc:
                Collections.sort(arrEl, new Comparator<Place>() {
                    @Override
                    public int compare(Place element1, Place element2) {
                        return element2.getPlaceName().compareTo(element1.getPlaceName());
                    }
                });
                break;
        }
        viewAllAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void setupViewAllAdapter() {
        viewAllRV.setAdapter(viewAllAdapter);
        viewAllRV.setHasFixedSize(true);
        viewAllRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public List<Place> getSelectedList() {
        return selectedList;
    }
}