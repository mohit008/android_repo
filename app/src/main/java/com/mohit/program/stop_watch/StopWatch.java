package com.mohit.program.stop_watch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mohit.program.R;


/**
 * This is a simple example stop watch
 * contains start,pause and stop function.
 * it is completed on Tuesday January 21,2014
 *
 * Created by Mohit Soni on January 21,2014
 */

public class StopWatch extends Activity {

    // objects
    private static TextView hour, min, sec, mili, current;
    private static Button update, stop, pause;

    // run thread */
    static Handler han = new Handler();

    final static Thread clock = new Thread(new Clock());
    final static Thread stop_clock = new Thread(new StopClock());
    final static Thread resume = new Thread(new Resume());
    final static Thread paused = new Thread(new Paused());

    // local variables
    private static boolean ok = false;
    static long starttime = 0L;
    static long timeswap = 0L;
    static long finaltime = 0L;
    static long currentTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_watch);

        hour = (TextView) findViewById(R.id.hour);
        min = (TextView) findViewById(R.id.min);
        sec = (TextView) findViewById(R.id.sec);
        mili = (TextView) findViewById(R.id.mili);
        update = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        current = (TextView) findViewById(R.id.textView1);

        // click listener
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ok) {
                    // pause
                    timeswap = +currentTime;
                    current.setText(Long.toString(timeswap));

                    // set paused value
                    han.postDelayed(paused, 0);
                }
                pause.setEnabled(false);
                update.setEnabled(true);
                stop.setEnabled(false);

				// changing ok value for other button to access
                ok = false;
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!ok) {
					// resume
                    if (currentTime > timeswap) {
                        starttime = SystemClock.uptimeMillis();
                        han.postDelayed(resume, 0);

                    } else {
					// start new
                        starttime = SystemClock.uptimeMillis();
                        han.postDelayed(clock, 0);
                    }

                }
                pause.setEnabled(true);
                update.setEnabled(false);
                stop.setEnabled(true);

                ok = true;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ok) {
                    starttime = SystemClock.uptimeMillis();
                    han.postDelayed(stop_clock, 0);
                }
                stop.setEnabled(false);
                update.setEnabled(true);
                pause.setEnabled(false);

                ok = false;
            }
        });
    }

    /**
     * making thread to calculate and setting data
     */
    public static class Clock implements Runnable {
        @Override
        public void run() {

            currentTime = (int) ((SystemClock.uptimeMillis()) - starttime);
            int milis = (int) ((currentTime));
            int second = (int) ((currentTime / 1000) % 60);
            int mini = (int) (((currentTime / 1000) / 60) % 60);
            int hours = (int) ((((currentTime / 1000) / 3600)) % 24);

            mili.setText(Integer.toString(milis));
            sec.setText(Integer.toString(second));
            min.setText(Integer.toString(mini));
            hour.setText(Integer.toString(hours));
            han.postDelayed(this, 0);
        }
    }

    public static class Resume implements Runnable {
        @Override
        public void run() {
					/*transfer paused value to watch to resume*/
            currentTime = (int) (((SystemClock.uptimeMillis()) - starttime) + timeswap);
            int milis = (int) ((currentTime));
            int second = (int) ((currentTime / 1000) % 60);
            int mini = (int) (((currentTime / 1000) / 60) % 60);
            int hours = (int) ((((currentTime / 1000) / 3600)) % 24);

            mili.setText(Integer.toString(milis));
            sec.setText(Integer.toString(second));
            min.setText(Integer.toString(mini));
            hour.setText(Integer.toString(hours));
            han.postDelayed(this, 0);
        }
    }

    public static class StopClock implements Runnable {
        @Override
        public void run() {
            currentTime = (int) (SystemClock.uptimeMillis() - starttime);
            mili.setText(Integer.toString(0));
            sec.setText(Integer.toString(0));
            min.setText(Integer.toString(0));
            hour.setText(Integer.toString(0));
            han.postDelayed(this, 0);
        }
    }

    /**
     * This is to show the time at where watch is stopped it does not provide any calculation
     **/
    public static class Paused implements Runnable {
        @Override
        public void run() {
            int milis = (int) ((timeswap));
            int second = (int) ((timeswap / 1000) % 60);
            int mini = (int) (((timeswap / 1000) / 60) % 60);
            int hours = (int) ((((timeswap / 1000) / 3600)) % 24);
            mili.setText(Integer.toString(milis));
            sec.setText(Integer.toString(second));
            min.setText(Integer.toString(mini));
            hour.setText(Integer.toString(hours));
            han.postDelayed(this, 0);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        new Paused();
        super.onRestart();
    }
}
