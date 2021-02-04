package com.kpitb.mustahiq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }

     /*   Bundle extras = getIntent().getExtras();

        if (extras != null) {
            lat1 = extras.getDouble("myLat");
            long1 = extras.getDouble("myLong");
        } else {
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }*/

        Log.e("HOOLLAA", String.valueOf(lat1 + long1));

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
                //mMarker = mMap.addMarker(markerOptions);

                //Move camera to this position
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
               // mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            }
        else
        {
            Log.d("ERROR", "CANNOT GET YOUR LOCATION");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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

                    latitude = lat1;
                    longitude = long1;
                    final LatLng current = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(current).title("Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                            .target(current)
                            .zoom(16)
                            .bearing(30)
                            .tilt(45)
                            .build()));
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
