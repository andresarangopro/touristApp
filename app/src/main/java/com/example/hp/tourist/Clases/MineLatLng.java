package com.example.hp.tourist.Clases;

public class MineLatLng {

    private double Latitude;
    private double Longitude;

    public MineLatLng(double latitude, double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public MineLatLng() {
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
