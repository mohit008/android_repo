package com.mohit.program.map.location_with_map;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mohit.program.R;

import static com.mohit.program.map.location_with_map.LocationConstant.LOCATION_PERMISSION_GRANTED;
import static com.mohit.program.map.location_with_map.LocationConstant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

/**
 * Author @ Mohit Soni on 14-05-2018 05:17 PM.
 */

public class LocationActivity extends Activity implements LocationListener {
    public final static String TAG = "LocationActivity";

    Location location;
    double latitude, longitude;
    LocationConstant locationConstant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        // init variable
        locationConstant = new LocationConstant(this);

        // get permission at runtime
        locationConstant.getLocationPermission(this, this);

        initLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationConstant.removeLocationUpdate(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationConstant.removeLocationUpdate(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        locationConstant.removeLocationUpdate(this);
        finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        // callback if location changed
        if (location != null) {
            this.location = location;
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        Toast.makeText(LocationActivity.this, Double.toString(latitude) + " : " + Double.toString(longitude)
                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        LOCATION_PERMISSION_GRANTED = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LOCATION_PERMISSION_GRANTED = true;
                initLocation();
            }
        }
    }

    void initLocation() {
        // get current location
        location = locationConstant.getLocation(this, this);
        if (LocationConstant.canGetLocation) {
            try {
                if (location != null) {
                    onLocationChanged(location);
                } else {
                    location = locationConstant.getLocation(this, this);
                    Toast.makeText(this, "Location not available",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {
            locationConstant.showSettingsAlert(null, this);
        }
    }

    /**
     * use timer
     */
//    protected void getDirection() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        Toast.makeText(LocationActivity.this, " latitude : " +
//                                String.valueOf(location.getLatitude()) + "\n" + "longitue : " +
//                                String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
//                        onLocationChanged(location);
//                    }
//                });
//            }
//        }, 2000, 5000);
//    }
}
