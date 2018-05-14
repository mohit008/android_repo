package com.mohit.program.map.location_with_map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;


/**
 * Author @ Mohit Soni on 14-05-2018 05:18 PM.
 */

public class LocationConstant {
    public final static String TAG = "LocationActivity";

    static LocationManager locationManager;
    static Location location;
    static boolean getGPS = false, getNetwork = false, canGetLocation = false;
    static double latitude, longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000; // 1 minute

    static boolean LOCATION_PERMISSION_GRANTED = false;
    static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9090;

//    String provider;
//    Criteria criteria;

    /**
     * show alert setting to enable location setting
     *
     * @param provider value
     */
    public void showSettingsAlert(String provider, final Context context) {
        if (TextUtils.isEmpty(provider)) {
            provider = "Network/GPS location";
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Location settings");
        alertDialog.setMessage(provider + " is disabled. Do you want to enable?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    LocationConstant(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

//    public Location getByCriteria(Context context){
//        location = getLocation();
//        if (canGetLocation) {
//            try {
//                criteria = new Criteria();
//                provider = locationManager.getBestProvider(criteria, false);
//                location = getLocation();
//                if (location != null) {
//                    Log.i(TAG, "Provider " + provider + " has been selected.");
//                    return  location;
//                } else {
//                    location = getLocation();
//                    Toast.makeText(context, "Location not available",
//                            Toast.LENGTH_SHORT).show();
//                }
//            } catch (SecurityException e) {
//                e.printStackTrace();
//            }
//        } else {
//            showSettingsAlert(provider,context);
//        }
//    }

    /**
     * get location which availbale provider
     *
     * @return location
     */
    public Location getLocation(Context context, LocationListener locationListener) {
        try {
            getGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            getNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!getGPS && !getNetwork) {
            } else {
                canGetLocation = true;
                if (getNetwork) {
                    try {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        try {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                if (getGPS) {
                    if (location == null) {
                        try {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            try {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            } catch (SecurityException e) {
                                e.printStackTrace();
                            }
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     * calculate distance from one location to other
     *
     * @param lat1 latitude
     * @param lng1 longitute
     * @param lat2 latitude
     * @param lng2 longitute
     * @return distance in meter
     */
    private static long calculateDistance(double lat1, double lng1, float lat2, float lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        long distanceInMeters = Math.round(6371000 * c);
        return distanceInMeters;
    }


    /**
     * remove listener
     */
    public void removeLocationUpdate(LocationListener locationListener) {
        try {
            locationManager.removeUpdates(locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * request for location permisson at runtime
     */
    public void getLocationPermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LOCATION_PERMISSION_GRANTED = true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


}
