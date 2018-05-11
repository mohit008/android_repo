package com.mohit.program.motion_event;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 01:47 PM.
 */

public class DragNDrop extends Activity {

    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        // view
        final ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        imageView.setBackgroundResource(R.mipmap.ic_launcher);

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData clip = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(view);
                    view.startDrag(clip, shadow, imageView, 0);
                    view.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        imageView.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View view, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                        Log.i("ACTION_DRAG_STARTED", "STARTED");
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        int x_chord = (int) event.getX();
                        int y_chord = (int) event.getY();

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        x_chord = (int) event.getX();
                        y_chord = (int) event.getY();

                        layoutParams.leftMargin = x_chord;
                        layoutParams.rightMargin = y_chord;

                        view.setLayoutParams(layoutParams);

                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        x_chord = (int) event.getX();
                        y_chord = (int) event.getY();

                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        // add to parent
        linearLayout.addView(imageView);
        setContentView(linearLayout);
    }
}
