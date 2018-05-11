package com.mohit.program.download;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.mohit.program.R;
import com.mohit.program.util.Bean;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 11-05-2018 05:18 PM.
 */

public class JsonFromUrl extends Activity {
    private ArrayList<Bean> feedList = null;
    private ProgressDialog progressDialog;

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

        new DownloadTask().execute("http://mysafeinfo.com/api/data?list=englishmonarchs&format=json");

    }

    /**
     * This class download json data from link using {@link AsyncTask} and parse it to {@link JSONArray} and
     * {@link JSONObject} and then it feed this data to {@link Bean} at getter,setter
     *
     * @author Mohit Soni
     */
    public class DownloadTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            getJSONFromUrl(url);
            return null;
        }
    }

    /**
     * use when fetch {@link JSONArray} online from url
     *
     * @param url
     * @return
     */
    public String getJSONFromUrl(String url) {
        String json_string = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection http = (HttpURLConnection) obj.openConnection();
            http.setRequestMethod("GET");
            int response = http.getResponseCode();
            Log.i("response", Integer.toString(response));
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();

            json_string = sb.toString();
            Log.i("json_string", json_string);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseJson(json_string);
        return null;
    }

    /**
     * parse data
     * @param json
     */
    public void parseJson(String json) {
        try {
            /*
             * for array name is not null use
             * try {
             * 			JSONObject jObj = new JSONObject(json);
             * } catch (JSONException e) {
             * 			Log.e("JSON Parser", "Error parsing data " + e.toString());
             * }
             * JSONArray root = new JSONArray(jObj);
             */

            JSONArray root = new JSONArray(json);
            feedList = new ArrayList<Bean>();
            for (int i = 0; i < root.length(); i++) {
                JSONObject att = (JSONObject) root.getJSONObject(i);
                Bean item = new Bean();
                item.setText(att.getString("nm"));
                item.setId(att.getString("cty"));

                //feeding
                feedList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
