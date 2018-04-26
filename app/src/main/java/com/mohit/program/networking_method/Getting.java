package com.mohit.program.networking_method;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author @ Mohit Soni on 26-04-2018 17:46.
 */

public class Getting {

    public Getting(){
        new GettingTask().execute();
    }

    /**
     * This class download json data from link using {@link AsyncTask} and parse it to {@link JSONArray} and
     * {@link JSONObject} and then it feed this data to getter,setter
     * @author Mohit Soni
     */
    public class GettingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void result) {
        }
        @Override
        protected Void doInBackground(Void... params) {
            getJSONFromUrl();
            return null;
        }
    }

    public String getJSONFromUrl() {
        // url
        String url = "http://www.appresive.com/wp-content/jsongetbusiness_realestate.php?appid=183";
        try {
            // open connection to retrieve data
            URL obj = new URL(url);
            HttpURLConnection http = (HttpURLConnection)obj.openConnection();
            http.setRequestMethod("GET");
            int response = http.getResponseCode();

            // this is response code shows if page is accessible or not
            Log.i("response", Integer.toString(response));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
