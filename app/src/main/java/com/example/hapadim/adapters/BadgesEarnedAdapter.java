package com.example.hapadim.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by meltemyildirim on 3/18/17.
 */

public class BadgesEarnedAdapter extends RecyclerView.Adapter {
    private List<Badge> badges;

    public BadgesEarnedAdapter(List<Badge> badgesEarnedForThisChallenge) {
        this.badges = badgesEarnedForThisChallenge;
        if (badges.size() == 0) {
            Badge temp = new Badge();
            temp.setBadgeDesc("No badges earned yet!");
            temp.setBadgeImg("");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BadgesEarnedViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BadgesEarnedViewHolder) holder).bind(badges.get(position));
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }
}

class BadgesEarnedViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;
    private Context context;

    public BadgesEarnedViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        imageView = (ImageView) itemView.findViewById(R.id.badge_earned_img_ip);
        textView = (TextView) itemView.findViewById(R.id.badge_earned_name_ip);
        context = parent.getContext();

    }

    private static View inflateView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.badges_earned_ip, parent, false);
    }

    public void bind(Badge badge) {

        if (badge.getBadgeImg().isEmpty()) {
            Picasso.with(context).load(R.drawable.ideadicon).into(imageView);
        } else if (!badge.getBadgeImg().contains("no name")) {
            Picasso.with(context).load(badge.getBadgeImg()).into(imageView);
        }
        textView.setText(badge.getBadgedName());
    }
}
