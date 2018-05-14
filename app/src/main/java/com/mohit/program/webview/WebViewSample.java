package com.mohit.program.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 12:20 PM.
 */

public class WebViewSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        // linking with XML element
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.setVisibility(View.VISIBLE);

        // use to load specific URL
        /*webview.loadUrl("http://www.google.com");*/

        //loading local file assets folder
        webview.loadUrl("file:///android_asset/index.html");

        // getting setting to enable controls
        WebSettings ws1 = webview.getSettings();

        // enable java script
        ws1.setJavaScriptEnabled(true);

        // enable zoom in
        ws1.setBuiltInZoomControls(true);

        // Use to load URL in given Web view instead of browser
        webview.setWebViewClient(new Callback());

        // clearing history of browser
        webview.clearHistory();

        // clearing cache of browser
        webview.clearCache(true);

        // enabling java script
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.setWebChromeClient(new WebChromeClient());
    }

    /**
     * Use to load URL in given Web view instead of browser
     */
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
}
