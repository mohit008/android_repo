package com.mohit.program.bluetooth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 14-05-2018 11:18 AM.
 */

public class PairAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> device_name = new ArrayList<String>();
    ArrayList<String> device_mac = new ArrayList<String>();

    public PairAdapter(Context context, ArrayList<String> device_name, ArrayList<String> device_mac) {
        this.device_mac = device_mac;
        this.device_name = device_name;
        this.context = context;
    }

    @Override
    public int getCount() {
        return device_name.size();
    }

    @Override
    public Object getItem(int position) {
        return device_name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.bluetooth_device_item, parent,false);

        }
        TextView name,mac;

        name = (TextView)view.findViewById(R.id.device_name);
        mac = (TextView)view.findViewById(R.id.device_mac);

        name.setText(device_name.get(position));
        mac.setText(device_mac.get(position));

        return view;
    }
}
