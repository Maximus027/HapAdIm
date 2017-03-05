package com.example.hapadim.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hapadim.R;

import java.util.ArrayList;


/**
 * Created by Nesada on 2/28/2017.
 */

public class MonumentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Integer> temp;

    public MonumentsAdapter() {
        notifyDataSetChanged();
    }

    public void giveAdapterValue(ArrayList<Integer> value) {
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

        if(viewType == 1){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_recyclerview, parent, false);

            return new Footer(view);

        }
        else {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingpageviewholder, parent, false);

            return new Holder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder mHolder = (Holder) holder;
            mHolder.mTvTest.setText(temp.get(position) + "");
        }
    }

    @Override
    public int getItemCount() {
        return temp.size() + 1;
    }


    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTvTest;


        private Holder(View itemView) {
            super(itemView);

            mTvTest = (TextView) itemView.findViewById(R.id.tv_elevation);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class Footer extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTvTest;


        private Footer(View itemView) {
            super(itemView);

            mTvTest = (TextView) itemView.findViewById(R.id.tv_elevation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
