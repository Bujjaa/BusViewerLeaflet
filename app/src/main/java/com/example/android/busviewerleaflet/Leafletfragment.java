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
        String gpsLat = "";
        String gpsLong = "";

        if(bundle != null) {
            buslinie = bundle.getString("Buslinie");
            gpsLat = bundle.getString("GPSLat");
            gpsLong = bundle.getString("GPSLong");
        }

        buslinie = buslinie.toLowerCase();

        myWebView = (WebView) view.findViewById(R.id.webviewLeafletfrag);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);

        myWebView.setWebViewClient(new WebViewClient());
        Log.d("Leafletfragment", ""+buslinie);
        Log.d("Leafletfragment LAT", ""+gpsLat);
        Log.d("Leafletfragment LONG", ""+gpsLong);
        myWebView.loadUrl("http://www.bujjaa.bplaced.net/"+buslinie+"/?lat="+gpsLat+"&long="+gpsLong);

        return view;


    }

}
