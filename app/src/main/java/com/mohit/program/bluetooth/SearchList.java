package com.mohit.program.bluetooth;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mohit.program.R;
import com.mohit.program.util.Global;

import java.util.ArrayList;
import java.util.Set;

/**
 * Author @ Mohit Soni on 14-05-2018 11:22 AM.
 */

public class SearchList extends Activity implements View.OnClickListener {

    private ListView listview;
    private ArrayList<String> device_list;
    private Button scan;
    private BluetoothAdapter b_adapter;
    private Set<BluetoothDevice> paired;
    private ArrayList<BluetoothDevice> b_list;
    private SearchAdapter sAdapter;
    private String name = "", mac = "";

    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        listview = (ListView) this.findViewById(R.id.list);
        scan = (Button) this.findViewById(R.id.scan);

        scan.setVisibility(View.VISIBLE);
        listview.setVisibility(View.VISIBLE);

        scan.setOnClickListener(this);

        b_adapter = BluetoothAdapter.getDefaultAdapter();

        //turning on bluetooth
        if (!b_adapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
        } else {
            //do nothing
        }

        // broad cast
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);

        registerReceiver(mReceiver, filter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> view, View conVertview, int position,
                                    long Id) {
                // name and mac address of selected item in listview
                name = b_list.get(position).getName();
                mac = b_list.get(position).getAddress();
                Toast.makeText(SearchList.this, name + " - " + mac, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchList.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Searching......");
        alert = builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan:
                paired = b_adapter.getBondedDevices();
                b_adapter.startDiscovery();
                alert.show();

                break;
            default:
                break;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                b_list = new ArrayList<BluetoothDevice>();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                setAdapter(device);
                alert.dismiss();
            }
        }
    };


    public void setAdapter(BluetoothDevice device) {

        BluetoothVariable.device_name = new ArrayList<String>();
        BluetoothVariable.device_mac = new ArrayList<String>();
        device_list = new ArrayList<String>();

        b_list.add(device);

        for (int i = 0; i < b_list.size(); i++) {
            device_list.add(b_list.get(i).getName() + "\n" + b_list.get(i).getAddress());
            BluetoothVariable.device_name.add(b_list.get(i).getName());
            BluetoothVariable.device_mac.add(b_list.get(i).getAddress());
        }
        /*sAdapter = new SearchAdapter(SearchList.this, device_name, device_mac);*/

        //setting data in list view
        ArrayAdapter<String> list_ada = new ArrayAdapter<String>(SearchList.this, android.R.layout.simple_expandable_list_item_1, device_list);
        listview.setAdapter(list_ada);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
