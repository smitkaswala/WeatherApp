package com.bbot.darkweatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class PrivacyPolicyActivity extends AppCompatActivity {

    android.webkit.WebView webView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        webView = findViewById(R.id.webView);
        toolbar = findViewById(R.id.toolbar);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl("https://bitbotdevelopers.blogspot.com/2020/11/bit-bot-privacy-policy.html");
    }

    public void onBack(View view) {
        finish();
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(android.webkit.WebView view, KeyEvent event) {
            return false;
        }
    }
}