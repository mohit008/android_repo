package com.mohit.program.draw.draw_on_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author @ Mohit Soni on 21-05-2018 02:41 PM.
 */

public class MyDrawing extends View {

    Paint paint = new Paint();
    Path path = new Path();

    public MyDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

    }

    public void clear() {
        path.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // get position co-ordinate
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            /*Toast.makeText(GridWallPaperMaster.this,x+""+":"+y+"",Toast.LENGTH_SHORT).show();*/
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
            /*Toast.makeText(GridWallPaperMaster.this,x+""+y+"",Toast.LENGTH_SHORT).show();*/
                path.lineTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }
}
