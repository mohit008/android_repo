package com.mohit.program.progress_bar_sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 06:48 PM.
 */

public class CustomProgressBar extends Activity {
    ArcProgress arc;
    ProgressBar p1,p2;

    CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_layout);

        p1 = (ProgressBar) findViewById(R.id.p1);
        p2 = (ProgressBar) findViewById(R.id.p2);

        p1.setProgress(70);
        p2.setProgress(50);

        arc = (ArcProgress) findViewById(R.id.arc_progress);
        arc.setProgress(55);
        arc.setUnfinishedStrokeColor(R.color.colorAccent);
        arc.setFinishedStrokeColor(R.color.color1);
        arc.setBottomText("");
        arc.setArcAngle(180);
        arc.setStrokeWidth(100);


        circularProgressBar = (CircularProgressBar)findViewById(R.id.yourCircularProgressbar);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 1000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(45, animationDuration);
    }
}
