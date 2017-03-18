package com.example.hapadim.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hapadim.Constants;
import com.example.hapadim.R;
import com.example.hapadim.StartPageActivity;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.Holder> {

    private List<Place> listAll;
    private Context context;

    public ViewAllAdapter(List<Place> all) {
        this.listAll = all;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_view_holder, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(listAll.get(position));
    }

    @Override
    public int getItemCount() {
        return listAll.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView tvNameViewAll, tvElevationViewAll;
        ImageView imgViewAll;


        public Holder(View itemView) {
            super(itemView);
            tvElevationViewAll = (TextView)itemView.findViewById(R.id.tv_elevation_view_all);
            tvNameViewAll = (TextView)itemView.findViewById(R.id.tv_name_view_all);
            imgViewAll = (ImageView)itemView.findViewById(R.id.img_view_all);

        }

        public void bind(final Place place) {

            tvNameViewAll.setText(place.getPlaceName());
            tvElevationViewAll.setText(String.valueOf(place.getStepNumber()));
            Picasso.with(context).load(place.getUrlImg()).into(imgViewAll);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity activity = (Activity) itemView.getContext();
                    Intent intent = new Intent(activity, StartPageActivity.class);
                    Parcelable placeParcel = Parcels.wrap(place);
                    intent.putExtra(Constants.CHOSEN_PLACE, placeParcel);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
