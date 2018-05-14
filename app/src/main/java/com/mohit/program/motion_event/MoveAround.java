package com.mohit.program.motion_event;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 07:08 PM.
 */

public class MoveAround extends Activity {
    boolean status = false;

    int _y = 0, y_ = 0, _x = 0, x_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_layout);

        ImageView ivWall = (ImageView) findViewById(R.id.ivWall);
        ivWall.setVisibility(View.VISIBLE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ivWall.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout.LayoutParams dragParam = (LinearLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE: {
                        status = true;
                        Log.i("s", "Second");
                        // block from getting out from screen
                        if (((LinearLayout.LayoutParams) v.getLayoutParams()).topMargin < 0) {
                            dragParam.topMargin = 0;
                        }
                        if (((LinearLayout.LayoutParams) v.getLayoutParams()).leftMargin < 0) {
                            dragParam.leftMargin = 0;
                        }
                        dragParam.topMargin = _y + ((int) event.getRawY() - y_);
                        dragParam.leftMargin = _x + ((int) event.getRawX() - x_);

                        v.setLayoutParams(dragParam);

                        Log.i("margin", ((LinearLayout.LayoutParams) v.getLayoutParams())
                                .topMargin + " : " + ((LinearLayout.LayoutParams) v.getLayoutParams()).leftMargin);

                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        break;
                    }

                    // collect last touch position
                    case MotionEvent.ACTION_DOWN: {
                        status = true;
                        _y = dragParam.topMargin;
                        y_ = (int) event.getRawY();
                        Log.i("D", _y + ":" + y_);

                        _x = dragParam.leftMargin;
                        x_ = (int) event.getRawX();
                        Log.i("D", _x + ":" + x_);
                        break;
                    }
                    // out of touch
                    case MotionEvent.ACTION_CANCEL:
                        status = false;
                        break;
                }
                return status;
            }
        });
    }
}
