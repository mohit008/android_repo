package com.mohit.program.bitmap_on_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 26-04-2018 16:26.
 */

public class BitmapOfIconWithView extends View {

    Paint paint;
    Context context;
    int bitwidth, bitheight;
    ArrayList<Bitmap> arrayList = new ArrayList<>();
    int widthHalf, heighthHalf, radius;
    Bitmap bit;
    Path path;

    public BitmapOfIconWithView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        this.context = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setDimen(ArrayList<Bitmap> arrayList) {
        this.arrayList = arrayList;
    }

    public void set() {
        widthHalf = getMeasuredWidth() / 2;
        heighthHalf = getMeasuredHeight() / 2;
        bit = arrayList.get(0);
        bitwidth = widthHalf - bit.getWidth();
        bitheight = heighthHalf - bit.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        set();
        widthHalf = getMeasuredWidth() / 2;
        heighthHalf = getMeasuredHeight() / 2;
        if (widthHalf > heighthHalf) {
            radius = widthHalf - 10;
        } else
            radius = heighthHalf - 10;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAlpha(128);
        canvas.drawCircle(widthHalf, heighthHalf, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAlpha(128);
        canvas.drawCircle(widthHalf , heighthHalf, radius -10, paint);

        canvas.drawBitmap((createAllIcon()), 0, 0, paint);
    }

//    public Bitmap getCroppedBitmap(Bitmap src) {
//        Bitmap output = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.BLUE);
//        path.addCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2, (getMeasuredWidth() / 2) - 10, Path.Direction.CCW);
//        canvas.drawPath(path, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(src, 0, 0, paint);
//
//        return output;
//    }

    public Bitmap createAllIcon() {
        Bitmap output = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        Canvas canvas1 = new Canvas(output);
        if (arrayList.size() == 2) {
            canvas1.drawBitmap((arrayList.get(0)), 0 + bitwidth / 2, bitheight + bitheight, paint);
            canvas1.drawBitmap(arrayList.get(1), widthHalf + bitwidth / 2, bitheight + bitheight, paint);
        }
        if (arrayList.size() == 3) {
            canvas1.drawBitmap((arrayList.get(0)), 0 + bitwidth / 2, 0 + bitheight / 2, paint);
            canvas1.drawBitmap(arrayList.get(1), widthHalf + bitwidth / 2, 0 + bitheight / 2, paint);
            canvas1.drawBitmap(arrayList.get(2), widthHalf - (bit.getWidth() / 2), heighthHalf + bitheight / 2, paint);
        }
        if (arrayList.size() >= 4) {
            canvas1.drawBitmap((arrayList.get(0)), 0 + bitwidth / 2, 0 + bitheight / 2, paint);
            canvas1.drawBitmap(arrayList.get(1), widthHalf + bitwidth / 2, 0 + bitheight / 2, paint);
            canvas1.drawBitmap(arrayList.get(2), 0 + bitwidth / 2, heighthHalf + bitheight / 2, paint);
            canvas1.drawBitmap((arrayList.get(3)), widthHalf + bitwidth / 2, heighthHalf + bitheight / 2, paint);
        }
        return output;
    }

}
