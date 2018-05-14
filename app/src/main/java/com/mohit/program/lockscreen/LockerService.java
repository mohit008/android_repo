package com.mohit.program.lockscreen;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Author @ Mohit Soni on 14-05-2018 05:58 PM.
 */

public class LockerService extends Service {

    BroadcastReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        KeyguardManager kManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        KeyguardManager.KeyguardLock key = kManager.newKeyguardLock(KEYGUARD_SERVICE);
        key.disableKeyguard();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);

        receiver = new LockerReceiver();
        registerReceiver(receiver, filter);

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
