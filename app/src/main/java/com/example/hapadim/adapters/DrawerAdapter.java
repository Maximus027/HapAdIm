package com.example.hapadim.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.models.Badge;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NesadaKoca on 3/14/17.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.Holder> {

    private List<Badge> listBadges;
    private Context context;

    public DrawerAdapter(List<Badge> badges) {
        if (badges.size() == 0){
            Badge badge = new Badge();
            badge.setBadgedName("No badges yet. Start a challenge to begin earning some!");
            badge.setBadgeImg("");
            badges.add(badge);
        }
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
        Badge badge = listBadges.get(position);
        holder.tvBadgeDescription.setText(badge.getBadgedName()); //this is only for testing purpose

        if (badge.getBadgeImg().contains("no image") || badge.getBadgeImg().isEmpty()){
            Picasso.with(context).load(R.drawable.ideadicon).into(holder.imgBadgeIcon);
        } else {
            Log.d("adapter", "onBindViewHolder: " + badge.getBadgeImg());
            Picasso.with(context).load(badge.getBadgeImg()).into(holder.imgBadgeIcon);
        }
    }

    @Override
    public int getItemCount() {
        return listBadges.size();
    }

    public void setNewData(List<Badge> newData) {
        listBadges.addAll(newData);
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView tvBadgeDescription;
        ImageView imgBadgeIcon;


        public Holder(View itemView) {
            super(itemView);
            tvBadgeDescription = (TextView) itemView.findViewById(R.id.badgeDescription);
            imgBadgeIcon = (ImageView) itemView.findViewById(R.id.badgeIcon);

        }
    }
}

