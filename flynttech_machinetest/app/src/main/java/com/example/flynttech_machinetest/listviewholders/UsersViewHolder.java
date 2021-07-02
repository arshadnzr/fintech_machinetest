package com.example.flynttech_machinetest.listviewholders;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flynttech_machinetest.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {

    public TextView Name;
    public TextView username;
    public TextView email;
    public View view;

    public UsersViewHolder(View itemView)
    {
        super(itemView);
        Name
                = (TextView)itemView
                .findViewById(R.id.usercardname);
        username
                = (TextView)itemView
                .findViewById(R.id.usercardusername);
        email
                = (TextView)itemView
                .findViewById(R.id.usercardemail);

        view  = itemView;
    }



}
