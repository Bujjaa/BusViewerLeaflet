package com.example.android.busviewerleaflet;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Anton on 21.11.2017.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);
    }
}
