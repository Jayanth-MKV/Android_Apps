package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.sql.Time;
import java.time.Instant;

public class AffiliateProductViewer extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliate);

       // createDynamicLink();
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(this, pendingDynamicLinkData -> {

            Log.e("Link got","Success");
            Uri deepLink=null;

            if(pendingDynamicLinkData!=null){
                deepLink=pendingDynamicLinkData.getLink();

                assert deepLink != null;
                String AffiliateLink=deepLink.toString();
                try {
                    String AffiliateLinkContent=AffiliateLink.substring(AffiliateLink.lastIndexOf("=")+1);

                    Log.e("Content",AffiliateLinkContent);

                    String[] values=AffiliateLinkContent.split("--");
                    String AUID=values[0];
                    String APID=values[1];
                    String TimeMillis=values[2];
                    Log.e("AUID=",AUID);
                    Log.e("APID=",APID);
                    Log.e("MilliSecs",TimeMillis);

                }catch (Exception e){

                }
            }

            if(deepLink!=null){
                Log.e("DeepLink content Uri",deepLink.toString());
            }
        }).addOnFailureListener(this, e -> {
            Log.e("Deeplink","Failed not got");
        });
    }


    private void createDynamicLink(){

        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://www.vugido.com/"))
                .setDomainUriPrefix("https://vugido.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();

        Log.e("Long DLink",dynamicLinkUri.toString());

        shortenLongDynamicLink(dynamicLink.getUri());
    }

    private void shortenLongDynamicLink(Uri longLink){

        String manualLink="https://vugido.page.link/?"+
                "link="+createProductAffiliateLink("15","40","20")+
                "&apn="+getPackageName()+
                "&st="+"Your Product or App Title Goes Here"+
                "&sd="+"Your Product or App Description Goes Here"+
                "&si="+"http://www.vugido.com/VUGIDO/INVENTORY_MANAGEMENT_FILES/NOTIFICATION_IMAGES/1.jpeg";

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                //.setLongLink(longLink) // automatic long link..
                //manually
                .setLongLink(Uri.parse(manualLink))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();

                            Log.e("Short Link",shortLink.toString());
                            Log.e("flowchartLink",flowchartLink.toString());
                        } else {
                            // Error
                            // ...
                            Log.e("Error","Short link not got");
                        }
                    }
                });
    }
     private String createProductAffiliateLink(String UID,String PID,String COINS){

        return "http://www.vugido.com/AFFITIATE_PRODUCT.php?AUID="+UID+"--"+PID+"--"+COINS+"--"+ConfigVariables.getCurrentDate()+"_"+ConfigVariables.getCurrentTime()+"_"+Math.abs(ConfigVariables.getMilliSeconds());

     }
}
