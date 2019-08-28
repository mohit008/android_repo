package com.mohit.program.broadcast.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * receiver
 * <receiver android:name=".IncomingCall">
 * <intent-filter>
 * <action android:name="android.intent.action.PHONE_STATE" />
 * </intent-filter>
 * <intent-filter>
 * <action android:name="android.intent.action.NEW_OUTGOING_CALL" />
 * </intent-filter>
 * </receiver>
 */
public class IncomingCall extends BroadcastReceiver {
    Context context;

    TelephonyManager telephony;

    public void onReceive(Context context, Intent intent) {
        MyPhoneStateListener phoneListener = new MyPhoneStateListener();
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }
}
