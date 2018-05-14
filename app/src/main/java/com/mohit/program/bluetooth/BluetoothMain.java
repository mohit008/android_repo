package com.mohit.program.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mohit.program.R;

import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 14-05-2018 11:16 AM.
 */

public class BluetoothMain extends Activity implements View.OnClickListener {

    ListView listview;
    ArrayList<String> list;
    Button paired, search;
    BluetoothAdapter b_adapter;
    ArrayList<BluetoothDevice> b_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_main);
        listview = (ListView) this.findViewById(R.id.list);
        paired = (Button) this.findViewById(R.id.paired);
        search = (Button) this.findViewById(R.id.search);

        paired.setOnClickListener(this);
        search.setOnClickListener(this);

        /*b_adapter = BluetoothAdapter.getDefaultAdapter();
        if(!b_adapter.isEnabled()){
			Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(intent, 0);
		}
		IntentFilter filter = new IntentFilter();

		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

		registerReceiver(mReceiver, filter);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                startActivity(new Intent(BluetoothMain.this, SearchList.class));
                break;

            default:
                break;
        }
    }

    /*private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                b_list = new ArrayList<BluetoothDevice>();

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                setAdapter(device);
            }
        }
    };*/

    /*public void setAdapter(BluetoothDevice device) {
        list = new ArrayList<String>();
        b_list.add(device);

        for (int i = 0; i < b_list.size(); i++) {
            list.add(b_list.get(i).getName() + "\n" + b_list.get(i).getAddress());
        }
        ArrayAdapter<String> getList = new ArrayAdapter<String>(BlueTooth_Main.this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(getList);

    }*/

    /*@Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }*/
}
