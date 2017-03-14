package com.example.hapadim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by queenabergen on 3/8/17.
 */

public class StatueOfLiberty extends Activity {
    ArrayList<Integer> statuesliderimages;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        imageView = (ImageView) findViewById(R.id.locationSlideImages);


        statuesliderimages = new ArrayList<>();
        statuesliderimages.add(0, R.drawable.liberty);
        Picasso.with(getApplicationContext()).load(statuesliderimages.get(0)).resize(300, 300).into(imageView);
    }
}
