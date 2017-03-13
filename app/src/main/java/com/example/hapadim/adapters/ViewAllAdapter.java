package com.example.hapadim.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hapadim.R;
import com.example.hapadim.models.Place;
import com.squareup.picasso.Picasso;

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

        holder.tvNameViewAll.setText(listAll.get(position).getPlaceName() + "");
        holder.tvElevationViewAll.setText(listAll.get(position).getStepNumber() + "");
        Picasso.with(context).load(listAll.get(position).getUrlImg()).into(holder.imgViewAll);
    }

    @Override
    public int getItemCount() {
        return listAll.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameViewAll, tvElevationViewAll;
        ImageView imgViewAll;


        public Holder(View itemView) {
            super(itemView);

            tvElevationViewAll = (TextView)itemView.findViewById(R.id.tv_elevation_view_all);
            tvNameViewAll = (TextView)itemView.findViewById(R.id.tv_name_view_all);
            imgViewAll = (ImageView)itemView.findViewById(R.id.img_view_all);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO: go to start page.
            Activity activity = (Activity) itemView.getContext();
            Toast.makeText(activity, "This is the view all adapter, take me to the start page", Toast.LENGTH_SHORT).show();

        }
    }
}
