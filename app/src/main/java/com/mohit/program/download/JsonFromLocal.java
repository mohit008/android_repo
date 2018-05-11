package com.mohit.program.download;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mohit.program.R;
import com.mohit.program.util.Bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 11-05-2018 05:58 PM.
 */

public class JsonFromLocal extends Activity {
    private ArrayList<Bean> feedList = new ArrayList<>();
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

        new FetchJsonStringLocal().execute();
    }

    public class FetchJsonStringLocal extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            LoadJson();
            return null;
        }

        /**
         * use when fetch {@link JSONArray} from local directory (../assets)
         *
         * @return
         */
        public void LoadJson() {
            try {
                InputStream in = getAssets().open("b.json");
                int size = in.available();

                byte[] buffer = new byte[size];
                in.read(buffer);

                String json_string = new String(buffer, "UTF-8");
                in.close();
                parseJson(json_string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * parse json
         * @param ouput
         */
        public void parseJson(String ouput) {
            try {

                JSONObject jObj = new JSONObject(ouput);
                JSONArray root = jObj.getJSONArray("detail");

                for (int i = 0; i < root.length(); i++) {
                    JSONObject att = (JSONObject) root.getJSONObject(i);
                    Bean bean =  new Bean();

                    String id = att.getString("id");
                    String f_name = att.getString("first name");

                    JSONArray rootv2 = att.getJSONArray("educational");
                    ArrayList<Bean> feedList2 = new ArrayList<>();
                    for (int j = 0; j < rootv2.length(); j++) {
                        JSONObject attv2 = (JSONObject) rootv2.getJSONObject(j);
                        Bean bean1 =  new Bean();

                        String education_title = attv2.getString("title");
                        String education_degree = attv2.getString("degree");

                        bean1.setId(education_title);
                        bean1.setText(education_degree);

                        feedList2.add(bean1);
                    }
                    bean.setId(id);
                    bean.setText(f_name);
                    bean.setArrayList(feedList2);

                    feedList.add(bean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
