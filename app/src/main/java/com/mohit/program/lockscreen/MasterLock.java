package com.mohit.program.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 05:57 PM.
 */

public class MasterLock extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        setContentView(R.layout.item);

        Button button = (Button) findViewById(R.id.btItem);
        button.setVisibility(View.VISIBLE);
        button.setText("Unlock");
        (button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                unlock(v);
            }
        });
        startService(new Intent(this, LockerService.class));
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void unlock(View view) {
        startService(new Intent(this, MasterLock.class));
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
