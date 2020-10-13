package com.kpitb.zakattandusherr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import com.google.android.gms.location.LocationListener;

import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsLocation extends AppCompatActivity implements OnMapReadyCallback,
          GoogleApiClient.ConnectionCallbacks,
          GoogleApiClient.OnConnectionFailedListener,
          LocationListener{

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private double latitude,longitude;
    private Location mLastLocation;
    private Marker mMarker;
    private LocationRequest mLocationReq;

    private static int UPDATE_INTERVAL = 6000;
    private static int FATEST_INTERVAL = 1000;
    private static int DISPLACEMENT = 200;

    double lat1, long1;

    Intent myIntent;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent().getExtras() != null)
        {
            lat1 = Double.valueOf(getIntent().getStringExtra("myLat"));
            long1 = Double.valueOf(getIntent().getStringExtra("myLong"));
        }
        else {

        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        //Request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            displayLocation();
        }

        mLocationReq = new LocationRequest();
        mLocationReq.setInterval(UPDATE_INTERVAL);
        mLocationReq.setFastestInterval(FATEST_INTERVAL);
        mLocationReq.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationReq.setSmallestDisplacement(DISPLACEMENT);
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            //check if location is enabled in device or not, if not then send to gps area so he can enable it
            locationManager = (LocationManager) getApplicationContext()
                    .getSystemService(Context.LOCATION_SERVICE);
            if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
                new AlertDialog.Builder(this)
                        .setTitle("GPS not enabled")  // GPS not found
                        .setMessage("Want to enable to access location?") // Want to enable?
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
            return;
        }

        //check if location is enabled in device or not, if not then send to gps area so he can enable it
        locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
            new AlertDialog.Builder(this)
                    .setTitle("GPS not enabled")  // GPS not found
                    .setMessage("Want to enable to access location?") // Want to enable?
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null)
        {
            if (getIntent().getExtras() != null)
            {
                final double latitude = lat1;
                final double longitude = long1;

                LatLng latLng = new LatLng(latitude,longitude);
                //ADD MARKER
                if (mMarker != null)
                    mMarker.remove();     //Remove the marker which is already present

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title("Your position")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMarker = mMap.addMarker(markerOptions);

                //Move camera to this position
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            }
            else {
                final double latitude = mLastLocation.getLatitude();
                final double longitude = mLastLocation.getLongitude();

                LatLng latLng = new LatLng(latitude,longitude);
                //ADD MARKER
                if (mMarker != null)
                    mMarker.remove();     //Remove the marker which is already present

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title("Your position")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMarker = mMap.addMarker(markerOptions);

                //Move camera to this position
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            }
        }
        else
        {
            Log.d("ERROR", "CANNOT GET YOUR LOCATION");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Init google play services here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else
        {
            mMap.setMyLocationEnabled(true);
        }
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.markers_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if (mGoogleApiClient == null)
                            buildGoogleApiClient();
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
//        mMap.clear();
//        mLastLocation = location;
//        if (mMarker != null)
//            mMarker.remove();
//
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//        LatLng latLng = new LatLng(latitude,longitude);
//        MarkerOptions markerOptions = new MarkerOptions()
//                .position(latLng)
//                .title("my position")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        mMarker = mMap.addMarker(markerOptions);
//
//        //move camera
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),15.0f));
//
//        if (mGoogleApiClient != null)
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        mLocationReq.setInterval(1000);
//        mLocationReq.setFastestInterval(1000);
//        mLocationReq.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//        {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationReq,this);
//        }

        try {
            mLocationReq = new LocationRequest();
            if (ActivityCompat.checkSelfPermission(MapsLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Request missing location permission.
                ActivityCompat.requestPermissions(MapsLocation.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
            } else if (getIntent().getExtras() != null)
            {
                // Location permission has been granted, continue as usual.
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationReq, this);

                } else {
                    latitude = lat1;
                    longitude = long1;
                    final LatLng current = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                            .target(current)
                            .zoom(16)
                            .bearing(30)
                            .tilt(45)
                            .build()));

                }
            }
            else {
                // Location permission has been granted, continue as usual.
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationReq, this);

                } else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    final LatLng current = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                            .target(current)
                            .zoom(16)
                            .bearing(30)
                            .tilt(45)
                            .build()));

                }
            }
        }catch (Exception e)
        {

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
