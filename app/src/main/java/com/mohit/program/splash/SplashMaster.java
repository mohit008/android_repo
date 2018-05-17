package com.mohit.program.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mohit.program.R;
import com.mohit.program.custom.MainCustomView;

/**
 * Author @ Mohit Soni on 17-05-2018 12:00 PM.
 */

public class SplashMaster extends Activity {

    private static long splash_trimer = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashMaster.this, MainCustomView.class);
                startActivity(intent);
                finish();
            }
        },splash_trimer);

    }
}
