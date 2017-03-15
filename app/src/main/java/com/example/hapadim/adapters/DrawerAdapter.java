package com.example.hapadim.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nesadakoca on 3/14/17.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.Holder> {

    private List<Place> listBadges;
    private Context context;

    public DrawerAdapter(List<Place> badges) {
        this.listBadges = badges;
    }

    @Override
    public DrawerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievementcardviewholder, parent, false);
        return new DrawerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.Holder holder, int position) {

        holder.tvBadgeDescription.setText(listBadges.get(position).getBadges().get(0).getBadgeDesc()); //this is only for testing purpose
        Picasso.with(context).load(listBadges.get(position).getBadges().get(0).getBadgeImg()).into(holder.imgBadgeIcon);

    }

    @Override
    public int getItemCount() {
        return listBadges.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView tvBadgeDescription;
        ImageView imgBadgeIcon;


        public Holder(View itemView) {
            super(itemView);
            tvBadgeDescription = (TextView)itemView.findViewById(R.id.badgeDescription);
            imgBadgeIcon = (ImageView)itemView.findViewById(R.id.badgeIcon);

        }
    }
}

