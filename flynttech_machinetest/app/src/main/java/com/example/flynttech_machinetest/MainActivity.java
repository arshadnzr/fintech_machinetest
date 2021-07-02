package com.example.flynttech_machinetest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.flynttech_machinetest.adapter.ClickListener;
import com.example.flynttech_machinetest.adapter.UserViewAdapter;
import com.example.flynttech_machinetest.cardclass.users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserViewAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listiner;
    String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    final List<users> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        GetUsersData();


;







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


    private void GetUsersData ( ) {
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray usersjson = new JSONArray(response);

                    for(int i = 0 ; i < usersjson.length() ; i++)
                    {
                        JSONObject userobject = usersjson.getJSONObject(i);
                        int id = userobject.getInt("id");
                        String name = userobject.getString("name");
                        String username = userobject.getString("username");
                        String email  = userobject.getString("email");
                        JSONObject address = userobject.getJSONObject("address");
                        String street  = address.getString("street");
                        String Suite  = address.getString("suite");
                        String City  = address.getString("city");
                        String Zipcode = address.getString("zipcode");
                        JSONObject geo =  address.getJSONObject("geo");
                        String Lat  = geo.getString("lat");
                        String Log  = geo.getString("lng");
                        String Phone = userobject.getString("phone");
                        JSONObject comp = userobject.getJSONObject("company");
                        String Website = userobject.getString("website");
                        String Company = comp.getString("name");
                        String phrase = comp.getString("catchPhrase");
                        String bs = comp.getString("bs");
                        users newuser = new users(id,name,username,email,street,Suite,City,Zipcode,Lat,Log,Phone,Website,Company,phrase,bs);
                        list.add(newuser);
                    }
                    Render();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

        };

        queue.add(stringRequest);


    }

    public void Render()
    {
        recyclerView  = (RecyclerView)findViewById(R.id.userlist);
        listiner = new ClickListener() {
            @Override
            public void click(int index){
                Log.i("asd", "click: " + index);
                users user = list.get(index);
                int id = user.id;
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putInt("userid", id);
                myEdit.commit();
                Intent intent = new Intent(MainActivity.this, user.class);
                startActivity(intent);
            }
        };
        adapter = new UserViewAdapter ( list, getApplication(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(MainActivity.this));
    }


}