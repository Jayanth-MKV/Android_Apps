package com.vugido.foodhub.vdexecutives.services;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vugido.foodhub.vdexecutives.R;
import com.vugido.foodhub.vdexecutives.Retrofit.RetrofitBuilder;
import com.vugido.foodhub.vdexecutives.activities.MainActivity;
import com.vugido.foodhub.vdexecutives.app_config_variables.MyPreferences;
import com.vugido.foodhub.vdexecutives.models.status.Response;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.foodhub.vdexecutives.app_config_variables.MyApp.CHANNEL_ID;


public class LocationService extends Service {
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
               // Log.e("OnLocationResultCalled", locationResult.toString());
                sendLocationUpdates(locationResult.getLastLocation().getLatitude(),locationResult.getLastLocation().getLongitude(),locationResult.getLastLocation().getSpeed(),locationResult.getLastLocation().getBearing(),locationResult.getLastLocation().getAccuracy());
            }
        };
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1=new Intent(this, MainActivity.class);
        createNotificationChannel();
        PendingIntent pendingIntent=PendingIntent.getActivity(this,2,intent1,0);
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("VDExecutive")
                .setContentText("Vugido is accessing your location")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
        requestLocation();
        return START_STICKY;
       // return super.onStartCommand(intent, flags, startId);
    }

   /* @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }*/

    private void sendLocationUpdates(double latitude,
                                     double longitude, float speed,
                                     float bearing,float accuracy){


        databaseReference= FirebaseDatabase.getInstance().getReference("DEL_B");
        databaseReference.child(new MyPreferences(getApplicationContext()).getDID()).child("latitude").setValue(String.valueOf(latitude));

        databaseReference.child(new MyPreferences(getApplicationContext()).getDID()).child("longitude").setValue(String.valueOf(longitude));

        databaseReference.child(new MyPreferences(getApplicationContext()).getDID()).child("bearing").setValue(String.valueOf(bearing));
        databaseReference.child(new MyPreferences(getApplicationContext()).getDID()).child("speed").setValue(String.valueOf(speed));

        databaseReference.child(new MyPreferences(getApplicationContext()).getDID()).child("accuracy").setValue(String.valueOf(accuracy));




        //Log.e("sending","ok");
        Map<String,Object> map=new HashMap<>();
        map.put("DID",new MyPreferences(this).getDID());
        map.put("LATITUDE",String.valueOf(latitude));
        map.put("LONGITUDE",String.valueOf(longitude));
        map.put("SPEED",String.valueOf(speed));



        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().updateLocationUrl(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

               // Log.e("response",response.toString());
                if(response.isSuccessful()){

                    //ok
                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok successfully updated
                      //  Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    }else {

                        // error
                       // Log.e("Updated","Not Success");
                    }
                }else {
                    //failed
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
               // Log.e("error",t.toString());
            }
        });
    }
    private void requestLocation() {
        LocationRequest locationRequest = new LocationRequest();
        //locationRequest.setFastestInterval(500);
        locationRequest.setInterval(2500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper());
    }
}
