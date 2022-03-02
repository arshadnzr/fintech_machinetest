package com.example.flynttech_machinetest.listviewholders;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    public TextView Body;
    public ConstraintLayout Card;
    public View view;

    public ToDoViewHolder(View itemView)
    {
        super(itemView);
        Body
                = (TextView)itemView
                .findViewById(R.id.task);

        Card = itemView.findViewById(R.id.card);
        view  = itemView;
    }



}
