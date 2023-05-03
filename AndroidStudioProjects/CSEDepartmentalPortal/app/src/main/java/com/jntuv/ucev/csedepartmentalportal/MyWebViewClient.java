package com.jntuv.ucev.csedepartmentalportal;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//Create a class that extends WebViewClient//

public class MyWebViewClient extends WebViewClient {
    @Override
//Implement shouldOverrideUrlLoading//
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

//Check whether the URL contains a whitelisted domain. In this example, we’re checking
//whether the URL contains the “example.com” string//
        if(Uri.parse(url).getHost().endsWith("example.com")) {

//If the URL does contain the “example.com” string, then the shouldOverrideUrlLoading method
//will return ‘false” and the URL will be loaded inside your WebView//
            return false;
        }

//If the URL doesn’t contain this string, then it’ll return “true.” At this point, we’ll
//launch the user’s preferred browser, by firing off an Intent//
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}
