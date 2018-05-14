package com.mohit.program.map.location_with_map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mohit.program.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkerAndInfoWindow extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    ArrayList<MapDetail> detailArrayList = new ArrayList<>();
    HashMap<Marker, MapDetail> mapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlocation_activity_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // add data
        MapDetail detail = new MapDetail();
        detail.setName("a");
        detail.setPlace(new LatLng(22.7294139, 75.8591938));

        detailArrayList.add(detail);

        detail = new MapDetail();
        detail.setName("b");
        detail.setPlace(new LatLng(22.7294139, 76.8591938));

        detailArrayList.add(detail);

        // call create map
        createMap(detailArrayList);
    }

    /**
     * adapter for map marker to show information
     */
    public class Info implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(android.R.layout.activity_list_item, null, false);

            //get layout from hashmap by marker
            MapDetail detail = mapping.get(marker);

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(detail.getName());
            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    /**
     * create map with icon and register adapter
     *
     * @param detailArrayList arraylist
     */
    public void createMap(ArrayList<MapDetail> detailArrayList) {

        for (MapDetail detail : detailArrayList) {

            // icon to show
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
            Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw.getBitmap(), 200, 200, false);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(detail.getPlace())
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

            Marker marker = map.addMarker(markerOptions);

            //map marker to detail object
            mapping.put(marker, detail);

            map.setInfoWindowAdapter(new Info());
            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    marker.showInfoWindow();
                }
            });

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(detail.getPlace(), 12);
            map.moveCamera(cameraUpdate);
        }
    }

}
