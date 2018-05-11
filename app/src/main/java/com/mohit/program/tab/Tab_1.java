package com.mohit.program.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 06:27 PM.
 */

public class Tab_1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        ((Button)findViewById(R.id.btItem)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.btItem)).setText("Tab 1");
    }
}
