package com.mohit.program.download;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.text.Html;
import android.util.Log;

import com.mohit.program.R;

import java.io.IOException;

/**
 * Author @ Mohit Soni on 11-05-2018 06:14 PM.
 */

public class JsoupSample extends Activity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        new JeX().execute();

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Getting");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * This class open link and get get {@link Html} code,then in findImgLink method returns
     * string
     *
     * @author Mohit Soni
     */
    public class JeX extends AsyncTask<Void, Void, Void> {

        String code = "";
        private static final String key = "http://www.imdb.com/title/tt1617661/";

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = (Document) Jsoup.connect(key).get();
                org.jsoup.nodes.Element title = ((Element) doc).select("img").get(3);
                code = title.toString();
                code = code.replace(" ", "").replace("\n", "");

                findImgLink(code);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i("Getcode", code);
            super.onPostExecute(result);
        }

        /**
         * code string is passed to this method, which in returns log to
         * specific string
         *
         * @param code
         * @return
         */
        public String findImgLink(String code) {
            int getsrcName = code.indexOf("src");
            int getitemprop = code.indexOf("itemprop");

            String s = Integer.toString(getsrcName);
            String i = Integer.toString(getitemprop);
//            String link = code.substring(getsrcName+5, getitemprop-1);
            //			Log.i("########", s);
            //			Log.i("########", i);
            Log.i("Final Link", code);
            return null;
        }
    }
}
