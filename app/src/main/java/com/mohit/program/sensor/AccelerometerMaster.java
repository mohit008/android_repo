package com.mohit.program.sensor;

import android.app.Activity;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 17-05-2018 11:48 AM.
 */

public class AccelerometerMaster extends Activity {
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
        sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMgr.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x1 = event.values[0];
            float y1 = event.values[1];
            float z1 = event.values[2];

            /*x.setText(Float.toString(x1));
            y.setText(Float.toString(y1));
            z.setText(Float.toString(z1));*/

            float backLight = (float) y1 / 10;
            layoutParams.screenBrightness = Math.abs(backLight);
            getWindow().setAttributes(layoutParams);
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

}
