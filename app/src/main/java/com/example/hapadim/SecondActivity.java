package com.example.hapadim;

/**
 * Created by Nesada on 2/28/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hapadim.adapters.MountainAdapter;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRvMountains, mRvMonuments, mRvLongDistances;
    private MountainAdapter mMountainAdapter, mMountainAdapter2, mMountainAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

        mRvMountains = (RecyclerView) findViewById(R.id.rv_mountains);
        mRvMonuments = (RecyclerView) findViewById(R.id.rv_monuments);
        mRvLongDistances = (RecyclerView) findViewById(R.id.rv_long_distances);


        mMountainAdapter = new MountainAdapter();
        mMountainAdapter2 = new MountainAdapter();
        mMountainAdapter3 = new MountainAdapter();

        setUpAdapter();
        setUpAdapter2();
        setUpAdapter3();
        mMountainAdapter.giveAdapterValue(giveValueToAdapter(1, 100));

    }

    public void setUpAdapter() {

        mRvMountains.setAdapter(mMountainAdapter);
        mRvMountains.setHasFixedSize(true);
        mRvMountains.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }

    public void setUpAdapter2() {

        mRvMonuments.setAdapter(mMountainAdapter);
        mRvMonuments.setHasFixedSize(true);
        mRvMonuments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }

    public void setUpAdapter3() {

        mRvLongDistances.setAdapter(mMountainAdapter);
        mRvLongDistances.setHasFixedSize(true);
        mRvLongDistances.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }

    public ArrayList<Integer> giveValueToAdapter(int start, int end){

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=start; i<end; i++){
            arr.add(i);
        }
        return arr;
    }
}
