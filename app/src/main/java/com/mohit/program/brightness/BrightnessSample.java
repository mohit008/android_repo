package com.mohit.program.brightness;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 04:10 PM.
 */

public class BrightnessSample extends Activity {

//    SeekBar seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.two_line_list_item);

        TextView dark = ((TextView) findViewById(android.R.id.text1));
        dark.setText("Dark");
        dark.setGravity(Gravity.CENTER);

        TextView bright = ((TextView) findViewById(android.R.id.text2));
        bright.setText("Bright");
        bright.setGravity(Gravity.CENTER);


//        seek = (SeekBar) findViewById(R.id.brightness_set);
//
//
//        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
//                float backLight = (float) progress / 100;
//                Toast.makeText(BrightnessSample.this,backLight + "",Toast.LENGTH_SHORT).show();
//
//                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//                layoutParams.screenBrightness = backLight;
//                getWindow().setAttributes(layoutParams);
//            }
//        });

        dark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // complete dark
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 0;
                getWindow().setAttributes(layoutParams);
            }
        });

        bright.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // complete bright
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 1;
                getWindow().setAttributes(layoutParams);
            }
        });
    }
}
