package com.mohit.program.sms;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Window;
import android.widget.RelativeLayout;

import com.mohit.program.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This activity is show alert massage to user about security breach
 * <p>
 * <receiver android:name=".sms.SMS_Receive" > in Manifest
 * Author @ Mohit Soni on 17-05-2018 11:54 AM.
 */

public class SMSMaster extends Activity {
    //    private RelativeLayout _relative;
    private Timer _timer;
    private TimerTask _timertask;
    private static MediaPlayer error;
    private static Vibrator _vibrator;

    final Handler _handle = new Handler();

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_view);
//        _relative = (RelativeLayout) findViewById(R.id.main_layout);
        _vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
//        error = MediaPlayer.create(this, R.raw.alert_siren);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        error.start();
    }

    public void startTimer() {
        //set a new Timer
        _timer = new Timer();
        initializeTimerTask();
        _timer.schedule(_timertask, 100, 100);
    }

    public void stoptimertask() {
        if (_timer != null) {
            _timer.cancel();
            _timer = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stoptimertask();
        _vibrator.cancel();
        error.stop();
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stoptimertask();
        _vibrator.cancel();
        error.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stoptimertask();
        _vibrator.cancel();
        error.stop();
    }

    private void initializeTimerTask() {
        _timertask = new TimerTask() {

            @Override
            public void run() {
                _handle.post(new Runnable() {

                    @Override
                    public void run() {
                       /* _vibrator.vibrate(100);
                        ColorDrawable _color = (ColorDrawable) _relative.getBackground();
                        int _color_value = _color.getColor();
                        if (_color_value == Color.RED) {
                            _relative.setBackgroundColor(Color.BLACK);
                        }
                        if (_color_value == Color.BLACK) {
                            _relative.setBackgroundColor(Color.RED);
                        }*/
                    }
                });
            }
        };
    }
}
