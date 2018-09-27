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

    int _y = 0, y_ = 0, _x = 0, x_ = 0, rightmargin = 100, bottommargin = 100;
    boolean withZoom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_layout);

        ImageView ivWall = (ImageView) findViewById(R.id.ivWall);
        ivWall.setVisibility(View.VISIBLE);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final int padeWidth = size.x;
        final int pageHeight = size.y;
        ivWall.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout.LayoutParams dragParam = (LinearLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE: {
                        status = true;
                        int top = 0, left = 0;
                        top = _y + ((int) event.getRawY() - y_);
                        left = _x + ((int) event.getRawX() - x_);

                        // when zoom out
                        if (!withZoom) {
                            // reject less 0
                            top = Math.max(0, top);
                            left = Math.max(0, left);

                            // reject out of right and bottom end
                            if (top > pageHeight - v.getHeight()) {
                                top = pageHeight - v.getHeight();
                            }
                            if (left > padeWidth - v.getWidth()) {
                                left = padeWidth - v.getWidth();
                            }
                        }
                        // when zoom in
                        else {
                            // get left when right of object touch screen edge
                            if (v.getRight() < padeWidth) {
                                rightmargin = dragParam.leftMargin;
                            }
                            if (rightmargin < 0) {
                                // not less then right touch
                                left = Math.max(Math.min(0, left), rightmargin);
                            } else {
                                left = Math.min(0, left);
                            }

                            // get top when bottom of object touch screen edge
                            if (v.getBottom() < pageHeight) {
                                bottommargin = dragParam.topMargin;
                            }
                            if (bottommargin < 0) {
                                top = Math.max(Math.min(0, top), bottommargin);
                            } else {
                                top = Math.min(0, top);
                            }
                        }
                        dragParam.topMargin = top;
                        dragParam.leftMargin = left;

                        v.setLayoutParams(dragParam);
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
