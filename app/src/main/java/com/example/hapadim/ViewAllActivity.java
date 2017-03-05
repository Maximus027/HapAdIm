package com.example.hapadim;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hapadim.adapters.ViewAllAdapter;
import com.example.hapadim.models.Element;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView mRvViewAll;
    private ViewAllAdapter mViewAllAdapter;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        mRvViewAll = (RecyclerView) findViewById(R.id.rv_view_all);
        mViewAllAdapter = new ViewAllAdapter();

        bundle = getIntent().getExtras();
        setupViewAllAdapter();
    }

    private void setupViewAllAdapter() {
        String name = bundle.getString("id");

        mRvViewAll.setAdapter(mViewAllAdapter);
        mRvViewAll.setHasFixedSize(true);
        mRvViewAll.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        switch (name) {
            case "mountains":
                mViewAllAdapter.giveAdapterValue(elementArray(name));
                break;
//            case "monuments":
//                mTvName.setText(name);
//                mViewAllAdapter.giveAdapterValue(mMainActivity.giveValueToAdapter(100, 200));
//                break;
//            case "long distances":
//                mTvName.setText(name);
//                mViewAllAdapter.giveAdapterValue(mMainActivity.giveValueToAdapter(200, 400));
//                break;
//            case "view all":
//                mTvName.setText(name);
//                mViewAllAdapter.giveAdapterValue(mMainActivity.giveValueToAdapter(1, 400));
//                break;
        }

    }

    private ArrayList<Element> elementArray(String el) {

        ArrayList<Element> arrElement = new ArrayList<>();

        if (el.equals("mountains")) {

            String[] mountainsNames = getResources().getStringArray(R.array.mountains);
            Resources r = getResources();
            int[] elevation = r.getIntArray(R.array.mountainsElevation);
            int[] images = {R.drawable.meverest, R.drawable.mk2, R.drawable.mkangchenjunga, R.drawable.mnangaparbat, R.drawable.mlhotse, R.drawable.mmakalu, R.drawable.mmanaslu, R.drawable.mchooyu, R.drawable.mkilimanjaro, R.drawable.mdenali};

            for (int i = 0; i < mountainsNames.length; i++) {
                Element obj = new Element();
                obj.setName(mountainsNames[i]);
                obj.setElevation(elevation[i]);
                obj.setImages(images[i]);
                arrElement.add(obj);
            }
        }
        return arrElement;
    }
}
