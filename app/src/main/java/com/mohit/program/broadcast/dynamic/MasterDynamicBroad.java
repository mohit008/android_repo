package com.mohit.program.broadcast.dynamic;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 04:39 PM.
 */

public class MasterDynamicBroad extends Activity {

    private static final String TAG = "Dynamic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        if (!isServiceRunning()) {
            // start device status service
            startService(new Intent(this, BroadService.class));
        }
    }

    /**
     * check if service is running or not
     *
     * @return state
     */
    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (BroadService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // un register braodcast
        unregisterReceiver(receiver);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // register braodcast with key
        registerReceiver(receiver, new IntentFilter(DynamicBroadVariable.KEY_MESSAGE));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = null;
            if (intent != null) {
                msg = (String) intent.getExtras().get("msg");
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };

}
