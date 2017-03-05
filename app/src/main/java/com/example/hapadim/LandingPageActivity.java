package com.example.hapadim;

/**
 * Created by Nesada on 2/28/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    private TextView mTvMountains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

        mRvMountains = (RecyclerView) findViewById(R.id.rv_mountains);
        mRvMonuments = (RecyclerView) findViewById(R.id.rv_monuments);
        mRvLongDistances = (RecyclerView) findViewById(R.id.rv_long_distances);
        mTvMountains = (TextView)findViewById(R.id.tv_mountains);


        mMountainAdapter = new MountainAdapter();
        mMonumentsAdapter = new MonumentsAdapter();
        mLongDistancesAdapter = new LongDistancesAdapter();

        setUpMountainsAdapter();
        setUpMonumentsAdapter();
        setUpLongDistanceAdapter();

    }

    public void setUpMountainsAdapter() {

        mRvMountains.setAdapter(mMountainAdapter);
        mRvMountains.setHasFixedSize(true);
        mRvMountains.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mMountainAdapter.giveAdapterValue(elementArray("mountains"));
    }

    public void setUpMonumentsAdapter() {

        mRvMonuments.setAdapter(mMonumentsAdapter);
        mRvMonuments.setHasFixedSize(true);
        mRvMonuments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mMonumentsAdapter.giveAdapterValue(giveValueToAdapter(1,4));
    }

    public void setUpLongDistanceAdapter() {

        mRvLongDistances.setAdapter(mLongDistancesAdapter);
        mRvLongDistances.setHasFixedSize(true);
        mRvLongDistances.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mLongDistancesAdapter.giveAdapterValue(giveValueToAdapter(1, 4));
    }

    private ArrayList<Integer> giveValueToAdapter(int start, int end){

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=start; i<end; i++){
            arr.add(i);
        }
        return arr;
    }
private ArrayList<Element> elementArray(String element){
    ArrayList<Element>arrElement = new ArrayList<>();
    if (element.equals("mountains")) {
        String[] mountainsNames = getResources().getStringArray(R.array.mountains);
        int[] elevation = getResources().getIntArray(R.array.mountainsElevation);
        int[] images = {R.drawable.meverest, R.drawable.mk2, R.drawable.mkangchenjunga, R.drawable.mnangaparbat, R.drawable.mlhotse, R.drawable.mmakalu, R.drawable.mmanaslu, R.drawable.mchooyu, R.drawable.mkilimanjaro, R.drawable.mdenali};


        for (int i = 0; i < 3; i++) {
            Element obj = new Element();
            obj.setName(mountainsNames[i]);
            obj.setElevation(elevation[i]);
            obj.setImages(images[i]);
            arrElement.add(obj);
        }
    }
    return arrElement;
}

    public void tvClick(View view){
        Intent viewAll = new Intent(this, ViewAllActivity.class);

        switch (view.getId()){
            case R.id.tv_mountains:
                viewAll.putExtra("id", "mountains");
                break;
            case R.id.tv_monuments:
                viewAll.putExtra("id", "monuments");
                break;
            case R.id.tv_long_distances:
                viewAll.putExtra("id", "long distances");
                break;
        }

        this.startActivity(viewAll);
    }

    public void btnViewAllClick(View view) {
//        Intent viewAll = new Intent(this, ViewAllActivity.class);
//        viewAll.putExtra("id", "view all");
//        this.startActivity(viewAll);
    }
}
