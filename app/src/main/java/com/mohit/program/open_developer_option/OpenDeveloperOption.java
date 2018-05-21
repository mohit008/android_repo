package com.mohit.program.open_developer_option;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Author @ Mohit Soni on 21-05-2018 05:08 PM.
 * Add this to manifest if you want this to not display in recent app
 * <p>
 *
 * <activity android:name=".OpenDeveloperOption"
 * android:excludeFromRecents="true">
 */

public class OpenDeveloperOption extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoDisplay);
        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
        finish();
    }
}
