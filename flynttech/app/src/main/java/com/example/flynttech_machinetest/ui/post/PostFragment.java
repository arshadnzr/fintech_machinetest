package com.example.flynttech_machinetest.ui.post;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.SingletonRequestQueue;
import com.example.flynttech_machinetest.adapter.AlbumsViewAdapter;
import com.example.flynttech_machinetest.adapter.ClickListener;
import com.example.flynttech_machinetest.adapter.PostViewAdapter;
import com.example.flynttech_machinetest.cardclass.album;
import com.example.flynttech_machinetest.cardclass.post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    final List<post> list = new ArrayList<>();
    private PostViewModel postViewModel;
    public  String BASE_URL = "https://jsonplaceholder.typicode.com/posts?userId=";
    PostViewAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postViewModel =
                ViewModelProviders.of(this).get(PostViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_posts, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.postlist);
        postViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

                GetPostsData(root, recyclerView ,errorListener);
            }
        });


        return root;
    }


    public void GetPostsData(final View root, final RecyclerView recyclerView, Response.ErrorListener errorListener)
    {
        SharedPreferences sh = root.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        final int id = sh.getInt("userid", 0);
        String newurl = BASE_URL + id;
        RequestQueue queue = SingletonRequestQueue.getInstance(root.getContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray posts  = new JSONArray(response);
                    for(int i = 0 ; i < posts.length() ; i++)
                    {
                        JSONObject pst = posts.getJSONObject(i);
                        String title = pst.getString("title");
                        String body = pst.getString("body");
                        post post = new post(title,body);
                        list.add(post);
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



    public void Render(View root,RecyclerView recyclerView)
    {
        ClickListener listiner = new ClickListener() {
            @Override
            public void click(int index){

            }
        };
        adapter = new PostViewAdapter( list, root.getContext(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(root.getContext()));
    }



}