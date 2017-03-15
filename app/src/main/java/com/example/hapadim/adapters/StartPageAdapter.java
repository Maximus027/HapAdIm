package com.example.hapadim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hapadim.R;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by queenabergen on 3/14/17.
 */

public class StartPageAdapter extends PagerAdapter {
    Context adaptercontext;
    LayoutInflater layoutInflater;
    Intent intentPage;
    Place example;
    ArrayList<String> urlPhoto;


    public StartPageAdapter(Context context, Intent intent) {
        this.intentPage = intent;
        example = Parcels.unwrap(intentPage.getParcelableExtra("chosen_place"));
        adaptercontext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        urlPhoto = new ArrayList<>();
        urlPhoto.add(0, example.getUrlImg());
        urlPhoto.add(1, example.getUrlImg2());
        urlPhoto.add(2, example.getUrlImg3());
        View itemView = layoutInflater.inflate(R.layout.startviewholder, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Picasso.with(adaptercontext).load(urlPhoto.get(position)).resize(300, 300).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
