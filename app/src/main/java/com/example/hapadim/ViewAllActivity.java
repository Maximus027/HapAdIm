package com.example.hapadim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hapadim.adapters.MountainAdapter;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView mRvViewAll;
    private MountainAdapter mMountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        mRvViewAll = (RecyclerView)findViewById(R.id.rv_view_all);
        mMountainAdapter = new MountainAdapter();


        mRvViewAll.setAdapter(mMountainAdapter);
        mRvViewAll.setHasFixedSize(true);
        mRvViewAll.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        SecondActivity secondActivity = new SecondActivity();
        mMountainAdapter.giveAdapterValue(secondActivity.giveValueToAdapter(1,10));

    }
}
