package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.R;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.app_config_variables.MyPreferences;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Bottom_Model_Fragments.FragmentSelectAddressBottomSheet;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Delivery_Fragments.Fragment_Choose_Existing_Address;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Delivery_Fragments.Fragment_Get_Address;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.network.Retrofit.RetrofitBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;



public class DeliveryActivity extends AppCompatActivity implements Fragment_Choose_Existing_Address.SetFragmentGetAddress, Fragment_Choose_Existing_Address.GET_ADDRESSES,
        FragmentSelectAddressBottomSheet.GetAddressesBottomSheet, FragmentSelectAddressBottomSheet.ChangePrimaryAID, Fragment_Choose_Existing_Address.ShowProgress, OnFailureListener, OnSuccessListener<LocationSettingsResponse> ,
        Fragment_Get_Address.ShowProgress {


    private Toolbar toolbar;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<DATAItem> AddressesList;
    View Progress;

    ///////////////////

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

        setContentView(R.layout.activity_delivery);
        toolbar=findViewById(R.id.delivery_toolbar);
        fragmentManager=getSupportFragmentManager();

        toolbar.setTitle("Shipping Address");

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Progress=findViewById(R.id.my_progress);

        // first check the address available or not
        fetchAddress();


    }




    private void checkLocationPermission() {
        Log.e("0","ok");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Log.e("1","ok");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Log.e("2","ok");
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.enable_location)
                        .setMessage(R.string.permission_rationale_location)
                        .setPositiveButton("Ok", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(DeliveryActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        })
                        .create()
                        .show();


            } else {
                Log.e("3","ok");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

        } else {


            // permission granted..

            //  intIt();
            Log.e("4","ok");
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
                    Log.e("5","ok");
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        Log.e("6","ok");
                        //  intIt();
                        buildLocationSettingsRequest();
                        //  locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    Log.e("7","ok");

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
        Log.e("8","ok");
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
                updateMap(new LatLng(LATITUDE,LONGITUDE));
               // reverseGeoCode(LATITUDE, LONGITUDE);


            }
        };
    }

  /*  private void reverseGeoCode(double latitude, double longitude) {
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

    }*/


    private void updateMap(LatLng latLng){

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
                setAddAddressFragment(address,latLng);


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

    /*private float calDistance(LatLng latLng) {


        Location Source=new Location("Source");
        Location Destination=new Location("Destination");
        Source.setLatitude(latLng.latitude);
        Source.setLongitude(latLng.longitude);
        Destination.setLongitude(ConfigVariables.DestinationLongitude);
        Destination.setLatitude(ConfigVariables.DestinationLatitude);
        return Source.distanceTo(Destination);
    }*/




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
                resolvable.startResolutionForResult(DeliveryActivity.this,
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






    private void fetchAddress() {

        Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());// user id

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).fetchAllAddresses(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // address exists
                        // fetch address and show to user to select
                        AddressesList=response.body().getDATA();

                        setChooseAddressFragment();

                    }else {

                        // no previous address saved so launch new address fragment..
                        checkLocationPermission();

                    }

                    Progress.setVisibility(View.GONE);
                }else {

                    // some problem
                    Progress.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {


            }
        });






    }

    private void setChooseAddressFragment() {
        fragment=new Fragment_Choose_Existing_Address();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.delivery_frame_layout,fragment,"CHOOSE_ADDRESS");
        fragmentTransaction.commit();

    }


    private  void setAddAddressFragment(Address address,LatLng latLng) {


        showProgress(false);
        if (fragment != null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
    }

        fragment=new Fragment_Get_Address();
        Bundle bundle=new Bundle();
        bundle.putString("Z",address.getPostalCode());
        bundle.putString("L",address.getFeatureName()+" "+address.getSubLocality()+","+address.getThoroughfare()+", "+address.getSubAdminArea());
        bundle.putString("S",address.getAdminArea());
        bundle.putString("C",address.getCountryName());
        Log.e("address test","Feature Name :"+address.getFeatureName()+"\nPremisis"+address.getPremises()+"\nSub Admin area"+address.getSubAdminArea()+"\nSub Locality"+address.getSubLocality()
                +"\nSub through fare"+address.getSubThoroughfare()+"\n Through fare"+address.getThoroughfare());
        bundle.putString("CA",address.toString());
        bundle.putDouble("LAT",latLng.latitude);
        bundle.putDouble("LON",latLng.longitude);
        fragment.setArguments(bundle);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.delivery_frame_layout,fragment,"GET_ADDRESS");
        fragmentTransaction.commit();


    }


    @Override
    public void setFragmentGetAddress() {

        // get location.....

        //setAddAddressFragment();
        showProgress(true);
        checkLocationPermission();
       /* fragment=new Fragment_Get_Address();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.delivery_frame_layout,fragment,"GET_ADDRESS");
        fragmentTransaction.commit();*/


    }

    @Override
    public List<DATAItem> getFetchedUserAddresses() {
        return AddressesList;
    }

    @Override
    public List<DATAItem> getAddressesSelectionBottomSheet() {



        return AddressesList;
    }

    @Override
    public void changePrimaryAid(int aid) {
        // get the primary id and change the list..
        // first set all primary to 0

        for(int i=0;i<AddressesList.size();i++){

            if(AddressesList.get(i).getAID()==aid){
                AddressesList.get(i).setISPRIMARY(1);
            }else {
                AddressesList.get(i).setISPRIMARY(0);
            }
        }

        // now we have set the correct primary address..

        resetPrimaryAddressLayout();
    }

    private void resetPrimaryAddressLayout() {
        fragment=new Fragment_Choose_Existing_Address();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.delivery_frame_layout,fragment,"CHOOSE_ADDRESS");
        fragmentTransaction.commit();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ORDER_PLACED_CODE){
            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }

        }
    }

    @Override
    public void showProgress(boolean show) {
        if(show)
            Progress.setVisibility(View.VISIBLE);
        else
            Progress.setVisibility(View.GONE);
    }


}
