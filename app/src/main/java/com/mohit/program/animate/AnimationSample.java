package com.mohit.program.animate;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 27-04-2018 19:15.
 */

public class AnimationSample extends Activity {

    String [] data = {"blink","bounce","fade","move","rotate","sequential","slidedown","slideup","together","zoom"};
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        list = (ListView)findViewById(R.id.list);
        list.setVisibility(View.VISIBLE);

        // adapter
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Object getItem(int position) {
                return data[position];
            }

            @Override
            public long getItemId(int position) {
                return data.length;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                    ((TextView) view.findViewById(android.R.id.text1)).setText(data[position] + "");
                }
                return view;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), data[position].toString(), Toast.LENGTH_SHORT).show();
//                final Dialog dialog = new Dialog(AnimationSample.this);
//                dialog.setTitle("This is custom Dialog");
//
//                LinearLayout linearLayout = new LinearLayout(AnimationSample.this);
//                linearLayout.setOrientation(LinearLayout.VERTICAL);
//                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(200,200));
//
//                // view
//                ImageView imageView = new ImageView(AnimationSample.this);
//                Animation ani =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
//                imageView.setAnimation(ani);
//
//
//                // add to parent
//                linearLayout.addView(imageView);
//                dialog.addContentView(linearLayout,new LinearLayout.LayoutParams(300,500));
//                dialog.show();
            }
        });
    }

}
