package com.example.hapadim.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hapadim.R;
import com.example.hapadim.ViewAllActivity;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class LandMarksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HOLDER = 1;
    private static final int FOOTER = 0;



    private List<Place> landmarkList;
    private Context context;
    public static final String TAG = LandMarksAdapter.class.getName();

    public LandMarksAdapter(List<Place> landmarks) {
        Log.d(TAG, "Monuments size " + landmarks.size());
        if (landmarks.size() == 0){
            this.landmarkList = new ArrayList<>();
        } else {
            this.landmarkList = landmarks;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position < landmarkList.size()) {
            //reg viewholder
            return 1;
        } else {
            //footer
            return 0;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.footer_recyclerview, parent, false);
            return new Footer(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.landingpageviewholder, parent, false);
            return new Holder(view);
        }

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int type = holder.getItemViewType();

        if (type == 1 ) {
            Holder mHolder = (Holder) holder;
            int stepNumber = landmarkList.get(position).getStepNumber();
            String newStepNumber = stepNumber +" ";

            mHolder.tvName.setText(landmarkList.get(position).getPlaceName());
            mHolder.tvElevation.setText(newStepNumber);
            Picasso.with(context).load(landmarkList.get(position).getUrlImg()).into(mHolder.images);
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, " landmarks ", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (type == 0){
            ((Footer) holder).bind(landmarkList.get(position-1));
        }

    }

    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvElevation;
        ImageView images;
        private Holder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvElevation = (TextView) itemView.findViewById(R.id.tv_elevation);
            images = (ImageView) itemView.findViewById(R.id.images);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }


    @Override
    public int getItemCount() {
        return landmarkList.size() + 1;
    }


    private class Footer extends RecyclerView.ViewHolder {

        private Footer(View itemView) {
            super(itemView);
        }

        public void bind(final Place place){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewAll = new Intent(v.getContext(), ViewAllActivity.class);
                    viewAll.putExtra("category_key", place.getCategory());
                    v.getContext().startActivity(viewAll);
                }
            });

        }

    }
}