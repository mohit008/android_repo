package com.mohit.program.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Author @ Mohit Soni on 14-05-2018 04:32 PM.
 */

public class MasterCustomBroadCast extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.test_list_item);

        TextView textView = ((TextView)findViewById(android.R.id.text1));
        textView.setText("Send Broad Cast");
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                broadcastIntent(v);
            }
        });
    }

    /**
     * send broadcast to class register for it
     *
     * @param view view
     */
    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.get.custom.INTENT");
        sendBroadcast(intent);
    }
}
