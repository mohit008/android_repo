package com.mohit.program.database.external;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 03:48 PM.
 */

public class ExternalDataSample extends Activity {

    DBManager dbAdapter;
    String value = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        dbAdapter = DBManager.getInstance(this);
        dbAdapter.open();
        Cursor c = dbAdapter.getData("4");
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    value = c.getString(c.getColumnIndex("name"));
                    System.out.println("fir ::" + value);
                } while (c.moveToNext());
            }
        }
        dbAdapter.close();
    }
}
