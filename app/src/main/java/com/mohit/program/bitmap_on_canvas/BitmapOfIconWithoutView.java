package com.mohit.program.bitmap_on_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 26-04-2018 16:26.
 */

public class BitmapOfIconWithoutView {

    Paint paint;
    Context context;
    int bitwidth, bitheight;
    ArrayList<Bitmap> arrayList = new ArrayList<>();
    int widthHalf, heighthHalf, radius;
    Bitmap output, bit;
    Path path;

    Canvas canvas;
    int width = 128, height = 128;

    public BitmapOfIconWithoutView(Context context, ArrayList<Bitmap> arrayList) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        this.context = context;
        this.arrayList = arrayList;
        output = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(output);
    }

    public void set() {
        widthHalf = width / 2;
        heighthHalf = height / 2;
        bit = arrayList.get(0);
        bitwidth = widthHalf - bit.getWidth();
        bitheight = heighthHalf - bit.getHeight();
    }

    protected Bitmap onDraw() {
        set();
        if (widthHalf > heighthHalf) {
            radius = widthHalf - 10;
        } else
            radius = heighthHalf - 10;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAlpha(128);
        canvas.drawCircle(widthHalf, heighthHalf, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAlpha(100);
        canvas.drawCircle(widthHalf , heighthHalf, radius -5, paint);

        canvas.drawBitmap(getCroppedBitmap(createAllIcon()), 0, 0, paint);
        return output;
    }

    public Bitmap getCroppedBitmap(Bitmap src) {
        Bitmap output = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        path.addCircle(width / 2, width / 2, (width / 2) - 10, Path.Direction.CCW);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, 0, 0, paint);

        return output;
    }

    public Bitmap createAllIcon() {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
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
