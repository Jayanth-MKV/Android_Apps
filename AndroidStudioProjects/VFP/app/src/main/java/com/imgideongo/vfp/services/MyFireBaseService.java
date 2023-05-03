package com.imgideongo.vfp.services;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.imgideongo.vfp.helper.MyPreferences;
import com.imgideongo.vfp.notifications.ShowNotification;


import org.json.JSONException;
import org.json.JSONObject;

public class MyFireBaseService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s){
        super.onNewToken(s);
        new MyPreferences(getApplicationContext()).setFireBaseToken(s);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size()>0){

            try {
                JSONObject jsonObject=new JSONObject(remoteMessage.getData().toString());
               sendPushNotification(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendPushNotification(JSONObject jsonObject) {

        try {
            JSONObject object = jsonObject.getJSONObject("data");
            String title = object.getString("title");
            int TYPE=object.getInt("TYPE");
            int OID=object.getInt("ID");

            switch (TYPE){
                //case for new Order Placed
                case 1:
                    ShowNotification.getInstance(getApplicationContext()).orderDispatchedNotification(OID,title);
                    break;
                //case for order processed..
                case 2:
                    ShowNotification.getInstance(getApplicationContext()).orderProcessedNotification(OID,title);
                    break;

            }

        } catch(JSONException e){
            e.printStackTrace();
        }


    }

}




