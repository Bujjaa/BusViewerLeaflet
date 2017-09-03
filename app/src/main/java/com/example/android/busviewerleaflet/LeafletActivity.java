package com.example.android.busviewerleaflet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class LeafletActivity extends AppCompatActivity {


    private long backPressedTime = 0;
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaflet);

        myWebView = (WebView) findViewById(R.id.webviewLeaflet);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://www.bujjaa.bplaced.net");

    }

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press back again to to move to Homescreen",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up#
            finish();
            super.onBackPressed();       // bye
        }
    }

}
