package com.example.hapadim.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hapadim.R;
import com.squareup.picasso.Picasso;

/**
 * Created by queenabergen on 3/14/17.
 */

public class StartPageAdapter extends PagerAdapter {
    Context adaptercontext;
    LayoutInflater layoutInflater;
    String[] urlPhoto;


    public StartPageAdapter(Context context, String[] photos) {
        urlPhoto=photos;
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
        View itemView = layoutInflater.inflate(R.layout.startviewholder, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Picasso.with(adaptercontext).load(urlPhoto[position]).resize(300, 300).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
