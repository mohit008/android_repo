package com.mohit.program.map.location_with_map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mohit.program.R;


public class MasterMapView extends Activity implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;

    LatLng indore = new LatLng(22.7294139, 75.8591938);
    LatLng ind = new LatLng(22.710567, 75.876767);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_layout);
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        /*map = mapView.getMap();*/
        map.getUiSettings().setMyLocationButtonEnabled(false);
        /*mapview.setMyLocationEnabled(true);*/
        map.getUiSettings().setAllGesturesEnabled(true);

        MapsInitializer.initialize(this);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw.getBitmap(), 200, 200, false);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(indore)
                .title("Indore")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        MarkerOptions markerOptions1 = new MarkerOptions()
                .position(ind)
                .title("Indore")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        map.addMarker(markerOptions);
        map.addMarker(markerOptions1);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(indore, 12);
        /*mapview.animateCamera(cameraUpdate);*/
        map.moveCamera(cameraUpdate);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*mMap = googleMap;

        LatLng sydney = new LatLng(22.7294139,75.8591938);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Indore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));*/
    }
}
