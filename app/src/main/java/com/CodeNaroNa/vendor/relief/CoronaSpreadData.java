package com.CodeNaroNa.vendor.relief;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CoronaSpreadData extends AppCompatActivity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_spread_data);
        initialiseView();

        web.loadUrl("https://vendor-relief.web.app/data.html");
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    private void initialiseView() {
        web=findViewById(R.id.web);
    }
}
