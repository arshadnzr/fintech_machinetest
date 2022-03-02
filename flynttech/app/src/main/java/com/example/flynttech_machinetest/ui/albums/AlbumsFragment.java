package com.example.flynttech_machinetest.ui.albums;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.flynttech_machinetest.MainActivity;
import com.example.flynttech_machinetest.PhotosActivity;
import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.SingletonRequestQueue;
import com.example.flynttech_machinetest.adapter.AlbumsViewAdapter;
import com.example.flynttech_machinetest.adapter.ClickListener;
import com.example.flynttech_machinetest.adapter.UserViewAdapter;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.users;
import com.example.flynttech_machinetest.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumsFragment extends Fragment {
    final List<album> list = new ArrayList<>();
    private String BASE_URL = "https://jsonplaceholder.typicode.com/albums?userId=";
    AlbumsViewAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlbumsViewModel albumsViewModel = ViewModelProviders.of(this).get(AlbumsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_albums, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.albumslist);
        albumsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(root.getContext(), "No network available", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(root.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                };

                GetAlbumsData(root, recyclerView ,errorListener);
            }
        });


        return root;
    }


    public void GetAlbumsData(final View root, final RecyclerView recyclerView, Response.ErrorListener errorListener)
    {
        SharedPreferences sh = root.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        final int id = sh.getInt("userid", 0);
        String newurl = BASE_URL + id;
        RequestQueue queue = SingletonRequestQueue.getInstance(root.getContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray albums  = new JSONArray(response);
                    for(int i = 0 ; i < albums.length() ; i++)
                    {
                        JSONObject alb = albums.getJSONObject(i);
                        int albumid = alb.getInt("id");
                        String title = alb.getString("title");
                        album album = new album(albumid,title);
                        list.add(album);
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                Render(root,recyclerView);


            }
        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

        };

        queue.add(stringRequest);



    }


    public void Render(final View root,RecyclerView recyclerView)
    {
        ClickListener listiner = new ClickListener() {
            @Override
            public void click(int index){
                Intent intent  = new Intent(getActivity(), PhotosActivity.class);
                intent.putExtra("albumid",list.get(index).id);
                root.getContext().startActivity(intent);

            }
        };
        adapter = new AlbumsViewAdapter( list, root.getContext(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(root.getContext()));
    }

}