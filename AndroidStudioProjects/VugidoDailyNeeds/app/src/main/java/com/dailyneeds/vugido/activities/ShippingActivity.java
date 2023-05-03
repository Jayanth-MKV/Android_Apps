package com.dailyneeds.vugido.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.dialogs.NoService;
import com.dailyneeds.vugido.models.FinalOrderableProducts;
import com.dailyneeds.vugido.receivers.NetworkCallBack;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ShippingActivity extends AppCompatActivity implements View.OnClickListener, SweetAlertDialog.OnSweetClickListener, OnFailureListener, OnSuccessListener<LocationSettingsResponse> {

    TextInputEditText FirstName,LastName,Office, Address1, Address2, Country, State, City, PinCode;
    TextInputLayout F,L,O,A;

    Button Proceed;
    Toolbar toolbar;


    private static final int REQUEST_CHECK_SETTINGS = 10;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static double LATITUDE;
    private static double LONGITUDE;
    private Location CurrentLocation;
    private float DeliveryCharges;
    private float Distance;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ResolvableApiException resolvable;
    String FinalLocation;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        toolbar=findViewById(R.id.custom_toolbar_shipping);

        toolbar.setTitle("Shipping Address");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(view -> finish());




        F=findViewById(R.id.id1);
        L=findViewById(R.id.id2);
        O=findViewById(R.id.id3);
        A=findViewById(R.id.id4);

        FirstName=findViewById(R.id.firstName);
        LastName=findViewById(R.id.lastName);
        Office=findViewById(R.id.office);
        Address1=findViewById(R.id.address1);
        Address2=findViewById(R.id.address2);
        Country=findViewById(R.id.country);
        State=findViewById(R.id.state);
        City=findViewById(R.id.city);
        PinCode=findViewById(R.id.pinCode);

        Proceed=findViewById(R.id.LocationFormSubmit);
        Proceed.setOnClickListener(this);

        Proceed.setEnabled(false);
        Proceed.setBackgroundColor(R.drawable.disable_button);

        checkLocationPermission();

        // first check for the existing saved address.. if not exists then load the form




    }
    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(() -> {

            if(!NetworkCallBack.NetworkAvailability){

                startActivity(new Intent(ShippingActivity.this, NetworkErrorActivity.class));

            }
        }, TIME);
    }


 /*   @Override
    public void onClick(View view) {

        // check the address.. and all blanks..


        // if address is not saved already..




        final SweetAlertDialog disabledBtnDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Do you want to Save Address for future ?")
                .setConfirmText("Confirm")
                .setCancelText("Cancel")
                .setConfirmClickListener(this)
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })

                ;

        disabledBtnDialog.show();





    }*/

    @Override
    public void onClick(SweetAlertDialog sweetAlertDialog) {


        sweetAlertDialog.dismissWithAnimation();

        SweetAlertDialog s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Saved")
                ;
        s.show();

    }



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
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(ShippingActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
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

          //  intIt();
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

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                      //  intIt();
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
    // initializing the map..


// checking location permission and enabling




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
                stopLocationUpdates();
                LATITUDE = CurrentLocation.getLatitude();
                LONGITUDE = CurrentLocation.getLongitude();
                reverseGeoCode(LATITUDE, LONGITUDE);


            }
        };
    }

    private void reverseGeoCode(double latitude, double longitude) {
        LATITUDE=latitude;
        LONGITUDE=longitude;
        Distance=calDistance(new LatLng(latitude,longitude));
        DeliveryCharges=calDeliveryCharges(Distance);

        updateMap(new LatLng(latitude,longitude));

        if(Distance > 10000){

            // location out of service...
            Proceed.setEnabled(false);
            Proceed.setText(R.string.change_loc);
            Proceed.setBackground(getDrawable(R.drawable.disable_button));

            NoService noService=new NoService();
            noService.show(getSupportFragmentManager(),"NO_SERVICE");


        }else {
            Proceed.setText(R.string.proceed);
            Proceed.setEnabled(true);
            Proceed.setBackground(getDrawable(R.drawable.button));
        }

    }


    private void updateMap(LatLng latLng){

        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0) {

                Address address = addresses.get(0);

                PinCode.setText(address.getPostalCode());
                City.setText(address.getLocality());
                State.setText(address.getAdminArea());
                Country.setText(address.getCountryName());


                // ask user

               // Address1.setText(address.);

                Log.e("something",addresses.toString());
                Log.e("address details",address.getAddressLine(0));
               // CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,16f);
              //  mMap.animateCamera(cameraUpdate);

                // get the address here and fill the form...

               // UserSelectedLocation.setText(address.getAddressLine(0));
               // FinalLocation=address.getAddressLine(0);

               // FinalLocation=

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private float calDeliveryCharges(float distance) {

        if(distance <=1000.0f){
            return 15.0f;
        }else {
            float DeliveryCharges;
            float UnitCharge=0.005f;
            DeliveryCharges=(UnitCharge*(distance-1000))+15.0f;
            return Math.round(DeliveryCharges);
        }

    }

    private float calDistance(LatLng latLng) {


        Location Source=new Location("Source");
        Location Destination=new Location("Destination");
        Source.setLatitude(latLng.latitude);
        Source.setLongitude(latLng.longitude);
        Destination.setLongitude(ConfigVariables.DestinationLongitude);
        Destination.setLatitude(ConfigVariables.DestinationLatitude);
        return Source.distanceTo(Destination);
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
                resolvable.startResolutionForResult(ShippingActivity.this,
                        REQUEST_CHECK_SETTINGS);
            } catch (IntentSender.SendIntentException sendEx) {
                // Ignore the error.
            }
        }


    }

    private void stopLocationUpdates() {
        Log.e("onStop","updates");
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
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
                        resolvable.startResolutionForResult(ShippingActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                    break;

            }



        }else if (requestCode==REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {

                LatLng latLng;
                assert data != null;
                Place place = Autocomplete.getPlaceFromIntent(data);
                latLng = place.getLatLng();
                assert latLng != null;
           /* Log.e("FromResult",latLng.toString());
            Distance=calDistance(latLng);
            DeliveryCharges=calDeliveryCharges(Distance);*/
                reverseGeoCode(latLng.latitude, latLng.longitude);

            }
        }else if(requestCode== ConfigVariables.ORDER_PLACED_CODE && resultCode== ConfigVariables.ORDER_PLACED_RESPONSE_CODE){

            setResult(ConfigVariables.ORDER_PLACED_RESPONSE_CODE);
            finish();

        }


    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case  R.id.LocationFormSubmit:
                // check for the service availability...

                if(checkCredentials()){

                    FinalLocation= Objects.requireNonNull(FirstName.getText()).toString()+"," + Objects.requireNonNull(LastName.getText()).toString()+","
                            + Objects.requireNonNull(Office.getText()).toString()+","+ Objects.requireNonNull(Address1.getText()).toString();

                    FinalOrderableProducts finalOrderableProducts= Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getParcelable("FINAL");
                    assert finalOrderableProducts!=null;
                    finalOrderableProducts.setDistance(Distance);
                    finalOrderableProducts.setDeliveryCharges(DeliveryCharges);
                    finalOrderableProducts.setLATITUDE(LATITUDE);
                    finalOrderableProducts.setLONGITUDE(LONGITUDE);
                    assert FinalLocation!=null;
                    finalOrderableProducts.setLocation(FinalLocation);
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("FINAL",finalOrderableProducts);
                    Intent intent=new Intent(ShippingActivity.this,CheckOut.class);
                    intent.putExtra("BUNDLE",bundle);
                    startActivityForResult(intent,ConfigVariables.ORDER_PLACED_CODE);

                }

                break;




        }
    }

    private boolean checkCredentials() {

        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher first= pattern.matcher(Objects.requireNonNull(FirstName.getText()).toString());
        Matcher last=pattern.matcher(Objects.requireNonNull(LastName.getText()).toString());



        if(first.find() && first.group().equals(FirstName.getText().toString()) ){

            if(last.find() && last.group().equals(LastName.getText().toString())){

                if ( !Office.getText().toString().isEmpty()){

                    if(!Address1.getText().toString().isEmpty()){

                        return  true;

                    }else {
                        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                        A.startAnimation(shake);
                        return  false;

                    }

                }else {
                    Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                    O.startAnimation(shake);
                    return  false;

                }
            }
            else {

                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                L.startAnimation(shake);
                return  false;
            }
        }
        else {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            F.startAnimation(shake);
            return  false;
        }

    }



}
