package com.vugido.online_groceries.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.did.DATAItem;
import com.vugido.online_groceries.models.did.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;
import com.vugido.online_groceries.network.TaskLoadedCallback;
import com.vugido.online_groceries.network.TaskLoadedCallbackRunning;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.model.Place;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class DMaps extends AppCompatActivity implements OnFailureListener, TaskLoadedCallbackRunning,TaskLoadedCallback,OnMapReadyCallback, OnSuccessListener<LocationSettingsResponse>, ValueEventListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int ORDER = 1234;
    private GoogleMap mMap;
    private static final int REQUEST_CHECK_SETTINGS = 10;
    private static double LATITUDE;
    private static double LONGITUDE;
    private Location CurrentLocation;
    private LatLng source,reach;
    private static double sla;
    private static double slon;
//    private static double rla=18.296610437766482;
//    private static double rlon=83.90041345287537;
List<LatLng> decodedPoints;
    private Polyline currentPolyline,runningLine;
    ResolvableApiException resolvable;
    private MarkerOptions markerOptions,sMarker,rMarker;

    int oid;
    private Marker user;
    List<MarkerOptions> markerOptionsList=new ArrayList<>();

    Circle userLocCircle;
    DatabaseReference databaseReference;



    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    // Set the fields to specify which types of place data to
    // return after the user has made a selection.
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS, Place.Field.LAT_LNG);
    private boolean ready;
    private int did;
    private int s;
    private String assigned;
    private String del;


    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url="https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.api_key);
        Log.e("url",url);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

        Log.e("direc", Arrays.toString(values));
    }


    @Override
    public void onTaskDoneRunning(Object... values) {

        // for running ...
        if (runningLine != null)
            runningLine.remove();
        runningLine = mMap.addPolyline((PolylineOptions) values[0]);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dmaps);

       // fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        ready=false;
        oid=getIntent().getIntExtra("OID",0);
        rMarker=new MarkerOptions().position(new LatLng(Double.parseDouble(getIntent().getStringExtra("LA")),Double.parseDouble(getIntent().getStringExtra("LO")))).title("Reach");



        FetchDid(oid);

       // new FetchUrl(DMaps.this).execute(getUrl(sMarker.getPosition(), rMarker.getPosition(), "driving"), "driving");



    }
    MyDialogLoader myDialogLoader;

    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }
    private void FetchDid(int oid) {

        Map<String,Object> map=new HashMap<>();
        map.put("OID",String.valueOf(oid));
        map.put("ID",new MyPreferences(getApplicationContext()).getUID());

        loginLoader(true,"Please Wait..");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().did(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok order placed successfully


                        bindData(response.body().getDATA());
                    }else {


                        // order not placed..

                    }
                }else {

                    //error
                    Log.e("ok","not done");

                }
                loginLoader(false,"Please Wait..");

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                loginLoader(false,"Please Wait..");

            }
        });

    }

    private void bindData(List<DATAItem> data) {

        ready=true;
        did=data.get(0).getDID();
        s=data.get(0).getSTATUS();
        assigned=data.get(0).getASSIGNEDON();
        del=data.get(0).getCLOSEDON();

        sla= Double.parseDouble(data.get(0).getLAN());
        slon= Double.parseDouble(data.get(0).getLON());


        sMarker=new MarkerOptions().position(new LatLng(sla,slon)).title("Source");

        markerOptionsList.add(sMarker);
        markerOptionsList.add(rMarker);

    }


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
        databaseReference= FirebaseDatabase.getInstance().getReference("DEL_B");

        databaseReference.addValueEventListener(this);
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            //updateMap(new LatLng(location.getLatitude(),location.getLongitude()));
//
//                        }
//                    }
//                });
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        if (sMarker!=null)
            mMap.addMarker(sMarker);
        mMap.addMarker(rMarker);

        if (sMarker!=null)
            setCenterMap(sMarker.getPosition(), rMarker.getPosition(),0,0);

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
                            ActivityCompat.requestPermissions(DMaps.this,
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
           // buildLocationSettingsRequest();


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
                       // buildLocationSettingsRequest();
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


    private  void setCenterMap(LatLng s, LatLng position,float bearing,float accuracy){
        /**Latlng's to get focus*/



        /**create for loop/manual to add LatLng's to the LatLngBounds.Builder*/
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(s);
        builder.include(position);

        /**initialize the padding for map boundary*/
        int padding = 0;
        /**create the bounds from latlngBuilder to set into map camera*/
        LatLngBounds bounds = builder.build();
        /**create the camera with bounds and padding to set into map*/
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        /**call the map call back to know map is loaded or not*/
       mMap.setOnMapLoadedCallback(() -> {
           /**set animated zoom camera into map*/
           mMap.animateCamera(cu);
       });

       if(user==null){

           MarkerOptions markerOptions=new MarkerOptions();
           markerOptions.position(s);
           markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapbike));
           markerOptions.rotation(bearing);

           markerOptions.anchor((float) 0.5,(float) 0.5);

           user=mMap.addMarker(markerOptions);

       }else {


           user.setPosition(s);
           user.setRotation(bearing);
       }


       if(userLocCircle==null){

           CircleOptions circleOptions=new CircleOptions();
           circleOptions.center(s);
           circleOptions.strokeWidth(4);
           circleOptions.strokeColor(Color.argb(255,255,0,0));
           circleOptions.fillColor(Color.argb(32,255,0,0));
           circleOptions.radius(accuracy);
           userLocCircle=mMap.addCircle(circleOptions);
       }else {
           userLocCircle.setCenter(s);
           userLocCircle.setRadius(accuracy);
       }


    }
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);


    }




    @Override
    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

        ///Toast.makeText(this,"success",Toast.LENGTH_LONG).show();

       // setLocationRequest();
       // setLocationCallBack();
       // startLocationUpdates();

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
                resolvable.startResolutionForResult(DMaps.this,
                        REQUEST_CHECK_SETTINGS);
            } catch (IntentSender.SendIntentException sendEx) {
                // Ignore the error.
            }
        }


    }


    private void updateMap(LatLng latLng){

        //Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0) {

                Address address = addresses.get(0);

                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,16f);
                mMap.clear();

                mMap.animateCamera(cameraUpdate);
                markerOptions.position(latLng);

                mMap.addMarker(markerOptions);
                Toast.makeText(getApplicationContext(),"\nAdmin Area"+address.getAdminArea()+"\nPostalCode"+address.getPostalCode()+"\nFeatureName()"+address.getFeatureName()+"\nLocality()"+address.getLocality()+"\nSubAdminArea()"+address.getSubAdminArea()+"\nThoroughfare()"+address.getThoroughfare(),Toast.LENGTH_LONG).show();




            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==ORDER && resultCode==RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }



        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {






        if (ready && s!=1 && s!=3){

            Log.e("latitude", (snapshot.child(String.valueOf(did)).child("latitude").getValue(String.class)));
            Log.e("longitude", Objects.requireNonNull(snapshot.child(String.valueOf(did)).child("longitude").getValue()).toString());

//        updateMap(new LatLng(Double.parseDouble(Objects.requireNonNull(Objects.requireNonNull(snapshot.child("1").child("latitude").getValue()).toString())),
//                Double.parseDouble(Objects.requireNonNull(Objects.requireNonNull(snapshot.child("1").child("longitude").getValue()).toString()))));

            LatLng s=new LatLng(Double.parseDouble(snapshot.child(String.valueOf(did)).child("latitude").getValue(String.class)),Double.parseDouble(Objects.requireNonNull(snapshot.child(String.valueOf(did)).child("longitude").getValue()).toString()));

            //red
//        new FetchUrlRunning(DMaps.this).execute(getUrl(s, rMarker.getPosition(), "driving"), "driving");
//
//        //black
//        new FetchUrl(DMaps.this).execute(getUrl(sMarker.getPosition(), s, "driving"), "driving");


            setCenterMap(s,rMarker.getPosition(), (float) Double.parseDouble(snapshot.child(String.valueOf(did)).child("bearing").getValue(String.class)),(float) Double.parseDouble(snapshot.child(String.valueOf(did)).child("accuracy").getValue(String.class)));


            if (decodedPoints==null) {
                decodedPoints = new ArrayList<>();

            }
            decodedPoints.add(s);
            PolylineOptions options = new PolylineOptions();
            options.width(3);
            options.color(Color.RED);
            options.addAll(decodedPoints);
            mMap.addPolyline(options);
        }
         }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {



        Log.e("error","Firebase error");

    }








    @Override
    protected void onUserLeaveHint() {

        }


}
