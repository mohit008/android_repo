package com.mohit.program.bitmap_on_canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 26-04-2018 17:05.
 */

public class BitmapOnCanvasSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new BitmapView(this));
    }

    public class BitmapView extends View {

        private Bitmap bitmap;

        public BitmapView(Context context) {
            super(context);

            // allocate
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(bitmap, 10, 10, null);
        }
    }
}
