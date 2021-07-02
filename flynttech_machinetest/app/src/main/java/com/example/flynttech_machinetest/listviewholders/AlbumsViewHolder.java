package com.example.flynttech_machinetest.listviewholders;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;

public class AlbumsViewHolder extends RecyclerView.ViewHolder {

    public TextView Title;
    public View view;

    public AlbumsViewHolder(View itemView)
    {
        super(itemView);
        Title
                = (TextView)itemView
                .findViewById(R.id.albumcardtitle);

        view  = itemView;
    }



}
