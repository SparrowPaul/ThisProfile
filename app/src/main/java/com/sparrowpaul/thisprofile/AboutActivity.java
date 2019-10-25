package com.sparrowpaul.thisprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://intestinc.com";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (getSupportActionBar() != null){ //handles elements on the action bar
            getSupportActionBar().setTitle("About INTEST");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //changing the color of the nav keys
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        progressDialog = new ProgressDialog(this); // initializing the progress dialog
        loadWebView(); // function to load the web view
    }

    public void loadWebView(){

        // for webView
        webView = findViewById(R.id.webViewID); // finding web view id
        webView.loadUrl(url); // loading the url

        webView.setWebViewClient(new HelloWeb());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
    }

    private class HelloWeb extends WebViewClient {  // web view client class

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            progressDialog.setMessage("loading content ...");
            progressDialog.show();

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            progressDialog.dismiss();

            super.onPageFinished(view, url);

        }
    }


    // handles the top back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
