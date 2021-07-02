package com.example.flynttech_machinetest.listviewholders;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView Title;
    public TextView Body;
    public View view;

    public PostViewHolder(View itemView)
    {
        super(itemView);
        Title
                = (TextView)itemView
                .findViewById(R.id.postcardtitle);

        Body  = (TextView)itemView
            .findViewById(R.id.postcardbody);

        view  = itemView;
    }



}
