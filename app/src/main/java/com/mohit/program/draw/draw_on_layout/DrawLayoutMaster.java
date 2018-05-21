package com.mohit.program.draw.draw_on_layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 21-05-2018 02:40 PM.
 */

public class DrawLayoutMaster extends Activity {

    Button clear_signature;
    LinearLayout linearLayout;
    MyDrawing drawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature);

        clear_signature = (Button) findViewById(R.id.clear_signature);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        drawing = new MyDrawing(this, null);
        drawing.setBackgroundColor(Color.WHITE);
        linearLayout.addView(drawing, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

        clear_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawing.clear();
            }
        });

    }
}
