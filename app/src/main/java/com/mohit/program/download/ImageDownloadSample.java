package com.mohit.program.download;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author @ Mohit Soni on 27-04-2018 12:30.
 */

public class ImageDownloadSample extends Activity {

    // stream to read
    InputStream in;

    public ImageDownloadSample() {
        // initialise
        new DownloadImageTask().execute("http://www.imgion.com/images/01/Pink-Lotus-Flower.jpg");
    }

    /**
     * creating class for download task process
     */
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urls = url[0];
            Bitmap bitmap = null;
            try {

                // opening input stream to url
                in = new java.net.URL(urls).openStream();

                // decoding bitmap
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bit) {
        }
    }
}
