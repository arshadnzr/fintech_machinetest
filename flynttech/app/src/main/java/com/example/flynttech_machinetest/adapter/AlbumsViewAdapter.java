package com.example.flynttech_machinetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.listviewholders.AlbumsViewHolder;
import com.example.flynttech_machinetest.listviewholders.UsersViewHolder;

import java.util.Collections;
import java.util.List;

public class AlbumsViewAdapter extends RecyclerView.Adapter<AlbumsViewHolder>  {


    List<album> list
            = Collections.emptyList();

    Context context;
    ClickListener listiner;

    public AlbumsViewAdapter(List<album> list,
                                Context context,ClickListener listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public AlbumsViewHolder
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
                .inflate(R.layout.albumcard,
                        parent, false);

        AlbumsViewHolder viewHolder
                = new AlbumsViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final AlbumsViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.Title.setText(list.get(position).title);
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

    public void updateList(List<album> data) {
        this.list = data;
        notifyDataSetChanged();
    }


}

