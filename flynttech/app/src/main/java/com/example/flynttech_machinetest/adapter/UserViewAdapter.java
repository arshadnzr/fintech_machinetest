package com.example.flynttech_machinetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.listviewholders.UsersViewHolder;

import java.util.Collections;
import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UsersViewHolder>  {


    List<users> list
            = Collections.emptyList();

    Context context;
    ClickListener listiner;

    public UserViewAdapter(List<users> list,
                                Context context,ClickListener listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public UsersViewHolder
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
                .inflate(R.layout.usercard,
                        parent, false);

        UsersViewHolder viewHolder
                = new UsersViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final UsersViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.Name.setText(list.get(position).name);
        viewHolder.username.setText(list.get(position).username);
        viewHolder.email.setText(list.get(position).email);
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

    public void updateList(List<users> data) {
        this.list = data;
        notifyDataSetChanged();
    }


}

