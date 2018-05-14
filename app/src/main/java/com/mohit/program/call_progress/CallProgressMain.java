package com.mohit.program.call_progress;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:05 PM.
 */

public class CallProgressMain extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_view);
    }
}

