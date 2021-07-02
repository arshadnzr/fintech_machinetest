package com.example.flynttech_machinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class picture extends AppCompatActivity {
    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mImageLoader = SingletonRequestQueue.getInstance(this.getApplicationContext())
                .getImageLoader();

        NetworkImageView image = findViewById(R.id.imageView6);
        //Image URL - This can point to any image file supported by Android
        final String url = getIntent().getStringExtra("link") + ".png";
        mImageLoader.get(url, ImageLoader.getImageListener(image,
                R.drawable.ic_baseline_watch_later_24, android.R.drawable
                        .ic_dialog_alert));
        image.setImageUrl(url, mImageLoader);
    }
}