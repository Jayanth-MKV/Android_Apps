package com.dailyneeds.vugido.receivers;



import android.util.Log;

import androidx.annotation.NonNull;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class MyFireBaseService extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        new MyPreferences(getApplicationContext()).setFireBaseToken(s);

        Log.e("Token",s);
        new MyPreferences(getApplicationContext()).tokenChanged(true);
        // from here send the new token...
      //  sendToken(s);

    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
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



    private void sendToken(final String token){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConfigVariables.SEND_FIREBASE_TOKEN,
                response -> {




                },
                error -> {



                }){


            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("UID",new MyPreferences(getApplicationContext()).getUID());
                params.put("TOKEN",token);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void sendPushNotification(JSONObject jsonObject) {



     /*   try {
            JSONObject object = jsonObject.getJSONObject("data");
            String title = object.getString("title");
            int TYPE=object.getInt("TYPE");
            int OID=object.getInt("ID");

            switch (TYPE){
                //case for order dis-patched..
                case 1:
                    ShowNotification.getInstance(getApplicationContext()).orderDispatchedNotification(OID,title);
                    break;
                    //case for order processed
                case 2:
                    ShowNotification.getInstance(getApplicationContext()).orderProcessedNotification(OID,title);
                    break;



                /*case "points":
                    PointsNotification pointsNotification = new PointsNotification();
                    pointsNotification.setTitle(title);
                    ShowNotification.getInstance(getApplicationContext()).displayNotification(pointsNotification);
                    break;
                case "update":
                    ShowNotification.getInstance(getApplicationContext()).appUpdateAvailable();
                    break;
                case "dispatched":
                    String ID1=object.getString("ID");
                    ShowNotification.getInstance(getApplicationContext()).orderDispatchedNotification(ID1,title);
                    break;
                case "completed":
                    String ID2=object.getString("ID");
                    ShowNotification.getInstance(getApplicationContext()).orderProcessedNotification(ID2,title);
                    break;
            }





            } catch(JSONException e){
                e.printStackTrace();
            }*/


    }


}




