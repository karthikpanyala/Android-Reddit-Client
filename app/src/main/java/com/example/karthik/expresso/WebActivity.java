package com.example.karthik.expresso;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by karthik on 5/7/16.
 */
public class WebActivity extends FragmentActivity {

    private WebView mWebView;
    private static final String urlTag = "url";

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String postUrl = intent.getStringExtra(urlTag);
        mWebView  = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        final Activity activity = this;

        mWebView.setWebViewClient(new WebViewClient(){

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Handle the error
                Toast.makeText(activity,description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        mWebView.loadUrl(postUrl);
        setContentView(mWebView);
    }
}
