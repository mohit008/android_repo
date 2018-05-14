package com.mohit.program.notification;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Author @ Mohit Soni on 14-05-2018 06:18 PM.
 */

public class NotificationService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private NotificationListnerServiceReceiver notificationListnerServiceReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationListnerServiceReceiver = new NotificationListnerServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationVariable.NOTIFICATION_LISTENER_RECEIVER);
        registerReceiver(notificationListnerServiceReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(notificationListnerServiceReceiver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i(TAG, "**********  onNotificationPosted");
        Log.i(TAG, "ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent posted = new Intent(NotificationVariable.NOTIFICATION_RECEIVER);
        posted.putExtra("notification_event", "onNotificationPosted :" + sbn.getPackageName() + "\n");
        sendBroadcast(posted);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG, "********** onNOtificationRemoved");
        Log.i(TAG, "ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent removed = new Intent(NotificationVariable.NOTIFICATION_RECEIVER);
        removed.putExtra("notification_event", "onNotificationRemoved :" + sbn.getPackageName() + "\n");
        sendBroadcast(removed);
    }

    class NotificationListnerServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // clear all notification
            if (intent.getStringExtra("command").equals("clearall")) {
                NotificationService.this.cancelAllNotifications();
            }
            // get all notification
            else if (intent.getStringExtra("command").equals("get")) {
                Intent pre = new Intent(NotificationVariable.NOTIFICATION_RECEIVER);
                pre.putExtra("notification_event", "=====================");
                sendBroadcast(pre);
                int i = 1;
                for (StatusBarNotification sbn : NotificationService.this.getActiveNotifications()) {
                    Intent intent1 = new Intent(NotificationVariable.NOTIFICATION_RECEIVER);
                    intent1.putExtra("notification_event", i + " " + sbn.getPackageName() + "\n");
                    sendBroadcast(intent1);
                    i++;
                }
                Intent post = new Intent(NotificationVariable.NOTIFICATION_RECEIVER);
                post.putExtra("notification_event", "===== Notification List ====");
                sendBroadcast(post);
            }
        }
    }


}
