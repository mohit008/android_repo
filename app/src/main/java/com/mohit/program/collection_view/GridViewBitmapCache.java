package com.mohit.program.collection_view;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.LruCache;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mohit.program.R;

import java.lang.ref.WeakReference;

/**
 * Author @ Mohit Soni on 26-04-2018 18:33.
 */

public class GridViewBitmapCache extends Activity {


    GridBaseAdapter myGridBaseAdapter;
    CacheGridBaseAdapter myCacheGridBaseAdapter;

    GridView grid;

    private LruCache<String, Bitmap> mMemoryCache;
    static int[] image = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_sample);
        grid = (GridView) findViewById(R.id.grid);

        myGridBaseAdapter = new GridBaseAdapter(this, image);
        myCacheGridBaseAdapter = new CacheGridBaseAdapter(this, image);

        grid.setAdapter(myCacheGridBaseAdapter);

        final int memClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

        final int cacheSize = 1024 * 1024 * memClass / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in bytes rather than number of items.
                return bitmap.getByteCount();
            }

        };
    }


    public class GridBaseAdapter extends BaseAdapter {

        Context context;
        int[] getImage;

        GridBaseAdapter(Context cont, int[] getImage) {
            context = cont;
            this.getImage = getImage;
        }

        @Override
        public int getCount() {
            return getImage.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Bitmap bm = decodeSampledBitmapFromUri(image[position], 200, 200);
            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(new GridView.LayoutParams(250, 250));
            layout.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(bm);

            layout.addView(imageView);
            return layout;
        }


    }

    public class CacheGridBaseAdapter extends GridBaseAdapter {
        CacheGridBaseAdapter(Context cont, int[] getImage) {
            super(cont, getImage);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);

            final int imageKey = image[position];

            final Bitmap bm = getBitmapFromMemCache(imageKey);
            if (bm == null) {
                BitmapWorkerTask task = new BitmapWorkerTask(imageView);
                task.execute(imageKey);
            }

            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(new GridView.LayoutParams(250, 250));
            layout.setGravity(Gravity.CENTER);

            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(bm);

            layout.addView(imageView);
            return layout;

        }

    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromUri(params[0], 200, 200);
            addBitmapToMemoryCache(Integer.valueOf(params[0]), bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = (ImageView) imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

    }

    public void addBitmapToMemoryCache(int key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(Integer.toString(key), bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(int key) {
        return (Bitmap) mMemoryCache.get(Integer.toString(key));
    }

    public Bitmap decodeSampledBitmapFromUri(int path, int reqWidth, int reqHeight) {
        Bitmap bm = null;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeResource(getResources(), path, options);

        return bm;
    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        return inSampleSize;
    }
}
