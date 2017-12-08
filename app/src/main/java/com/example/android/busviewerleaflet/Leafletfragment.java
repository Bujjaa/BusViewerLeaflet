package com.example.android.busviewerleaflet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class Leafletfragment extends Fragment {
    private long backPressedTime = 0;
    private WebView myWebView;



    public Leafletfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leafletfragment, container, false);

        Bundle bundle = this.getArguments();
        String buslinie = "";
        String gps = "";

        if(bundle != null) {
            buslinie = bundle.getString("Buslinie");
            gps = bundle.getString("GPS");
        }

        buslinie = buslinie.toLowerCase();

        myWebView = (WebView) view.findViewById(R.id.webviewLeafletfrag);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        myWebView.setWebViewClient(new WebViewClient());
        Log.d("Leafletfragment", ""+buslinie);
        Log.d("Leafletfragment", ""+gps);
        myWebView.loadUrl("http://www.bujjaa.bplaced.net/"+buslinie);

        return view;


    }

}