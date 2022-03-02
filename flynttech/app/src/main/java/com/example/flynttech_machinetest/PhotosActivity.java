package com.example.flynttech_machinetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.flynttech_machinetest.adapter.AlbumsViewAdapter;
import com.example.flynttech_machinetest.adapter.ClickListener;
import com.example.flynttech_machinetest.adapter.PhotosViewAdapter;
import com.example.flynttech_machinetest.adapter.UserViewAdapter;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.photo;
import com.example.flynttech_machinetest.cardclass.users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    PhotosViewAdapter adapter;
    String BASE_URL = "https://jsonplaceholder.typicode.com/photos?albumId=";
    final List<photo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setTitle("Images");

        final RecyclerView recyclerView = findViewById(R.id.photoslist);

        GetAlbumsData(recyclerView ,errorListener);



    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };



    public void GetAlbumsData(final RecyclerView recyclerView, Response.ErrorListener errorListener)
    {
        int albumid = getIntent().getIntExtra("albumid", -1);
        String newurl = BASE_URL + albumid;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray phot  = new JSONArray(response);
                    for(int i = 0 ; i < phot.length() ; i++)
                    {
                        JSONObject alb = phot.getJSONObject(i);
                        String title = alb.getString("title");
                        String smallurl = alb.getString("thumbnailUrl");
                        String bigurl = alb.getString("url");
                        Log.i("ASSS", "onResponse: " + title);
                        Log.i("ASSS", "onResponse: " + smallurl);
                        Log.i("ASSS", "onResponse: " + bigurl);

                        photo newphoto = new photo(title,smallurl,bigurl);
                        list.add(newphoto);
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                Render(recyclerView);


            }
        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

        };

        queue.add(stringRequest);



    }

    public void Render(RecyclerView recyclerView)
    {
        ClickListener listiner = new ClickListener() {
            @Override
            public void click(int index){

                Intent intent = new Intent(PhotosActivity.this, picture.class);
                intent.putExtra("link" , list.get(index).bigurl);
                startActivity(intent);

            }
        };
        adapter = new PhotosViewAdapter( list, getApplicationContext() ,listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
    }






}