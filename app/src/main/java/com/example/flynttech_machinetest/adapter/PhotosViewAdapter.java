package com.example.flynttech_machinetest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.nsd.NsdManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.SingletonRequestQueue;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.photo;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.listviewholders.AlbumsViewHolder;
import com.example.flynttech_machinetest.listviewholders.PhotosViewHolder;
import com.example.flynttech_machinetest.listviewholders.UsersViewHolder;

import java.util.Collections;
import java.util.List;

public class PhotosViewAdapter extends RecyclerView.Adapter<PhotosViewHolder>  {


    List<photo> list
            = Collections.emptyList();

    Context context;
    ClickListener listiner;

    public PhotosViewAdapter(List<photo> list,
                                Context context,ClickListener listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public PhotosViewHolder
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
                .inflate(R.layout.photocard,
                        parent, false);

        PhotosViewHolder viewHolder
                = new PhotosViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final PhotosViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        Log.i("ASSS", "Getting in viewholder");
        Log.i("ASSS", "Total Data Size" + list.size());
        Log.i("ASSS", "Current Postition " + position);
        Log.i("ASSS", "Title : " + list.get(position).title);

        viewHolder.title.setText(list.get(position).title);

        String smallurl = list.get(position).smallurl + ".png";
        Log.i("ASSS", "Small URL : " + smallurl);

        //Image URL - This can point to any image file supported by Android
        String URL = "https://www.google.co.in/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        ImageRequest imageRequest = new ImageRequest(URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.i("ASSS", "onResponse: " + "Got Image");
                        viewHolder.image.setImageBitmap(response);

                    }
                }, 2000, 2000, ImageView.ScaleType.FIT_XY, Bitmap.Config.ALPHA_8, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ASSS", "onErrorResponse: ", error );

            }
        }
        );
        RequestQueue queue = SingletonRequestQueue.getInstance(viewHolder.view.getContext()).getRequestQueue();
        queue.add(imageRequest);




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

    public void updateList(List<photo> data) {
        this.list = data;
        notifyDataSetChanged();
    }


}

