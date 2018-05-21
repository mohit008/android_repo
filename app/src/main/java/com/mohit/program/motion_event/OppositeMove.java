package com.mohit.program.motion_event;

import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 21-05-2018 03:04 PM.
 */

public class OppositeMove extends Activity{

    Ball vertical;
    LinearLayout llProgressVer;
    LinearLayout.LayoutParams params;
    int xV=0, yV=0, width, height;
    Display display;
    Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        display= getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);

        width = point.x;
        height = point.y;

        Toast.makeText(this,width+":"+height,Toast.LENGTH_SHORT).show();
        llProgressVer = (LinearLayout) findViewById(R.id.ll_custom);

        params = new LinearLayout.LayoutParams(25,25);
        params.setMargins(xV,yV, 0, 0);
        vertical = new Ball(this,null);
        vertical.setLayoutParams(params);
        llProgressVer.addView(vertical);

        start();
    }
    public class DoSomething extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... param) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            params.setMargins(xV, yV, 0, 0);
            vertical.setLayoutParams(params);
            new DoSomething().execute();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                xV = width - (int)event.getX();
                yV = height - (int)event.getY();
                break;
        }
        return true;
    }


    public void start(){
        new DoSomething().execute();
    }


    @Override
    public void onBackPressed() {
        new DoSomething().cancel(true);
        super.onBackPressed();
    }
}
