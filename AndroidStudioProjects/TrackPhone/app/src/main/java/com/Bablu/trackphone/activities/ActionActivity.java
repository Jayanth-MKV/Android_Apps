package com.Bablu.trackphone.activities;



import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.Bablu.trackphone.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;



public class ActionActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, OnFailureListener, OnSuccessListener<LocationSettingsResponse> {
    Toolbar toolbar;
    private int type=-1;
    private View profile,protect,track;


    //////////tracking




    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ResolvableApiException resolvable;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static double LATITUDE;
    private static double LONGITUDE;
    private Location CurrentLocation;
    private GoogleMap mMap;
    private static final int REQUEST_CHECK_SETTINGS = 10;



    //tracking/////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        type=getIntent().getIntExtra("ACTION",-1);

        toolbar=findViewById(R.id.action_toolbar);
        protect=findViewById(R.id.protect_view);
        track=findViewById(R.id.track_view);
        profile=findViewById(R.id.profile_view);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        setToolBarTitle();

        setViewType();

    }

    private void setViewType() {


        switch (type){

            case 0:
                profile.setVisibility(View.VISIBLE);
                protect.setVisibility(View.GONE);
                track.setVisibility(View.GONE);
                break;
            case 1:
                profile.setVisibility(View.GONE);
                protect.setVisibility(View.VISIBLE);
                track.setVisibility(View.GONE);
                break;
            case 2:
                profile.setVisibility(View.GONE);
                protect.setVisibility(View.GONE);
                track.setVisibility(View.VISIBLE);


                checkLocationPermission();
                break;

        }
    }

    private void setToolBarTitle() {

        switch (type){
            case 0:
                Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
                break;
            case 1:
                Objects.requireNonNull(getSupportActionBar()).setTitle("Protect");
                break;
            case 2:
                Objects.requireNonNull(getSupportActionBar()).setTitle("Tracking");
                break;
        }

    }

    ////////////////////////tracking





    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.enable_location)
                        .setMessage(R.string.permission_rationale_location)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(ActionActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

        } else {


            // permission granted..

            intIt();
            buildLocationSettingsRequest();


        }
    }
    private void intIt(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.location_map_fragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
     /*   mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);*/

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        //  mMap.setOnMarkerClickListener(this);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        // once map is ready and all set check for location

        // enableMyLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        intIt();
                        buildLocationSettingsRequest();
                        //  locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {


                    checkLocationPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check here location settings request..
        if(requestCode==REQUEST_CHECK_SETTINGS){


            switch (resultCode){


                case RESULT_OK:
                    Log.e("accepted","ok");
                    setLocationCallBack();
                    startLocationUpdates();
                    break;
                case RESULT_CANCELED:

                    Log.e("not","accepted");
                    try {
                        assert resolvable!=null;
                        resolvable.startResolutionForResult(ActionActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                    break;

            }



        }
    }

    // initializing the map..


// checking location permission and enabling


    private void reverseGeoCode(double latitude, double longitude) {
        LATITUDE=latitude;
        LONGITUDE=longitude;

        updateMap(new LatLng(latitude,longitude));

    }


    private void updateMap(LatLng latLng){

        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0) {

                Address address = addresses.get(0);
                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,16f);
                mMap.animateCamera(cameraUpdate);
               // UserSelectedLocation.setText(address.getAddressLine(0));
              //  FinalLocation=address.getAddressLine(0);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void buildLocationSettingsRequest() {
        setLocationRequest();
        LocationSettingsRequest.Builder locationSettingsBuilder = new LocationSettingsRequest.Builder();
        locationSettingsBuilder.addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        // Tasks tasks=settingsClient.checkLocationSettings(locationSettingsBuilder.build());

        Task<LocationSettingsResponse> locationSettingsResponseTask = settingsClient.checkLocationSettings(locationSettingsBuilder.build());
        locationSettingsResponseTask.addOnFailureListener(this);
        locationSettingsResponseTask.addOnSuccessListener(this);

    }

    @Override
    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

        Log.e("onSuccess","executed");

        // all settings are satisfied...

        setLocationCallBack();
        startLocationUpdates();



    }
    private void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest,locationCallback ,null);
    }
    private void setLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(2000);
    }


    private void setLocationCallBack() {
        locationCallback=new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(locationResult==null){
                    return;
                }
                CurrentLocation=locationResult.getLastLocation();
              //  stopLocationUpdates();
                LATITUDE = CurrentLocation.getLatitude();
                LONGITUDE = CurrentLocation.getLongitude();
                reverseGeoCode(LATITUDE, LONGITUDE);


            }
        };
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onFailure(@NonNull Exception e) {

        Log.e("onFailure","executed");
        if (e instanceof ResolvableApiException) {
            // Location settings are not satisfied, but this can be fixed
            // by showing the user a dialog.
            try {
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                resolvable = (ResolvableApiException) e;
                resolvable.startResolutionForResult(ActionActivity.this,
                        REQUEST_CHECK_SETTINGS);
            } catch (IntentSender.SendIntentException sendEx) {
                // Ignore the error.
            }
        }
    }


    // tracking
}
