package com.mohit.program.menus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 17-05-2018 11:46 AM.
 */

public class MenuMaster extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "New");
        menu.add(0, 2, 2, "Create");
        menu.add(0, 3, 3, "Open");
        menu.add(0, 4, 4, "Delete");
        menu.add(0, 5, 5, "Exit");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case (1):
                //----//
                return true;
            case (2):
                //---//
                return true;
            case (3):
                //---//
                return true;
            case (4):
                //---//
                return true;
            case (5):
                finish();
                return true;

        }
        return false;
    }

}
