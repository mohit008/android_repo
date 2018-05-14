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
 * Author @ Mohit Soni on 14-05-2018 07:09 PM.
 */

public class DragAround extends Activity {
    boolean status = false;
    int width, height, image_length = 0, preper = 0, _y = 0, y_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_layout);

        ImageView ivWall = (ImageView) findViewById(R.id.ivWall);
        ivWall.setVisibility(View.VISIBLE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        width = size.x;
        height = size.y;

        /*Button show = (Button) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams dragParam = (LinearLayout.LayoutParams) text.getLayoutParams();
                dragParam.topMargin = (int) 0;
                dragParam.leftMargin = (int) 0;
                first = true;
                second = false;

                text.setVisibility(View.VISIBLE);
                ((LinearLayout.LayoutParams) text.getLayoutParams()).width  = width;
                text.setLayoutParams(dragParam);
            }
        });*/


        ivWall.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout.LayoutParams dragParam = (LinearLayout.LayoutParams) v.getLayoutParams();
                image_length = v.getWidth();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        int top = ((LinearLayout.LayoutParams) v.getLayoutParams()).topMargin;
                        status = true;
                        int percnatge_height = Math.abs((int) (height - top * 100) / height);
                        if (percnatge_height == 10 || percnatge_height == 20 || percnatge_height == 30) {
                            if (preper < percnatge_height) {
                                int ramain = ((percnatge_height * image_length) / 100);
                                int x = (image_length - (ramain));
                                preper = percnatge_height;
                            } else {
                                image_length = image_length + ((percnatge_height * image_length) / 100);
                                preper = percnatge_height;
                            }
                        }
                        dragParam.topMargin = _y + ((int) event.getRawY() - y_);
//                        ((LinearLayout.LayoutParams) v.getLayoutParams()).width = ;
                        if (((LinearLayout.LayoutParams) v.getLayoutParams()).topMargin < 0) {
                            dragParam.topMargin = 0;
                            preper = 0;
                        }
                        v.setLayoutParams(dragParam);
//                        Log.i("M", percnatge_height + ":" + x + ":" + ramain);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        status = true;
                        _y = dragParam.topMargin;
                        y_ = (int) event.getRawY();
                        Log.i("D", _y + ":" + y_);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                        status = false;
                        break;
                }
                return status;
            }
        });
    }
}
