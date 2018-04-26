package com.mohit.program.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author @ Mohit Soni on 26-04-2018 16:26.
 */

public class CustomView extends View {

    Paint paint;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int widthHalf = this.getMeasuredWidth() / 2;
        int heighthHalf = this.getMeasuredHeight() / 2;
        int radius = 0;
        if (widthHalf > heighthHalf) {
            radius = widthHalf - 70;
        } else
            radius = heighthHalf - 70;

        // paint to customize view
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        //canvas drawing circle
        canvas.drawCircle(widthHalf, heighthHalf, radius, paint);
    }
}
