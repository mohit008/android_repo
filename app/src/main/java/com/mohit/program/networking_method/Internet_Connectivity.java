package com.mohit.program.networking_method;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 03:11 PM.
 */

public class Internet_Connectivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        if (!CheckConnection()) {
            AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
            errorDialog.setTitle("Error");
            errorDialog.setMessage("No internet connection.");
            errorDialog.setNeutralButton("Dismiss",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog error = errorDialog.create();
            error.show();
        }
    }

    /**
     * check if net connection is there
     *
     * @return
     */
    public boolean CheckConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
