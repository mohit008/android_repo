package com.mohit.program.networking_method;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author @ Mohit Soni on 26-04-2018 17:28.
 */

public class Posting {

    public Posting() {
        new PostingTask().execute();
    }

    /**
     * This class download  data from link using {@link AsyncTask} and parse it to {@link JSONArray} and
     * {@link JSONObject} and then it feed this data to getter,setter
     *
     * @author Mohit Soni
     */
    public class PostingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void result) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            posting();
            return null;
        }
    }

    /**
     * uses apache dependcy
     *
     * compile 'org.apache.httpcomponents:httpclient:4.5.5'
     *
     * @return
     */
    public String posting() {
        try {
            HttpClient client = new DefaultHttpClient();
            // here we have to pass link/url.
            HttpPost post = new HttpPost("//-----IP-----//");
            List<NameValuePair> list = new ArrayList<NameValuePair>(2);

            // add param
            list.add(new BasicNameValuePair("param1", "value"));
            list.add(new BasicNameValuePair("param2", "value"));
            post.setEntity(new UrlEncodedFormEntity(list));

            HttpResponse res = client.execute(post);

            // string response
            HttpEntity ent = res.getEntity();
            Log.i("reponse", EntityUtils.toString(ent));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public void post() {
//        try {
//            String data = URLEncoder.encode("api_token", "UTF-8")
//                    + "=" + URLEncoder.encode("indian-express-token-id", "UTF-8");
//            data += "&" + URLEncoder.encode("member_email", "UTF-8") + "="
//                    + URLEncoder.encode("hemant.bhattrai@webdunia.net", "UTF-8");
//            data += "&" + URLEncoder.encode("member_password", "UTF-8")
//                    + "=" + URLEncoder.encode("123456", "UTF-8");
//
//
//            String text = "";
//            BufferedReader reader = null;
//            URL url = new URL(BASE_URL);
//            URLConnection conn = url.openConnection();
//            conn.setDoOutput(true);
//            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//            wr.write(data);
//            wr.flush();
//
//            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            text = sb.toString();
//            reader.close();
//        } catch (Exception ex) {
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
}
