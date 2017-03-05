package com.example.hapadim.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.models.Element;

import java.util.ArrayList;

/**
 * Created by Nesada on 2/28/2017.
 */

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.Holder> {

    private ArrayList<Element> temp;

    public ViewAllAdapter() {

    }

    public void giveAdapterValue(ArrayList<Element> value) {
        this.temp = value;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_view_holder, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.mTvNameViewAll.setText(temp.get(position).getName() + "");
        holder.mTvElevationViewAll.setText(temp.get(position).getElevation() + "");
        holder.mImgViewAll.setImageResource(temp.get(position).getImages());
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }





    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTvNameViewAll, mTvElevationViewAll;
        ImageView mImgViewAll;


        public Holder(View itemView) {
            super(itemView);

            mTvElevationViewAll = (TextView)itemView.findViewById(R.id.tv_elevation_view_all);
            mTvNameViewAll = (TextView)itemView.findViewById(R.id.tv_name_view_all);
            mImgViewAll = (ImageView)itemView.findViewById(R.id.img_view_all);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

        }
    }
}
