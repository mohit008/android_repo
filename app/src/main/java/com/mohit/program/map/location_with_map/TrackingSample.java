package com.mohit.program.map.location_with_map;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mohit.program.R;

import java.util.ArrayList;

import static com.mohit.program.map.location_with_map.LocationConstant.LOCATION_PERMISSION_GRANTED;
import static com.mohit.program.map.location_with_map.LocationConstant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

/**
 * Author @ Mohit Soni on 14-05-2018 05:18 PM.
 */

public class TrackingSample extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    public final static String TAG = "TrackingSample";

    // map
    GoogleMap map;
    SupportMapFragment mapFragment;

    Location location;

    Polyline line = null;
    ArrayList<LatLng> route = new ArrayList<>();
    ArrayList<String> route_location = new ArrayList<>();

    ProgressDialog loading;

    double latitude, longitude;

    LocationConstant locationConstant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlocation_activity_layout);

        // init variable
        locationConstant = new LocationConstant(this);

        // get permission at runtime
        locationConstant.getLocationPermission(this,this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        map.getUiSettings().setAllGesturesEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);

        initLocation();
    }

    /**
     * add maker to map with list data
     */
    public void updateMap() {
        // remove all marker from map
        map.clear();
        if (location != null) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            route.add(loc);
            route_location.add(Double.toString(loc.latitude)+":"+ Double.toString(loc.longitude));

            // add marker to map
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources()
                    .getDrawable(R.mipmap.ic_launcher);
            Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw.getBitmap()
                    , 50, 50, false);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(loc)
                    .title("You")
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

            map.addMarker(markerOptions);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(loc, 14);
            map.moveCamera(cameraUpdate);

            // draw line with all points
            line = map.addPolyline(new PolylineOptions()
                    .addAll(route)
                    .width(15)
                    .color(getResources().getColor(R.color.colorAccent)));

        }else{
            location = locationConstant.getLocation(this, this);
            Toast.makeText(this, "Location not available",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        locationConstant.removeLocationUpdate(this);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationConstant.removeLocationUpdate(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        map.clear();
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
        Toast.makeText(this, Double.toString(latitude) + " : " + Double.toString(longitude)
                , Toast.LENGTH_SHORT).show();
        // call map update
        mapFragment.getMapAsync(this);
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

    void initLocation(){
        // get current location
        location = locationConstant.getLocation(this,this);
        if (LocationConstant.canGetLocation) {
            try {
                if (location != null) {
                    onLocationChanged(location);
                } else {
                    location = locationConstant.getLocation(this,this);
                    Toast.makeText(this, "Location not available",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {
            locationConstant.showSettingsAlert(null,this);
        }

        // call update
        updateMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        LOCATION_PERMISSION_GRANTED = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LOCATION_PERMISSION_GRANTED = true;
                initLocation();
            }
        }
    }
}
