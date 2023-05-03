package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.Objects;

public class ReferEarnPolicy extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button button;
    Toolbar toolbar;
    private MyDialogLoader myDialogLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_terms_conditions);

        button=findViewById(R.id.button10);
        button.setOnClickListener(this);

        toolbar=findViewById(R.id.ReferEarnToolbar);
        toolbar.setTitle(getIntent().getStringExtra("TITLE"));

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        webView=findViewById(R.id.tc_pp);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("URL"));
        if(getIntent().getIntExtra("BUTTON",0)==1){
            button.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button10){
            createDynamicLink();

        }
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

     //   Log.e("Long DLink",dynamicLinkUri.toString());

        shortenLongDynamicLink(dynamicLink.getUri());
    }

    private void shortenLongDynamicLink(Uri longLink){
        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Generating Share Link");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        String manualLink="https://vugido.page.link/?"+
                "link="+createProductAffiliateLink(new MyPreferences(this).getUID(),"1")+
                "&apn="+getPackageName()+
                "&st="+"Use my link and Earn Rs.50/- by installing Vugido Food-Hub App in form of coins"+
                "&sd="+"Buy Vegetables,Tiffins ,Groceries, Foods, Snacks, Fruits, and many more."+
                "&si="+"http://www.vugido.com/vugido_logo.jpeg";

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                //.setLongLink(longLink) // automatic long link..
                //manually
                .setLongLink(Uri.parse(manualLink))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            if(myDialogLoader!=null)
                                myDialogLoader.dismiss();

                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();

                            assert shortLink != null;
//                            Log.e("Short Link",shortLink.toString());
//                            Log.e("flowchartLink",flowchartLink.toString());


                            Intent intent=new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "\nMade By Srikakulam Government Polytechnic Students \n");
                            intent.putExtra(Intent.EXTRA_TEXT, "\nMade By Srikakulam Government Polytechnic Students \nBuy Vegetables, Fruits, Non-veg, Foods , Dr-Fruits and  many more : " + "\n\n" + shortLink.toString());
                            startActivity(Intent.createChooser(intent,"\nShare Vugido Food-Hub\n"));
                        } else {
                            // Error
                            // ...
                            if(myDialogLoader!=null)
                                myDialogLoader.dismiss();

                            Log.e("Error","Short link not got");
                        }
                    }
                });
    }
    private String createProductAffiliateLink(String UID,String TYPE){

        return "http://www.vugido.com/SEND_USER_USED_REFERRAL_CODE.php?AUID="+TYPE+"--"+UID+"--"+ ConfigVariables.getCurrentDate()+"_"+ConfigVariables.getCurrentTime()+"_"+Math.abs(ConfigVariables.getMilliSeconds());

    }


}
