package com.example.hapadim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.adapters.MountainAdapter;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView mRvViewAll;
    private MountainAdapter mMountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationviewholder);
        ImageView imageView = (ImageView)findViewById(R.id.locationSlideImages) ;
//        Picasso.with(getApplicationContext()).load(R.drawable.mteverestimage).resize(300,300).into(imageView);

        TextView textView = (TextView) findViewById(R.id.locationFacts);
        textView.setText("Everest is 29,035 feet or 8848 meters high\n" +
                "                \"The summit is the border of Nepal to the south and China or Tibet on the north\n" +
                "                Being over 60 million years oldn\n" +
                "                Everest was formed by the movement of the Indian tectonic plate pushing up and against the Asian plate");
//
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

    }
}
