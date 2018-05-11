package com.mohit.program.download;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


import com.mohit.program.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mohit.soni on 26-04-2018.
 */

public class TextDownloadSample extends Activity {

    // object
    ProgressDialog mProgressDialog;
    TextView text;

    // stream to read
    InputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_download);

        // initialise
        ((ScrollView) findViewById(R.id.sb)).setVisibility(View.VISIBLE);
        text = (TextView) findViewById(R.id.text);

        mProgressDialog = new ProgressDialog(TextDownloadSample.this);
        mProgressDialog.setTitle("Downloading...");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new Download_text().execute("https://www.le.ac.uk/oerresources/bdra/html/page_09.htm");
    }

    /**
     * creating class for download process task
     */

    public class Download_text extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url) {

            String urls = url[0];
            String texts = "";

            try {
                // opening input stream to url
                in = new java.net.URL(urls).openStream();

                // Reads text from a character-input stream,
                // buffering characters so as to provide for the
                // efficient reading of characters, arrays, and lines.
                //
                //
                // An InputStreamReader is a bridge from byte streams to character streams:
                // It reads bytes and decodes them into characters using a specified charset.

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        in, "iso-8859-1"), 8);

	        	// StringBuilder objects are like String objects, except they can be modified.
                StringBuilder sb = new StringBuilder();
                String line ;
				// read content
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                texts = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return texts;

        }

        @Override
        protected void onPostExecute(String str) {
			// eleminating html tag from content
            String get = Html.fromHtml(str).toString();

            text.setText(get);
            mProgressDialog.dismiss();
        }
    }
}
