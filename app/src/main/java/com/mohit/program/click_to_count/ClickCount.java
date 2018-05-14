package com.mohit.program.click_to_count;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:14 PM.
 */

public class ClickCount extends Activity {
    int increase;
    int increment;

    // creating objects
    TextView digit;
    Button count;
    Button clear;
    RadioButton one;
    RadioButton two;
    RadioButton three;
    RadioButton four;
    RadioButton five;
    Vibrator vibrate;
    MediaPlayer count_play;
    MediaPlayer clear_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_count_layout);
        /*getting vibrating service from system*/
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        /*getting sound file from raw */
        count_play = MediaPlayer.create(this, R.raw.count);
        clear_play = MediaPlayer.create(this, R.raw.clear);
		/*initializing variable*/
        increase = 0;
		/*Change Font type from asset.
		 *You can find font file in assets*/
        Typeface font = Typeface.createFromAsset(getAssets(), "font/digital7.ttf");
		/*linking to XML widgets*/
        digit = (TextView) findViewById(R.id.digit);
        count = (Button) findViewById(R.id.count);
        clear = (Button) findViewById(R.id.clear);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
		/*Applying to TextView*/
        digit.setTypeface(font);
		/*changing behavior on touch */
        count.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#58FA58"), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
		/*reseting text field*/
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase = 0;
                digit.setText(Integer.toString(increase));
                vibrate.vibrate(40);
                clear_play.start();
            }
        });
        clear.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#FE2E2E"), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

    }

    /*Responding to radio button select*/
    public void OnClick(View view) {
        boolean checked = (((RadioButton) view).isChecked());
        switch (view.getId()) {
            case R.id.one:
                if (checked) {
				/*click listener for button*/
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            increment = increase += 1;
                            digit.setText(Integer.toString(increment));
						/*make vibration from 40 msec*/
                            vibrate.vibrate(40);
						/*paying sound on click*/
                            count_play.start();

                        }
                    });
                    // toasting massage on screen
                    Toast.makeText(this, "1x", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.two:
                if (checked) {
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            increment = increase += 2;
                            digit.setText(Integer.toString(increment));
                            vibrate.vibrate(40);
                            count_play.start();
                        }
                    });
                    Toast.makeText(this, "2x", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.three:
                if (checked) {
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            increment = increase += 3;
                            digit.setText(Integer.toString(increment));
                            vibrate.vibrate(40);
                            count_play.start();
                        }
                    });
                    Toast.makeText(this, "3x", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.four:
                if (checked) {
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            increment = increase += 4;
                            digit.setText(Integer.toString(increment));
                            vibrate.vibrate(40);
                            count_play.start();
                        }
                    });
                    Toast.makeText(this, "4x", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.five:
                if (checked) {
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            increment = increase += 5;
                            digit.setText(Integer.toString(increment));
                            vibrate.vibrate(40);
                            count_play.start();
                        }
                    });
                    Toast.makeText(this, "5x", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*Returning to state when application is switch (till on killed)*/
    @Override
    protected void onResume() {
        digit.setText(Integer.toString(increase));
        super.onResume();
    }
}
