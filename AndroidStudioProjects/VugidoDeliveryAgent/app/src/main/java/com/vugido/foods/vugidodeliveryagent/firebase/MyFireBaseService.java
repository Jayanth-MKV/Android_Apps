package com.vugido.foods.vugidodeliveryagent.firebase;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vugido.foods.vugidodeliveryagent.R;
import com.vugido.foods.vugidodeliveryagent.activities.MainActivity;
import com.vugido.foods.vugidodeliveryagent.app_config_variables.MyPreferences;
import com.vugido.foods.vugidodeliveryagent.services.RingToneService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class MyFireBaseService extends FirebaseMessagingService {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.e("token",s);
        new MyPreferences(getApplicationContext()).setFireBaseToken(s);
        new MyPreferences(getApplicationContext()).tokenChanged(true);
        // from here send the new token...

    }




    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        Log.e("OnMessage","Received");
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
            String title = object.getString("TITLE");
            String msg=object.getString("CONTENT");
            int TYPE=object.getInt("ID");
            String img_url=object.getString("IMAGE_URL");
            int NID=object.getInt("NID");

            notification(title,msg,img_url,NID);

           /* switch (TYPE){
                //case for order dis-patched..
                case ORDER_PLACED:

                    break;
                case ORDER_DISPATCHED:

                    break;
                case ORDER_SUCCESSFULLY_PROCESSED:

                    break;
                case HOME_PAGE:*/

                  //  notification(title,msg,img_url,NID);
                    //break;


          //  }







            } catch(JSONException e){
                e.printStackTrace();
            }


    }

    private void notification(String title, String msg, String img_url, int NID){


        Log.e("Order Placed","ok");

        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        //Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       // Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/" + R.raw.order_placed);

        //Bitmap bitmap=getBitmapFromURL(img_url);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"ID");
        builder.setSmallIcon(R.drawable.vn);
        builder.setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.vn)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                //.setSound(sound)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.vn))
               // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)
            //    )
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            NotificationChannel notificationChannel=new NotificationChannel("ID","channel", NotificationManager.IMPORTANCE_HIGH);
           // notificationChannel.setSound(sound,attributes);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);

            Intent startIntent = new Intent(this, RingToneService.class);
        }


        startService();


        assert notificationManager != null;
        notificationManager.notify(1,builder.build());


       // notifyServerNotificationReceived(NID);

    }


    public void startService() {
        Intent serviceIntent = new Intent(this, RingToneService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        ContextCompat.startForegroundService(this, serviceIntent);
    }



    @Override
    public void onDestroy() {
        Log.e("Destroyed","ok");
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();

        }
        super.onDestroy();

    }

    /* private void notifyServerNotificationReceived(int nid) {

        Map<String,Object> map=new HashMap<>();
        map.put("NID", nid);
        map.put("UID",new MyPreferences(this).getUID());


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().notifyServerNotificationReceived(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                // ok message sent successfully...

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                // else not sent....

            }
        });





    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/



}






