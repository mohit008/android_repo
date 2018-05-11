package com.mohit.program.download;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.mohit.program.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author @ Mohit Soni on 11-05-2018 05:02 PM.
 */

public class StringFromHtmlSample extends Activity {

    private ProgressDialog progressDialog;
    private static final String key = "http://www.imdb.com/title/tt1617661/";

    // create activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Getting");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        new GetCod().execute(key);
    }

    /**
     * This class use {@link AsyncTask} to fetch link, find specific string from it
     * and set it {@link StringFromHtmlSample}
     *
     * @author Mohit Soni
     */
    public class GetCod extends AsyncTask<String, Integer, Void> {

        private String code = "", html_code = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }


        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            getCodeFromUrl(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.i("HTML", html_code);
        }

        /**
         * Parsing Url and Getting HTML code
         * @param url
         * @return
         */
        public Void getCodeFromUrl(String url) {
            try {
                URL obj = new URL(url);
                HttpURLConnection http = (HttpURLConnection) obj.openConnection();
                http.setRequestMethod("GET");
                int response = http.getResponseCode();
                Log.i("response", Integer.toString(response));

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"), 8);

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                reader.close();
                code = sb.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            code = code.replace("\n", "").replace(" ", "");
            findImgLink(code);
            return null;
        }

        /**
         * Find image source link
         * @param code
         * @return
         */
        public String findImgLink(String code) {
            int getsrcName = code.indexOf("src=\"http://ia.media-imdb.com/images/M/");
            int getitemprop = code.indexOf("itemprop=\"image\"");

            String s = Integer.toString(getsrcName);
            String i = Integer.toString(getitemprop);

            html_code = code.substring(getsrcName + 5, getitemprop - 1);

            //		Log.i("########", s);
            //		Log.i("********", i);
            Log.i("Link", html_code);
            return null;
        }
    }
}
