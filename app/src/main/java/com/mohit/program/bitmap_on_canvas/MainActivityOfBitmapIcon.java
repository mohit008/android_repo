package com.mohit.program.bitmap_on_canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.ArrayList;

public class MainActivityOfBitmapIcon extends AppCompatActivity {
    ArrayList<Bitmap> arrayList = new ArrayList<>();
    BitmapOfIconWithView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_bitmap);
        customView = (BitmapOfIconWithView) findViewById(R.id.customview);

        for (int i = 0; i < 2; i++) {
            arrayList.add(drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher)));
        }
        customView.setDimen(arrayList);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        Bitmap output = new BitmapOfIconWithoutView(this,arrayList).onDraw();
        tv1.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(getResources(),output), null, null);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
//        View root = icon.getRootView();
//        Bitmap screenshot = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(screenshot);
//        root.layout(0, 0, root.getLayoutParams().width, root.getLayoutParams().height);
//        root.draw(canvas);
//        BaseSettingPreviewActivity.folder.put(0,icon);
        return bitmap;
    }
}
