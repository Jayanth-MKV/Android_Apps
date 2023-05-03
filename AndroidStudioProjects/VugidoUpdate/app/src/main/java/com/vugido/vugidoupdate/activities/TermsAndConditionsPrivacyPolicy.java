package com.vugido.vugidoupdate.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.vugidoupdate.R;


public class TermsAndConditionsPrivacyPolicy extends AppCompatActivity {

    private String TC_URL="http://www.vugido.com/VUGIDO/terms_and_conditions.html";
    private String PP_URL="http://www.vugido.com/VUGIDO/privacy_policy.html";
    private String ABOUT_US_URL="http://www.vugido.com";
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
