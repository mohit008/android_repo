package com.mohit.program.collection_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This use recycle dependency
 *
 * Author @ Mohit Soni on 26-04-2018 18:55.
 */

public class RecycleViewSample extends Activity {

    private static String LOG_TAG = "RecycleViewSample";

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_sample);

        for(int i=0 ; i<10 ; i++){
            data.add(i+"...");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.text1.setText(data.get(position));
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        public ViewHolder(View v){
            super(v);
            text1 = (TextView)v.findViewById(android.R.id.text1);
        }
    }

}
