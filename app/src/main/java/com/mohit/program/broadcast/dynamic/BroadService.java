package com.mohit.program.broadcast.dynamic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author @ Mohit Soni on 14-05-2018 04:37 PM.
 */

public class BroadService extends Service {
    private static final String TAG = "Service [BroadService]";
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        resendBroadcast();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroyed");

        // stop timer
        timer.cancel();
    }


    protected void resendBroadcast() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                broadcastIntent("Hello");
            }
        }, 3000, 3000);  // first time, periodic time
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * send broadcast to activity registered with key
     *
     * @param msg string
     */
    public void broadcastIntent(String msg) {
        Intent intent = new Intent();
        // identify broadcast
        intent.setAction(DynamicBroadVariable.KEY_MESSAGE);
        intent.putExtra("msg", msg);
        sendBroadcast(intent);
    }
}
