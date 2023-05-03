package com.imgideongo.vfp.ConfigVariables;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.receiver.ConnectivityChecker;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ConnectivityChecker connectivityChecker;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }


    public static synchronized MyApplication getInstance() {
        return myApplication;
    }


    public  void addToRequestQueue(Request request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public  void addToRequestQueue(Request request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public void unSetConnectivityListener(){
        unregisterReceiver(connectivityChecker);
    }

    public void setConnectivityListener(ConnectivityChecker.ConnectivityListener listener){

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityChecker=new ConnectivityChecker();
        registerReceiver(connectivityChecker,intentFilter);
        ConnectivityChecker.connectivityListener =listener;
    }


}
