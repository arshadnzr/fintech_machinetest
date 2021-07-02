package com.example.flynttech_machinetest.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.flynttech_machinetest.MainActivity;
import com.example.flynttech_machinetest.R;
import com.example.flynttech_machinetest.SingletonRequestQueue;
import com.example.flynttech_machinetest.cardclass.users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;

import static android.content.Context.MODE_APPEND;

public class UserFragment extends Fragment {

    private UserViewModel userViewModel;
    private String BASE_URL = "https://jsonplaceholder.typicode.com/users/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_user, container, false);
        final TextView basicinfo = root.findViewById(R.id.userbasicinfo);
        final TextView personal = root.findViewById(R.id.userpersonal);
        final TextView company = root.findViewById(R.id.usercompanyinfo);

        userViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                Log.i("ASSS", "onChanged: ");

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

                GetData(root,basicinfo,personal,company,errorListener);

                Button logout = root.findViewById(R.id.logout);
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), MainActivity.class));

                    }
                });

            }
        });


        return root;
    }

    public void GetData(View root, final TextView basic, final TextView personal , final TextView company, Response.ErrorListener errorListener)
    {   SharedPreferences sh = root.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int id = sh.getInt("userid", 0);
        String newurl = BASE_URL + id;
        RequestQueue queue = SingletonRequestQueue.getInstance(root.getContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                        JSONObject userobject = new JSONObject(response);
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

                        String basics =
                                "Name : " + name + " \n " +
                                        "Username : " + username +  " \n " +
                                        "Email : " + email;
                        android.util.Log.i("URL", "onResponse: " + basics);
                        basic.setText(basics);

                        String addressdet = "Street : " + street + " \n " +
                                "Suite : " + Suite + " \n " +
                                "City : " + City + " \n " +
                                "Zipcode : " + Zipcode  + " \n " +
                                "Lattitude : " + Lat + " \n " +
                                "Logintude : " + Log + " \n " +
                                "Phone : " + Phone + " \n " +
                                "Website : " + Website ;

                        personal.setText(addressdet);

                        String companydet = "Company Name : " + Company  + " \n " +
                                "Catch Phrase : "  + phrase  + " \n " +
                                "BS : " + bs;

                        company.setText(companydet);


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



}