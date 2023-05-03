package com.foodhub.vugido.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodhub.vugido.R;


public class TermsAndConditionsPrivacyPolicy extends AppCompatActivity {

    private String TC_URL="http://www.vugido.com/ANDHRA_PRADESH/SRIKAKULAM/MINERVA_HOTEL/PHP_FILES/terms_and_conditions.html";
    private String PP_URL="http://www.vugido.com/ANDHRA_PRADESH/SRIKAKULAM/MINERVA_HOTEL/PHP_FILES/privacy_policy.html";
    private String ABOUT_US_URL="http://www.vugido.com";
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        setContentView(R.layout.activity_tc_pp);

        webView=findViewById(R.id.tc_pp);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        int ID=getIntent().getIntExtra("ID",0);

        if(ID==1)
            webView.loadUrl(TC_URL);
        else if(ID==2)
            webView.loadUrl(PP_URL);
        else
            webView.loadUrl(ABOUT_US_URL);

    }
}
