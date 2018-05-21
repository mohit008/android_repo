package com.mohit.program.motion_event;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author @ Mohit Soni on 21-05-2018 03:05 PM.
 */

public class Ball extends View {

    Paint paint;

    public Ball(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int widthHalf = this.getMeasuredWidth() / 2;
        int heighthHalf = this.getMeasuredHeight() / 2;
        int radius = widthHalf;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(41, 128, 185));

        canvas.drawCircle(widthHalf, heighthHalf, radius, paint);
    }
}
