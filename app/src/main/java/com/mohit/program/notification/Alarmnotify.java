package com.mohit.program.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 06:22 PM.
 */

public class Alarmnotify extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(getIntent().getExtras().getInt("0"));
    }
}
