package com.foodhub.vugido.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app.MyPreferences;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
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
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.android.libraries.places.api.Places;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;

public class MapsActivity extends AppCompatActivity implements PlaceSelectionListener,OnMapReadyCallback, OnFailureListener, OnSuccessListener<LocationSettingsResponse>, GoogleMap.OnMapClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMarkerDragListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int ORDER = 1234;
    private GoogleMap mMap;
    private static final int REQUEST_CHECK_SETTINGS = 10;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static double LATITUDE;
    private static double LONGITUDE;
    private Location CurrentLocation;
    ResolvableApiException resolvable;
    private MarkerOptions markerOptions;
    private FusedLocationProviderClient fusedLocationClient;
   // private ImageButton imageButton;
    private Button button;
    //private MyDialogLoader myDialogLoader;
    //private APPINFO appinfo;
    int CID;


    CardView sl,cl;
    EditText lm,dno;
    TextView locName;
    //TextInputEditText mapPhone,mapaddress;
    private String ADDRESS;


    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    // Set the fields to specify which types of place data to
    // return after the user has made a selection.
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS, Place.Field.LAT_LNG);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        setContentView(R.layout.activity_maps);
//        mapPhone=findViewById(R.id.user_phone);
//        mapaddress=findViewById(R.id.user_address);
        button=findViewById(R.id.button3);


        locName=findViewById(R.id.textView40);
        cl=findViewById(R.id.cardView4);
        sl=findViewById(R.id.cs);

        lm=findViewById(R.id.editTextTextPersonName2);
        dno=findViewById(R.id.editTextTextPersonName);

        CID=getIntent().getIntExtra("CID",0);
        // Initialize the AutocompleteSupportFragment.





        cl.setOnClickListener(v -> getCurrentLocation());

        sl.setOnClickListener(v -> {
            // Start the autocomplete intent.
            Places.initialize(getApplicationContext(),"AIzaSyA0p1oIkl6pQsS_kj7hCEc4FNcnQDTQrt0");

            // Create a new PlacesClient instance
            PlacesClient placesClient = Places.createClient(this);

                    try { Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                            .build(MapsActivity.this);
                        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
                    } catch (Exception e ) {
                        e.printStackTrace();
                        Log.e("error main",e.toString());
                    }




        });
        button.setOnClickListener(v -> {


            if (!lm.getText().toString().isEmpty() && !dno.getText().toString().isEmpty() ){


                new MyPreferences(this).setLocLan(String.valueOf(LONGITUDE));
                new MyPreferences(this).setLocLat(String.valueOf(LATITUDE));
                new MyPreferences(this).setUserPrimaryAddress(ADDRESS);
                new MyPreferences(this).setLM(lm.getText().toString());
                new MyPreferences(this).setDNO(dno.getText().toString());


                Intent intent=new Intent(MapsActivity.this,CheckOutActivity.class);
                intent.putExtra("CID",CID);
                startActivityForResult(intent,ORDER_PLACED_CODE);

            }else
                Toast.makeText(this,"Fill All Info",Toast.LENGTH_LONG).show();

        });

       // imageButton=findViewById(R.id.imageButton);
        //imageButton.setOnClickListener(v -> );
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

    }



    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(number);

        if(matcher.find() && matcher.group().equals(number)){
            return  true;
        }
        else
            return false;


    }

//    private void checkServiceArea() {
//
//
//
//
//        if(myDialogLoader==null){
//            myDialogLoader=new MyDialogLoader();
//        }
//        Bundle bundle=new Bundle();
//        bundle.putString("MSG","Checking Location..");
//        myDialogLoader.setArguments(bundle);
//        myDialogLoader.show(getSupportFragmentManager(), "DL");
//
//        Map<String,Object> map=new HashMap<>();
//        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
//
//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getAppInfo(map);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//                myDialogLoader.dismiss();
//                if (response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(response.body().isSTATUS()){
//
//                        appinfo= response.body().getAPPINFO();
//                        setServiceInfo();
//                        button.setEnabled(true);
//
//
//                    }else {
//
//                    }
//                }else {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//
//
//                button.setEnabled(true);
//                myDialogLoader.dismiss();
//            }
//        });
//
//
//
//    }

//    private void setServiceInfo() {
//
//        MyPreferences myPreferences=new MyPreferences(getApplicationContext());
//
//        myPreferences.setAPP_ON(appinfo.getAPPON());
//        myPreferences.setDC(appinfo.getDELIVERYCHARGES());
//        myPreferences.setDS(appinfo.getDISTANCESERVED());
//        myPreferences.setSM(appinfo.getSERVICEMSG());
//        myPreferences.setDAC(appinfo.getDELIVERYAPPLIEDFROM());
//        myPreferences.setDelTimeMsg(appinfo.getDELIVERYTIME());
//        myPreferences.setServiceFrom(appinfo.getSERVICEFROM());
//        myPreferences.setServiceTill(appinfo.getSERVICETILL());
//
//
//
//        myPreferences.setLocLat(String.valueOf(markerOptions.getPosition().latitude));
//        myPreferences.setLocLan(String.valueOf(markerOptions.getPosition().longitude));
//
//        myPreferences.setUserPrimaryAddress(Objects.requireNonNull(mapaddress.getText()).toString());
//
//        myPreferences.setMapPhone(Objects.requireNonNull(mapPhone.getText()).toString());
//
//        myPreferences.setBaseLocationName(ADDRESS);
//
//
//        LatLng latLng= markerOptions.getPosition();
//        Location loc1 = new Location("");
//        loc1.setLatitude(latLng.latitude);
//        loc1.setLongitude(latLng.longitude);
//
//        Location loc2 = new Location("");
//        loc2.setLatitude(mLatitude);
//        loc2.setLongitude(mLongitude);
//
//        float distanceInMeters = loc1.distanceTo(loc2);
//
//
//        if (distanceInMeters>myPreferences.getDS())
//        {
//            //no service..
//
//            Toast.makeText(getApplicationContext(),"Service Not Available Here",Toast.LENGTH_LONG).show();
//        }else
//        {
//            //serviceable
//            startActivityForResult(new Intent(vugido.foodhub.ap.sklm.hungrybirds.activities.MapsActivity.this,CheckOutActivity.class),ORDER);
//
//        }
//
//    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            updateMap(new LatLng(location.getLatitude(),location.getLongitude()));

                        }
                    }
                });
    }

    private void intIt(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap=googleMap;
        markerOptions=new MarkerOptions();
        markerOptions.draggable(true);
        mMap.setOnMapClickListener(this);
       // mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMarkerDragListener(this);
     /*   mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);*/


        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }


    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }
    private void checkLocationPermission() {
        //Log.e("0","ok");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //Log.e("1","ok");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //Log.e("2","ok");
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.enable_location)
                        .setMessage(R.string.permission_rationale_location)
                        .setPositiveButton("Ok", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(MapsActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        })
                        .create()
                        .show();


            } else {
                //Log.e("3","ok");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

        } else {


            // permission granted..

            intIt();
            getCurrentLocation();
            //Log.e("4","ok");
            buildLocationSettingsRequest();


        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Log.e("5","ok");
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        //Log.e("6","ok");
                        intIt();
                        getCurrentLocation();
                        buildLocationSettingsRequest();
                        //  locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    //Log.e("7","ok");

                    checkLocationPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }

            }

        }
    }
    // initializing the map..


    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);


    }

    private void buildLocationSettingsRequest() {
       // Log.e("8","ok");
        setLocationRequest();
        LocationSettingsRequest.Builder locationSettingsBuilder = new LocationSettingsRequest.Builder();
        locationSettingsBuilder.addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = settingsClient.checkLocationSettings(locationSettingsBuilder.build());
        locationSettingsResponseTask.addOnFailureListener(this);
        locationSettingsResponseTask.addOnSuccessListener(this);

    }



    @Override
    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

        ///Toast.makeText(this,"success",Toast.LENGTH_LONG).show();

        setLocationRequest();
        setLocationCallBack();
        startLocationUpdates();

    }


    @Override
    public void onFailure(@NonNull Exception e) {
        //Toast.makeText(this,"not success",Toast.LENGTH_LONG).show();

        //Log.e("onFailure","executed");
        if (e instanceof ResolvableApiException) {
            // Location settings are not satisfied, but this can be fixed
            // by showing the user a dialog.
            try {
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                resolvable = (ResolvableApiException) e;
                resolvable.startResolutionForResult(MapsActivity.this,
                        REQUEST_CHECK_SETTINGS);
            } catch (IntentSender.SendIntentException sendEx) {
                // Ignore the error.
            }
        }


    }

    private void stopLocationUpdates() {
        //Log.e("onStop","updates");
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
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
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(2000);

    }


    private void setLocationCallBack() {
        locationCallback=new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(locationResult==null){
                   // Log.e("null call","back");
                    return;
                }
                CurrentLocation=locationResult.getLastLocation();
                LATITUDE = CurrentLocation.getLatitude();
                LONGITUDE = CurrentLocation.getLongitude();
                updateMap(new LatLng(LATITUDE,LONGITUDE));
                stopLocationUpdates();

                // reverseGeoCode(LATITUDE, LONGITUDE);


            }
        };
    }

    private void reverseGeoCode(double latitude, double longitude) {

    }

    private void updateMap(LatLng latLng){

        //Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0) {

                Address address = addresses.get(0);
/*
                PinCode.setText(address.getPostalCode());
                City.setText(address.getLocality());
                State.setText(address.getAdminArea());
                Country.setText(address.getCountryName());
*/


                //pass this data to add address fragment...
               // setAddAddressFragment(address,latLng);


                // ask user

                // Address1.setText(address.);

                //Log.e("something",addresses.toString());
                ADDRESS=address.getAddressLine(0);
                locName.setText(ADDRESS);
                //Log.e("address details",address.getAddressLine(0));

                //Toast.makeText(this,"update called",Toast.LENGTH_SHORT).show();
                 CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,16f);
                mMap.clear();

                mMap.animateCamera(cameraUpdate);
                 markerOptions.position(latLng);

                 mMap.addMarker(markerOptions);
               // Toast.makeText(getApplicationContext(),"\nAdmin Area"+address.getAdminArea()+"\nPostalCode"+address.getPostalCode()+"\nFeatureName()"+address.getFeatureName()+"\nLocality()"+address.getLocality()+"\nSubAdminArea()"+address.getSubAdminArea()+"\nThoroughfare()"+address.getThoroughfare(),Toast.LENGTH_LONG).show();




                // get the address here and fill the form...

                // UserSelectedLocation.setText(address.getAddressLine(0));
                // FinalLocation=address.getAddressLine(0);

                // FinalLocation=

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


       // checkLocationService(latLng);
    }




    @Override
    public void onMapClick(LatLng latLng) {

      //  mMap.setMyLocationEnabled(false);
        updateMap(latLng);


    }

    @Override
    public boolean onMyLocationButtonClick() {

      //  mMap.setMyLocationEnabled(true);
        getCurrentLocation();
        return  true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        animateMarker(marker,marker.getPosition(),false);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==ORDER_PLACED_CODE && resultCode==RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }

        if(requestCode==AUTOCOMPLETE_REQUEST_CODE && resultCode==RESULT_OK)
        {


            assert data != null;
            Place place=Autocomplete.getPlaceFromIntent(data);
            updateMap(Objects.requireNonNull(place.getLatLng()));
            Log.e("Sel Address",place.toString());

        }

        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onPlaceSelected(@NonNull Place place) {

    }

    @Override
    public void onError(@NonNull Status status) {

    }
}
