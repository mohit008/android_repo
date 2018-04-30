package com.mohit.program.animate;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 27-04-2018 19:15.
 */

public class AnimationSample extends Activity {

    public static  String [] data = {"blink","bounce","fade","move","rotate",
            "sequential","slidedown","slideup","together","zoom"};

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

                final Dialog dialog = new Dialog(AnimationSample.this);
                dialog.setContentView(R.layout.item);
                dialog.setTitle("Animation");

                ImageView image = (ImageView) dialog.findViewById(R.id.ivItem);
                image.setImageResource(R.mipmap.ic_launcher);
                image.setVisibility(View.VISIBLE);

                Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), getAni(position+""));
                image.setAnimation(ani);

                dialog.show();
            }
        });
    }

    /**
     * get animation layout file
     * @param animation_string
     * @return
     */
    public int getAni(String animation_string) {
        int r = 0;
        switch (animation_string) {
            case "0":
                r = R.anim.blink;
                break;
            case "1":
                r = R.anim.bounce;
                break;
            case "2":
                r = R.anim.fade;
                break;
            case "3":
                r = R.anim.move;
                break;
            case "4":
                r = R.anim.rotate;
                break;
            case "5":
                r = R.anim.sequential;
                break;
            case "6":
                r = R.anim.slidedown;
                break;
            case "7":
                r = R.anim.slideup;
                break;
            case "8":
                r = R.anim.together;
                break;
            case "9":
                r = R.anim.zoom;
                break;
        }
        return r;
    }

}
