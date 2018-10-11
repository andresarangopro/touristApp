package com.example.hp.tourist.Fragments;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hp.tourist.Clases.MineLatLng;
import com.example.hp.tourist.Clases.Transacciones;
import com.example.hp.tourist.Municipio;
import com.example.hp.tourist.R;
import com.example.hp.tourist.Ruta;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MapsFragment extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Boolean mLocationPermissionGranted;

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private Transacciones tr = new Transacciones();

    private LatLng caldas;

    private Calendar cal = Calendar.getInstance();
    /**
     * Contiene mi ubicación
     */
    private Location mLastKnownLocation;

    private FusedLocationProviderClient mfusedLocationProviderclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        tr.inicializatedFireBase(MapsFragment.this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mfusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();

    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getDeviceLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();

    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mfusedLocationProviderclient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            if (mLastKnownLocation != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()), 8.0f));
                                mMap.getUiSettings().setMyLocationButtonEnabled(Boolean.TRUE);
                                mMap.setMyLocationEnabled(Boolean.TRUE);
                            }else{
                                Toast.makeText(MapsFragment.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }

                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera,
        caldas = new LatLng(6.105582, -75.635064);
        mMap.addMarker(new MarkerOptions().position(caldas).title("Caldas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(caldas));
        onPinClick();
    }

    private void onPinClick(){


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(mLastKnownLocation != null){
                    marker.getPosition();//Esto tiene la latitud y longitud
                    mLastKnownLocation.getLatitude();
                    mLastKnownLocation.getLongitude();

                    FirebaseUser user = tr.firebaseAuth.getCurrentUser();
                    String fecha = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
                    MineLatLng pointini = new MineLatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude());
                    MineLatLng pointfin = new MineLatLng(caldas.longitude,caldas.latitude);
                    Ruta r = new Ruta(pointini,pointfin,fecha,user.getUid());
                    Municipio m = new Municipio(caldas,marker.getTitle());
                    tr.insertarMunicip(m);
                    tr.insertarRuta(r);
                }
                Toast.makeText(MapsFragment.this, "Hola", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}