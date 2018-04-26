package com.mohit.program.networking_method;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.mohit.program.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;

/**
 * Author @ Mohit Soni on 26-04-2018 18:16.
 */

public class ImageDownload extends AsyncTask<String, Void, Bitmap> {

    private int position;
    private ImageView imageView;

    public ImageDownload(ImageView imageView) {
        this.imageView = imageView;
        imageView.setTag(position);
        imageView.setImageBitmap(null);
    }

    public ImageDownload(int position) {
        this.position = position;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }
        if (imageView != null) {
            if (bitmap != null && ((Integer) position == this.position)) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.mipmap.ic_launcher));
            }
        }
    }

    static Bitmap downloadBitmap(String url) {
        if (URLUtil.isValidUrl(url)) {
            final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
            final HttpGet getRequest = new HttpGet(url);
            try {
                HttpResponse response = client.execute(getRequest);
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Log.w("ImageDownloader", "Error " + statusCode
                            + " while retrieving bitmap from " + url);
                    return null;
                }
                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = entity.getContent();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                getRequest.abort();
                Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
            } finally {
                if (client != null) {
                    client.close();
                }
            }
            return null;
        }
        return null;
    }
}
