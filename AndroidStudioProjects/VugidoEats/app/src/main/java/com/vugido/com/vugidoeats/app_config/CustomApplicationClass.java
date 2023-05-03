package com.vugido.com.vugidoeats.app_config;

import android.app.Activity;
import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vugido.com.vugidoeats.receivers.NetworkCallBack;


public class CustomApplicationClass extends Application implements Application.ActivityLifecycleCallbacks {

    NetworkCallBack networkCallBack;
    ConnectivityManager connectivityManager;
  //  private ProgressDialog progressDialog;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
      //  progressDialog=new ProgressDialog();


        }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        networkCallBack=new NetworkCallBack(activity,this);
        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull final Activity activity) {


               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   assert connectivityManager!=null;
                   connectivityManager.registerDefaultNetworkCallback(networkCallBack);
               }
           else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               NetworkRequest networkRequest = new NetworkRequest.Builder()
                       .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                       .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                       .build();
               connectivityManager.registerNetworkCallback(networkRequest,networkCallBack);
           }




    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

           if(connectivityManager!=null && networkCallBack!=null){
            connectivityManager.unregisterNetworkCallback(networkCallBack);
        }
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

}
