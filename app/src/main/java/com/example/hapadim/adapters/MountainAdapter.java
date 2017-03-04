package com.example.hapadim.adapters;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.ViewAllActivity;

import java.util.ArrayList;


/**
 * Created by Nesada on 2/28/2017.
 */

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.Holder> {

    ArrayList<Integer> temp;

    public MountainAdapter() {
        notifyDataSetChanged();
    }

    public void giveAdapterValue(ArrayList<Integer> value) {
        this.temp = value;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingpageviewholder, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.mTvTest.setText(temp.get(position) + "");

    }

    @Override
    public int getItemCount() {
        return temp.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTvTest;


        public Holder(View itemView) {
            super(itemView);

            mTvTest = (TextView) itemView.findViewById(R.id.tv_test);
             itemView.setOnClickListener(this);
        }

//        public void bind() {
//
//        }


        @Override
        public void onClick(View view) {
            Intent viewAll = new Intent(view.getContext(), ViewAllActivity.class);
            view.getContext().startActivity(viewAll);




        }
    }
}
