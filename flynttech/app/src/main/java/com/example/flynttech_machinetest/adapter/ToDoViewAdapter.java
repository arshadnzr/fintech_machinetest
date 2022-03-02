package com.example.flynttech_machinetest.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.post;
import com.example.flynttech_machinetest.cardclass.todo;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.listviewholders.AlbumsViewHolder;
import com.example.flynttech_machinetest.listviewholders.PostViewHolder;
import com.example.flynttech_machinetest.listviewholders.ToDoViewHolder;
import com.example.flynttech_machinetest.listviewholders.UsersViewHolder;
import com.example.flynttech_machinetest.ui.todo.ToDoViewModel;

import java.util.Collections;
import java.util.List;

public class ToDoViewAdapter extends RecyclerView.Adapter<ToDoViewHolder>  {


    List<todo> list
            = Collections.emptyList();

    Context context;
    ClickListener listiner;

    public ToDoViewAdapter(List<todo> list,
                                Context context,ClickListener listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public ToDoViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.todocard,
                        parent, false);

        ToDoViewHolder viewHolder
                = new ToDoViewHolder(photoView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void
    onBindViewHolder(final ToDoViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.Body.setText(list.get(position).title);
        if(list.get(position).done == true)
        {
            ColorStateList csl = ColorStateList.valueOf(Color.parseColor("#4CAF50"));
            viewHolder.Card.setBackgroundTintList(csl);
        }
        else
        {
            ColorStateList csl = ColorStateList.valueOf(Color.parseColor("#F44336"));
            viewHolder.Card.setBackgroundTintList(csl);
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listiner.click(index);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void updateList(List<todo> data) {
        this.list = data;
        notifyDataSetChanged();
    }


}

