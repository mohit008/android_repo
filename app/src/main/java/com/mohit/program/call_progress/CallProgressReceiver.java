package com.mohit.program.call_progress;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:07 PM.
 */

public class CallProgressReceiver extends BroadcastReceiver {

    private AlertDialog _alert;
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (action.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            CallProgressVariable.hold = isCallAudioActive(context);

            // when first call rings store number in local variable
            if (!CallProgressVariable.hold) {
                CallProgressVariable.previous_Number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }
        }
        if (action.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            // call disconnect
        }
        if (action.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            // if second call is picked up do something
            if (CallProgressVariable.hold) {
                Log.i("message", CallProgressVariable.previous_Number + "is onHold");
                showDialog(CallProgressVariable.previous_Number, context);
            }
        }
    }

    public boolean isCallAudioActive(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (manager.getMode() == AudioManager.MODE_IN_CALL) { //In call audio mode. A telephony call is established
            return true;
        } else {
            return false;
        }
    }

    public void showDialog(String number, final Context context) {
        final AlertDialog.Builder _builder = new AlertDialog.Builder(context);
        _builder.setIcon(R.mipmap.ic_launcher);
        _builder.setTitle("Alert");
        _builder.setPositiveButton("number is put onHold", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context.getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
            }
        });

        _alert = _builder.create();
        _alert.show();
    }
}
