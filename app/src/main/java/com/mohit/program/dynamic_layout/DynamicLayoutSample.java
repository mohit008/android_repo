package com.mohit.program.dynamic_layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author @ Mohit Soni on 26-04-2018 17:15.
 */

public class DynamicLayoutSample extends Activity {

    int height = 100;
    int width = LinearLayout.LayoutParams.MATCH_PARENT;
    LinearLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        params = new LinearLayout.LayoutParams(width, height);
        withInflater();
    }

    /**
     * pass context
     */
    public void withContext() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        // view
        TextView text = new TextView(this);
        text.setText("Hello");
        text.setLayoutParams(params);
        text.setGravity(Gravity.CENTER);

        // add to parent
        linearLayout.addView(text);
        setContentView(linearLayout);
    }

    /**
     * pass custom layout file
     */
    public void withInflater() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.removeAllViews();
        for (int i = 0; i < 10; i++) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View addView = layoutInflater.inflate(android.R.layout.test_list_item, null);

            TextView text = (TextView) addView.findViewById(android.R.id.text1);
            text.setText(i+"");
            text.setLayoutParams(params);
            text.setGravity(Gravity.CENTER);

            linearLayout.addView(addView);
        }
        setContentView(linearLayout);
    }
}
