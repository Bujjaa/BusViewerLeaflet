package com.example.android.busviewerleaflet;

import android.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class LeafletActivity extends AppCompatActivity {


    private long backPressedTime = 0;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaflet);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Buslinie");

        myWebView = (WebView) findViewById(R.id.webviewLeaflet);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (message.equals("ux1")) {
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("http://www.bujjaa.bplaced.net/ux1");
        } else if (message.equals("ux2")) {
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("http://www.bujjaa.bplaced.net/ux2");
        }

        Log.d("intentmessage", "onCreate: intentmessage " + message);

    }

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press back again to go back",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up#
            finish();
            super.onBackPressed();       // bye
        }
    }



}
