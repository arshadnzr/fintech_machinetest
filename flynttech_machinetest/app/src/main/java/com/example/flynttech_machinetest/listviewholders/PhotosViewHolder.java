package com.example.flynttech_machinetest.listviewholders;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.flynttech_machinetest.R;

public class PhotosViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView image;
    public View view;

    public PhotosViewHolder(View itemView)
    {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.phototitle);
        image = itemView.findViewById(R.id.imageView5);
        view  = itemView;
    }



}
