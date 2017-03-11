package com.example.hapadim.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.ViewAllActivity;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Nesada on 2/28/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView mTvTest;
    ArrayList<Place> temp;
    private Context context;

    public LocationAdapter() {
        notifyDataSetChanged();
    }

    public void giveAdapterValue(ArrayList<Place> value) {
        this.temp = value;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position < temp.size()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_recyclerview, parent, false);

            return new Footer(view);

        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingpageviewholder, parent, false);
            return new Holder(view);
        }

    }


    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTvName, mTvElevation;
        ImageView mImages;


        private Holder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvElevation = (TextView) itemView.findViewById(R.id.tv_elevation);
            mImages = (ImageView) itemView.findViewById(R.id.images);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {


        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Holder) {
            Holder mHolder = (Holder) holder;
            Long stepNumber = temp.get(position).getStepNumber();
            String newStepNumber = stepNumber.toString();
            mHolder.mTvName.setText(temp.get(position).getPlaceName());
            mHolder.mTvElevation.setText(newStepNumber);
            Picasso.with(context).load(temp.get(position).getUrlIMG());


        }

    }

    @Override
    public int getItemCount() {
        return temp.size();
    }


    private class Footer extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Footer(View itemView) {
            super(itemView);

            mTvTest = (TextView) itemView.findViewById(R.id.tv_test);
            mTvTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                }
            });
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Intent viewAll = new Intent(view.getContext(), ViewAllActivity.class);
            viewAll.putExtra("id", "mountains");
            view.getContext().startActivity(viewAll);

        }
    }
}
