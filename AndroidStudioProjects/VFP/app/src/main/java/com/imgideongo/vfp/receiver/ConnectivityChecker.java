package com.imgideongo.vfp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.imgideongo.vfp.ConfigVariables.MyApplication;

import java.util.Objects;

public class ConnectivityChecker extends BroadcastReceiver {

    public static ConnectivityListener connectivityListener;
    @Override
    public void onReceive(Context context, Intent intent) {

        if(Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.e("from broadcast","message ok");
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            boolean connection = networkInfo != null && networkInfo.isConnectedOrConnecting();

            if (connectivityListener != null) {
                connectivityListener.connectivityListener(connection);

            }else{

                Log.e("ConnectivityListener","null");
            }
        }else {
            Log.e("from broadcast","message not ok");

        }


    }


    public static boolean isConnected(){
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
    }

    public  interface ConnectivityListener {

        void connectivityListener(boolean connection);
    }

}

