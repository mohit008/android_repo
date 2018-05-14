package com.mohit.program.map.location_with_map;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by mohit on 01-Apr-17.
 */
public class MapDetail implements Serializable {

    String name;
    LatLng place;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getPlace() {
        return place;
    }

    public void setPlace(LatLng place) {
        this.place = place;
    }
}
