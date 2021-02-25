package com.gipra.kelebek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoToShero extends AppCompatActivity {
    private WebView sheroweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_shero);
        sheroweb=findViewById(R.id.sheroweb);
        WebSettings webSettings=sheroweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        sheroweb.loadUrl("https://www.mykelebek.com");
        sheroweb.setWebViewClient(new WebViewClient());

    }
    public void onBackPressed() {
        if (sheroweb.canGoBack()){
            sheroweb.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
