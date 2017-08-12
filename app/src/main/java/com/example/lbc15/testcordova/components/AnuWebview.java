package com.example.lbc15.testcordova.components;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lbc15.testcordova.utils.Logger;

/**
 * Created by lbc15 on 2017/8/11.
 */

class AuiWebClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        Logger.i("request url is: " + url);
//        return super.shouldOverrideUrlLoading(view, request);
        return false;
    }
}

public class AnuWebview extends WebView {
    public AnuWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        WebSettings settings = this.getSettings();
        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        this.setWebViewClient(new AuiWebClient());
    }


}
