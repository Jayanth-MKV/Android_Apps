package com.vugido.foods.vugidodeliveryagent.services;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.vugido.foods.vugidodeliveryagent.R;
import com.vugido.foods.vugidodeliveryagent.activities.MainActivity;
import com.vugido.foods.vugidodeliveryagent.app_config_variables.MyPreferences;
import com.vugido.foods.vugidodeliveryagent.models.status.Response;
import com.vugido.foods.vugidodeliveryagent.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.foods.vugidodeliveryagent.app_config_variables.MyApp.CHANNEL_ID;

public class LocationService extends Service {
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;

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
                Log.e("OnLocationResultCalled", locationResult.toString());
                sendLocationUpdates(locationResult.getLastLocation().getLatitude(),locationResult.getLastLocation().getLongitude(),locationResult.getLastLocation().getSpeed());
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,2,intent1,0);
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Vugido Delivery Agent")
                .setContentText("Vugido is accessing your location")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
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

    private void sendLocationUpdates(double latitude, double longitude, float speed){

        Log.e("sending","ok");
        Map<String,Object> map=new HashMap<>();
        map.put("DID",new MyPreferences(this).getDID());
        map.put("LATITUDE",String.valueOf(latitude));
        map.put("LONGITUDE",String.valueOf(longitude));
        map.put("SPEED",String.valueOf(speed));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().updateLocationUrl(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                Log.e("response",response.toString());
                if(response.isSuccessful()){

                    //ok
                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok successfully updated
                        Log.e("Updated","Success");
                      //  Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    }else {

                        // error
                        Log.e("Updated","Not Success");
                    }
                }else {
                    //failed
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }
    private void requestLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }
}
