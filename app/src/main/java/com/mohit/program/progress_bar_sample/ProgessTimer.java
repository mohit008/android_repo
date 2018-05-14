package com.mohit.program.progress_bar_sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:32 PM.
 */

public class ProgessTimer extends Activity {
    TextView tvProgress;
    private ProgressBar pbHome;

    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    // 1 min = 60000
    private static long timer = 60000;
    private int status = 0;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_timer_layout);
        pbHome = (ProgressBar) findViewById(R.id.pbHome);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        new ShowWait().execute();
    }

    /**
     * start progress bar with time
     */
    public class ShowWait extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                public void run() {
                    while (mProgressStatus < 60) {
                        mProgressStatus += 1;

                        // Update the progress bar
                        mHandler.post(new Runnable() {
                            public void run() {
                                pbHome.setProgress(mProgressStatus);
                                tvProgress.setText(60 - mProgressStatus + "" + " sec");
                            }
                        });
                        try {
                            // 1 sec delay
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    public void setDialog() {
        loading = new ProgressDialog(this);
        loading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loading.setTitle("Wrong Answer");
        loading.setMessage("Please Wait for 2 min");
        loading.setMax(60);
        loading.setProgressPercentFormat(null);
        loading.setCancelable(false);
        loading.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.dismiss();
            }
        }, timer);
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (status = 0; status < timer; status++) {
                    try {
                        // 1 sec
                        Thread.sleep(1000);
                        loading.setProgress(status);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
