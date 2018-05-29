package com.mohit.program.runtime_permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


/**
 * Author @ Mohit Soni on 14-05-2018 05:18 PM.
 */

public class RuntimePermissionMaster {
    public final String TAG = getClass().getSimpleName();

    static boolean GRANTED = false;
    static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9090;

    /**
     * request for location permission at runtime
     */
    public void requestPermission(Context context, Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

    }

    /**
     * check if permission is there or not
     *
     * @param context
     * @param activity
     */
    public void selfPermission(Context context, Activity activity) {
        int status = ContextCompat.checkSelfPermission(context.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (status == PackageManager.PERMISSION_GRANTED) {
            GRANTED = true;
        }
    }
}
