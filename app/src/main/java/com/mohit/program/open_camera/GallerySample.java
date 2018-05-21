package com.mohit.program.open_camera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 26-04-2018 16:55.
 */

public class GallerySample extends Activity {
    Gallery gallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_sample);

        gallery = (Gallery) findViewById(R.id.gallery);

        // adapter
        gallery.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return "Object" + position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView image_view = new ImageView(GallerySample.this);
                image_view.setLayoutParams(new Gallery.LayoutParams(500, 500));
                image_view.setImageResource(R.mipmap.ic_launcher);
                image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return image_view;
            }
        });


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //do something....
            }
        });


    }
}
