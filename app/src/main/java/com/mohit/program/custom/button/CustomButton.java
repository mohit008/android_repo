package com.mohit.program.custom.button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Author @ Mohit Soni on 16-05-2018 02:10 PM.
 */

public class CustomButton extends Button {

    CustomDrawable mBorder;

    public CustomButton(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setWillNotDraw(false);
        mBorder = new CustomDrawable(Color.parseColor("#FD659B"),
                Color.parseColor("#F76E63"),
                getPaddingLeft(),
                // radius
                80);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBorder.setBounds(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBorder.draw(canvas);
    }
}
