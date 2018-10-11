package com.example.hp.tourist;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Municipio implements FirebaseItems{

    private LatLng location;

    private String name;

    public Municipio(LatLng location, String name) {
        this.location = location;
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Municipios";
    }
}
