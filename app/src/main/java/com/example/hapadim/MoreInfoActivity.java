package com.example.hapadim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.hapadim.models.Element;

/**
 * Created by queenabergen on 3/9/17.
 */

public class MoreInfoActivity extends Activity {
    TextView locationName;
    TextView locationFacts;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mockpage);
        locationName = (TextView)findViewById(R.id.locationName);
        locationFacts = (TextView)findViewById(R.id.locationFacts);


        Intent intent = getIntent();
        Element element = (Element)intent.getSerializableExtra("element");
        int elevation = element.getElevation();
        String locationName = element.getName();


    }
}
