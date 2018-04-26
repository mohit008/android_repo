package com.mohit.program.grid_view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 26-04-2018 16:34.
 */

public class GridViewSample extends Activity {

    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_sample);

        grid = (GridView) findViewById(R.id.grid);

        // adapter
        grid.setAdapter(new BaseAdapter() {
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
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                    ((TextView) view.findViewById(android.R.id.text1)).setText(getItemId(position) + "");
                }
                return view;
            }
        });

        // change index background
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                grid.getChildAt(position).setBackgroundColor(Color.CYAN);
            }
        });
//        grid.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    view.setBackgroundColor(Color.CYAN);
//                }
//                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
//                    view.setBackgroundColor(Color.BLUE);
//                }
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    view.setBackgroundColor(Color.MAGENTA);
//
//                }
//                return true;
//            }
//        });
    }
}
