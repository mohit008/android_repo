package com.mohit.program.map.google_map;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 01:24 PM.
 */

public class GoogleMapSample extends Activity {

    // location coordinates
    static final LatLng lng = new LatLng(22.7f, 75.9f);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map_sample_layout);

        try {
            //get id
            if (map == null) {
//                map = ((MapFragment) getFragmentManager().findFragmentById(R.id.maps)).getMap();
            }

            //set type
            map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            //zoom to current location
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));
            /*map.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 10.0f));*/

            // put mark on current location
            Marker mak = map.addMarker(new MarkerOptions().position(lng).title("Work"));

        } catch (Exception e) {
        }
    }

}
