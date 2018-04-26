package com.mohit.program.dynamic_layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author @ Mohit Soni on 26-04-2018 17:15.
 */

public class DynamicLayout extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int height = 100;
        int width = LinearLayout.LayoutParams.MATCH_PARENT;

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

        // view
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);

        TextView text = new TextView(this);
        text.setText("Hello");
        text.setLayoutParams(params);
        text.setGravity(Gravity.CENTER);

        // add to parent
        linearLayout.addView(text);
        setContentView(linearLayout);
    }
}
