package com.mohit.program.sensor;

import android.app.Activity;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 17-05-2018 11:50 AM.
 */

public class ProximityMaster extends Activity {
    SensorManager sensorMgr;
    Sensor sensor;

    private int mProgressStatus = 0;
    Handler mHandler = new Handler();

    WindowManager.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        layoutParams = getWindow().getAttributes();

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorMgr.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float hg = event.values[0];
            if (hg == 0.0) {
                new Inc().execute();
            }
            if (hg == 5.0) {
                new Dec().execute();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        sensorMgr.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(listener);
    }

    public class Inc extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                public void run() {
                    while (mProgressStatus < 10) {
                        mProgressStatus += 1;
                        mHandler.post(new Runnable() {
                            public void run() {
                                float backLight = (float) mProgressStatus / 10;
                                /*pb.setProgress(mProgressStatus);
                                tv.setText(10 - mProgressStatus + "");*/
                                layoutParams.screenBrightness = backLight;
                                getWindow().setAttributes(layoutParams);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class Dec extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                public void run() {
                    while (mProgressStatus > 0) {
                        mProgressStatus -= 1;
                        mHandler.post(new Runnable() {
                            public void run() {
                                float backLight = (float) mProgressStatus / 10;
                                /*pb.setProgress(mProgressStatus);
                                tv.setText(mProgressStatus + "");*/
                                layoutParams.screenBrightness = backLight;
                                getWindow().setAttributes(layoutParams);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
