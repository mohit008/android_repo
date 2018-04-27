package com.mohit.program.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 26-04-2018 16:26.
 */

public class MainCustomView extends Activity{

    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        ((ListView)findViewById(R.id.list)).setVisibility(View.GONE);
        customView = (CustomView)this.findViewById(R.id.customview);
        customView.setVisibility(View.VISIBLE);
    }
}
