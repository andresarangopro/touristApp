package com.example.hp.tourist;

import android.location.Location;

public class Municipio {

    private Location location;

    private String name;

    public Municipio(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}