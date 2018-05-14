package com.mohit.program.broadcast.dynamic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Author @ Mohit Soni on 14-05-2018 04:34 PM.
 */

public class BootBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "This is custom intent", Toast.LENGTH_SHORT).show();
    }

}
