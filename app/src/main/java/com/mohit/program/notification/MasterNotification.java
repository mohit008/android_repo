package com.mohit.program.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mohit.program.R;

import java.util.Set;

/**
 * Author @ Mohit Soni on 14-05-2018 06:14 PM.
 */

public class MasterNotification extends Activity {
    private String TAG = this.getClass().getSimpleName();
    private int notification_id = 2;

    private NotificationReceiver notificationReceiver;
    String[] dialogButton = new String[]{"Alarm", "Create", "Clear", "List"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setVisibility(View.VISIBLE);

        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dialogButton));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setAlarm();
                }
                if (position == 1) {
                    // generate notification
                    NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    NotificationCompat.Builder ncomp = new NotificationCompat.Builder(MasterNotification.this);
                    ncomp.setContentTitle("My Notification");
                    ncomp.setContentText("Notification Listener Service Example");
                    ncomp.setTicker("Notification Listener Service Example");
                    ncomp.setSmallIcon(R.mipmap.ic_launcher);
                    ncomp.setAutoCancel(true);
                    nManager.notify((int) System.currentTimeMillis(), ncomp.build());
                }
                if (position == 2) {

                    // clear all notification
                    Intent i = new Intent(NotificationVariable.NOTIFICATION_LISTENER_RECEIVER);
                    i.putExtra("command", "clearall");
                    sendBroadcast(i);
                }
                if (position == 3) {

                    // get all notification
                    Intent i = new Intent(NotificationVariable.NOTIFICATION_LISTENER_RECEIVER);
                    i.putExtra("command", "get");
                    sendBroadcast(i);
                }
            }
        });
        setupNotification();
    }

    /**
     * notifying alarm
     */
    public void displaynotification() {
        // intent to second class when notification is clicked
        Intent intent = new Intent(this, Alarmnotify.class);
        intent.putExtra("notification_id", notification_id);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);

        // generate notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("New")
                .setContentText("hello")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pintent)
                .build();
        notification.vibrate = new long[]{100, 250, 100, 500};
        nm.notify(notification_id, notification);
    }

    /**
     * set alarm
     */
    public void setAlarm() {
        BroadcastReceiver alarmreceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                displaynotification();
                Toast.makeText(getBaseContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        };

        // register receiver
        registerReceiver(alarmreceiver, new IntentFilter("com.example.alaram_services"));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent("com.example.alaram_services"), 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0,
                0, pendingIntent);
    }

    /**
     * setting broadcastNLService alarmreceiver
     */
    private void setupNotification() {
        // register receiver
        notificationReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationVariable.NOTIFICATION_RECEIVER);
        registerReceiver(notificationReceiver, filter);

        // start service
        startService(new Intent(MasterNotification.this, NotificationListenerService.class));

        // check if listerner is allow to access notification
        Set<String> listenerlist = NotificationManagerCompat.getEnabledListenerPackages(this);
        boolean isallowed = false;
        for (String sd : listenerlist) {
            if (sd.equals(this.getClass().getPackage().getName())) {
                isallowed = true;
            }
        }

        if (!isallowed) {
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*manager.cancel(pendingIntent);
        unregisterReceiver(alarmreceiver);*/
        unregisterReceiver(notificationReceiver);
    }

    /**
     * display notification from panel
     */
    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String temp = intent.getStringExtra("notification_event");
            Log.w(TAG,temp);
        }
    }
}
