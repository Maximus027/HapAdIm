package com.example.hapadim.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.R;
import com.example.hapadim.StartPageActivity;
import com.example.hapadim.ViewAllActivity;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class LandMarksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ImageView catIcon;
    int mountIcon = R.drawable.mountainicon;
    int monumentIcon = R.drawable.monumenticon;
    int distanceIcon = R.drawable.walkicon;
    private static final int HOLDER = 1;
    private static final int FOOTER = 0;


    private List<Place> landmarkList;
    private Context context;
    public static final String TAG = LandMarksAdapter.class.getName();

    public LandMarksAdapter(List<Place> landmarks) {
        Log.d(TAG, "Monuments size " + landmarks.size());
        if (landmarks.size() == 0) {
            this.landmarkList = new ArrayList<>();
        } else {
            this.landmarkList = landmarks;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < landmarkList.size()) {
            //reg viewholder
            return HOLDER;
        } else {
            //footer
            return FOOTER;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == FOOTER) {
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

        if (type == HOLDER) {
            Holder defaultHolder = (Holder) holder;
            defaultHolder.bind(landmarkList.get(position));
        } else if (type == FOOTER) {
            //we get this position to pass in the category onto the viewAll activity
            ((Footer) holder).bind(landmarkList.get(position - 1));
        }

    }

    @Override
    public int getItemCount() {
        return landmarkList.size() + 1;
    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView tvName, tvElevation;
        ImageView images;

        private Holder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvElevation = (TextView) itemView.findViewById(R.id.tv_elevation);
            images = (ImageView) itemView.findViewById(R.id.images);
        }

        private void bind(final Place place) {
            tvName.setText(place.getPlaceName());
            tvElevation.setText(String.valueOf(place.getStepNumber()));
            Picasso.with(context).load(place.getUrlImg()).into(images);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity activity = (Activity) itemView.getContext();
                    Intent intent = new Intent(activity, StartPageActivity.class);
                    Parcelable placeParcel = Parcels.wrap(place);
                    intent.putExtra("chosen_place", placeParcel);
                    activity.startActivity(intent);
                }
            });
        }
    }

    private class Footer extends RecyclerView.ViewHolder {

        private Footer(View itemView) {
            super(itemView);
        }

        private void bind(final Place place) {
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