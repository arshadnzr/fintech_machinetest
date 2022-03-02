package com.example.flynttech_machinetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.post;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.listviewholders.AlbumsViewHolder;
import com.example.flynttech_machinetest.listviewholders.PostViewHolder;
import com.example.flynttech_machinetest.listviewholders.UsersViewHolder;

import java.util.Collections;
import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewHolder>  {


    List<post> list
            = Collections.emptyList();

    Context context;
    ClickListener listiner;

    public PostViewAdapter(List<post> list,
                                Context context,ClickListener listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public PostViewHolder
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
                .inflate(R.layout.postcard,
                        parent, false);

        PostViewHolder viewHolder
                = new PostViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final PostViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.Title.setText(list.get(position).title);
        viewHolder.Body.setText(list.get(position).body);
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

    public void updateList(List<post> data) {
        this.list = data;
        notifyDataSetChanged();
    }


}

