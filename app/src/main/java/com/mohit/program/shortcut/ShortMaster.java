package com.mohit.program.shortcut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 17-05-2018 11:52 AM.
 */

public class ShortMaster extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        addshortcut();
    }

    /**
     * add short cut to home screen
     */
    public void addshortcut() {
        Intent addshort = new Intent(getApplicationContext(), ShortMaster.class);
        addshort.setAction(Intent.ACTION_MAIN);
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, R.string.app_name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, addshort);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher));
        getApplicationContext().sendBroadcast(addIntent);
    }

}
