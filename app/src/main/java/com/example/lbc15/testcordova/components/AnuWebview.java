package com.example.lbc15.testcordova.components;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
    private final AuiWebClient webviewClient;

    public AnuWebview(final Context context, AttributeSet attrs) {
        super(context, attrs);
        final AnuWebview self = this;

        WebSettings settings = this.getSettings();

        settings.setDefaultTextEncodingName("UTF-8");

        // 支持缩放
        settings.setSupportZoom(true);
        // 调整到适合webview大小
        settings.setLoadWithOverviewMode(true);
        // 调整到适合webview大小
        settings.setUseWideViewPort(true);
        //屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
//        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        settings.setLoadsImagesAutomatically(true);

        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);

        //开启javascript
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //开启DOM
        settings.setDomStorageEnabled(true);

        // 支持文件流
        settings.setAllowFileAccess(true);

        // 加速
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        this.webviewClient = new AuiWebClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Logger.i("on Page Finished");
                Toast.makeText(context, "load Page Finished", Toast.LENGTH_SHORT).show();
                self.initJavscript();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Logger.i("load error");
            }
        };

        this.setWebViewClient(webviewClient);

//        this.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                Logger.i("call alert fun" + message);
//                return super.onJsAlert(view, url, message, result);
//            }
//        });

    }

    private void initJavscript() {
        this.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                Logger.i("calljs "+value);
            }
        });
    }
}
