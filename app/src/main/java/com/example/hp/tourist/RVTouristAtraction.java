package com.example.hp.tourist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RVTouristAtraction extends RecyclerView.Adapter<RVTouristAtraction.ViewHolder>{

    private ArrayList<AtraccionTuristica> mDataset;

    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;

        ImageView image;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            textViewTitle = v.findViewById(R.id.tvNombre);
            image = v.findViewById(R.id.imgAtraction);
        }
    }

    public RVTouristAtraction(ArrayList<AtraccionTuristica> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_municipios, parent,false);
        return new RVTouristAtraction.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textViewTitle.setText(mDataset.get(position).getName());
        holder.image.setImageDrawable(mDataset.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
