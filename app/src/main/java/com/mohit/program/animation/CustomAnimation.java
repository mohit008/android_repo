package com.mohit.program.animation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.mohit.program.R;


/**
 * Author @ Mohit Soni on 27-04-2018 19:15.
 */

public class CustomAnimation extends Activity {
    ImageView image;

    float progress = 0.01f;
    boolean stat = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        image = (ImageView) findViewById(R.id.ivItem);

//        Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
//        image.setAnimation(ani);
        new Ex().execute();
    }

    public class Ex extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (stat) {
                try {
                    new Thread().sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress = progress + 0.05f;
                image.setScaleX(progress);
                image.setScaleY(progress);

                Log.i("progress", progress + "");

                if (progress > 2.0f) {
                    progress = 0.01f;
                }
            }
            return null;
        }
    }
}
