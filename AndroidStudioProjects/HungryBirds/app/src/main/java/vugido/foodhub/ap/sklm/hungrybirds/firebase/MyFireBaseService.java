package vugido.foodhub.ap.sklm.hungrybirds.firebase;


import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.activities.MainActivity;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;


public class MyFireBaseService extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        //android.util.Log.e("FBASE","OnNewToken");
        new MyPreferences(getApplicationContext()).setFireBaseToken(s);
        new MyPreferences(getApplicationContext()).tokenChanged(true);
        // from here send the new token...



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




    private void sendPushNotification(JSONObject jsonObject) {


        android.util.Log.e("Notification",jsonObject.toString());

        try {
            JSONObject object = jsonObject.getJSONObject("data");
            String title = object.getString("TITLE");
            String msg=object.getString("CONTENT");
            int TYPE=object.getInt("ID");
            String img_url=object.getString("IMAGE_URL");
            int NID=object.getInt("NID");


           if(TYPE==10){
               // someone logged in.. on another device..

              // reVerifyNotification(title,msg);
           }else {
              // notification(title,msg,img_url,NID);

           }








            } catch(JSONException e){
                e.printStackTrace();
            }


    }


    private boolean applicationInForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        assert activityManager != null;
        java.util.List<ActivityManager.RunningAppProcessInfo> services = activityManager.getRunningAppProcesses();
        boolean isActivityFound = false;

        if (services.get(0).processName
                .equalsIgnoreCase(getPackageName()) && services.get(0).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
            isActivityFound = true;
        }

        return isActivityFound;
    }
//    private void reVerifyNotification(String title, String msg) {
//
//        new MyPreferences(this).setVerified(false);
//        new MyPreferences(this).tokenChanged(true);
//        Intent intent=new Intent(this, ReferralActivity.class);
//        intent.putExtra("CLICKED",1);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"ID");
//        builder.setSmallIcon(R.drawable.default_icon);
//        builder.setContentTitle(title)
//                .setContentText(msg)
//                .setSmallIcon(R.drawable.vn)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setAutoCancel(true)
//                .setSound(uri)
//                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
//                        R.drawable.vn))
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//
//            NotificationChannel notificationChannel=new NotificationChannel("ID","channel", NotificationManager.IMPORTANCE_HIGH);
//            assert notificationManager != null;
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        assert notificationManager != null;
//        notificationManager.notify(10,builder.build());
//
//
//        if(applicationInForeground()){
//            // show dialog box..
//
//
//            getApplicationContext().startActivity(intent);
//
//        }
//
//
//
//    }
//
//    private void notification(String title, String msg, String img_url, int NID){
//        Intent intent=new Intent(this, MainActivity.class);
//        intent.putExtra("NID",NID);
//        intent.putExtra("CLICKED",1);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Bitmap bitmap=getBitmapFromURL(img_url);
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"ID");
//        builder.setSmallIcon(R.drawable.default_icon);
//        builder.setContentTitle(title)
//                .setContentText(msg)
//                .setSmallIcon(R.drawable.vn)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setAutoCancel(true)
//                .setSound(uri)
//                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
//                        R.drawable.vn))
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)
//                )
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//
//            NotificationChannel notificationChannel=new NotificationChannel("ID","channel", NotificationManager.IMPORTANCE_HIGH);
//            assert notificationManager != null;
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        assert notificationManager != null;
//        notificationManager.notify(1,builder.build());
//
//
//        notifyServerNotificationReceived(NID);
//
//    }
//
//    private void notifyServerNotificationReceived(int nid) {
//
//        java.util.Map<String, Object> map=new HashMap<>();
//        map.put("NID", nid);
//        map.put("UID",new MyPreferences(this).getUID());
//
//
//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).notifyServerNotificationReceived(map);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//
//                // ok message sent successfully...
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//
//                // else not sent....
//
//            }
//        });
//
//
//
//
//
//    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            java.net.URL url = new java.net.URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            java.io.InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}






