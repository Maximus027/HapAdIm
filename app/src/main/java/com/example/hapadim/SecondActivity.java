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

import com.example.hapadim.adapters.MountainAdapter;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRvMountains, mRvMonuments, mRvLongDistances;
    private MountainAdapter mMountainAdapter, mMonumentsAdapter, mLongDistancesAdapter;
    private TextView mTvMountains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        mRvMountains = (RecyclerView) findViewById(R.id.rv_mountains);
        mRvMonuments = (RecyclerView) findViewById(R.id.rv_monuments);
        mRvLongDistances = (RecyclerView) findViewById(R.id.rv_long_distances);
        mTvMountains = (TextView)findViewById(R.id.tv_mountains);


        mMountainAdapter = new MountainAdapter();
        mMonumentsAdapter = new MountainAdapter();
        mLongDistancesAdapter = new MountainAdapter();

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
        mMountainAdapter.giveAdapterValue(giveValueToAdapter(1, 7));
    }

    public void setUpMonumentsAdapter() {

        mRvMonuments.setAdapter(mMonumentsAdapter);
        mRvMonuments.setHasFixedSize(true);
        mRvMonuments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mMonumentsAdapter.giveAdapterValue(giveValueToAdapter(1, 4));
    }

    public void setUpLongDistanceAdapter() {

        mRvLongDistances.setAdapter(mLongDistancesAdapter);
        mRvLongDistances.setHasFixedSize(true);
        mRvLongDistances.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mLongDistancesAdapter.giveAdapterValue(giveValueToAdapter(1, 6));
    }

    public ArrayList<Integer> giveValueToAdapter(int start, int end){

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=start; i<end; i++){
            arr.add(i);
        }
        return arr;
    }

    public void tvClick(View view){
        Intent viewAll = new Intent(this, ViewAllActivity.class);
           this.startActivity(viewAll);
    }
}
