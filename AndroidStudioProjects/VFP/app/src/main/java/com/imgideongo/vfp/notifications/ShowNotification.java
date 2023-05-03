package com.imgideongo.vfp.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

import com.imgideongo.vfp.ConfigVariables.NotificationConstants;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.activity.MainActivity;


public class ShowNotification {

    private static final int NOTIFICATION_REQUEST_CODE = 1;
    private Context context;
    private static ShowNotification showNotificationInstance;

    private ShowNotification(Context context){

        this.context=context;
    }

    public static synchronized ShowNotification getInstance(Context context){
        if(showNotificationInstance==null)
        showNotificationInstance=new ShowNotification(context);
        return showNotificationInstance;
    }




    public void appUpdateAvailable(){

        Intent intent;
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
        PendingIntent pendingIntent=PendingIntent.getActivity(context,NOTIFICATION_REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.vugido)
                .setAutoCancel(true)
                .setContentTitle("Update Available");
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.notify(1,builder.build());
        }
    }

    public  void orderDispatchedNotification(int ID,String title){

        Intent resultIntent=new Intent(context,MainActivity.class);
        //resultIntent.putExtra("OID",ID);
       // resultIntent.putExtra("S",1);
        PendingIntent pendingIntent=
                PendingIntent.getActivity(context,NOTIFICATION_REQUEST_CODE,resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.vugido)
                .setBadgeIconType(R.drawable.vugido)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.vugido))
                .setContentTitle(title);

        builder.setContentIntent(pendingIntent);


        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManager!=null){
            notificationManager.notify(1,builder.build());
        }

    }

    public  void orderProcessedNotification(int ID,String title){

        Intent resultIntent=new Intent(context, MainActivity.class);
       // resultIntent.putExtra("OID",ID);
        PendingIntent pendingIntent=
                PendingIntent.getActivity(context,NOTIFICATION_REQUEST_CODE,resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.vugido)
                .setBadgeIconType(R.drawable.vugido)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.vugido))
                .setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManager!=null){
            notificationManager.notify(1,builder.build());
        }
    }
}
