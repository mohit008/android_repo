package com.mohit.program.collection_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 26-04-2018 18:04.
 */

public class ListViewSample extends Activity{

    String []list_data = {"Android","Apple","Microsoft","LinkedIn","Facebook","Github"};

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        list = (ListView)findViewById(R.id.list);
        list.setVisibility(View.VISIBLE);

        // set only text
        /* ArrayAdapter<String> _li = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,data); */

        // adapter
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list_data.length;
            }

            @Override
            public Object getItem(int position) {
                return list_data[position];
            }

            @Override
            public long getItemId(int position) {
                return list_data.length;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                    ((TextView) view.findViewById(android.R.id.text1)).setText(list_data[position] + "");
                }
                return view;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), list_data[position].toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
