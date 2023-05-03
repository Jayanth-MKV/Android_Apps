package com.vugido.com.vugidoeats.receivers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vugido.com.vugidoeats.activities.NetworkErrorActivity;


public class NetworkCallBack extends ConnectivityManager.NetworkCallback {

    public static boolean NetworkAvailability=false;
    private Activity activity;
    private Context context;

    public NetworkCallBack(Activity activity, Context context) {
        super();
        this.activity=activity;
        this.context=context;


    }



    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);

        // do the work..
        NetworkAvailability=true;
        if(NetworkErrorActivity.ON){
            activity.finish();
        }



    }

    @Override
    public void onLosing(@NonNull Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);

        if(!NetworkErrorActivity.ON){

            Log.e("onLost","executed");
            context.startActivity(new Intent(context,NetworkErrorActivity.class));
        }

        NetworkAvailability=false;

        // tell user to check the network connection..

    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();

    }

    @Override
    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);

    }

    @Override
    public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties);

    }

    @Override
    public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
        super.onBlockedStatusChanged(network, blocked);

    }


}
