package com.example.hp.tourist;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Image;

public class AtraccionTuristica {

    private Location location;

    private String name;

    private Drawable image;

    public AtraccionTuristica(Location location, String name, Drawable image) {
        this.location = location;
        this.name = name;
        this.image = image;
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

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
