package com.mohit.program.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Author @ Mohit Soni on 17-05-2018 11:55 AM.
 */

public class SMS_Receive extends BroadcastReceiver {

    SmsManager _smsmanager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle _bundle = intent.getExtras();
        try {
            if (_bundle != null) {
                final Object[] pdusObj = (Object[]) _bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String _senderNumber = phoneNumber;
                    String _message = currentMessage.getMessageBody();

                    //   Toast.makeText(context, _senderNumber+"\n"+_message, Toast.LENGTH_SHORT).show();
                    if (!_senderNumber.isEmpty() && !_message.isEmpty()) {
                        if (_senderNumber.equals("8103605864")) {
                            Intent _intent = new Intent(context, SMSMaster.class);
                            _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(_intent);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
