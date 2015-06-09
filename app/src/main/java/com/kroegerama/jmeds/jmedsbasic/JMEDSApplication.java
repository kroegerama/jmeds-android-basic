package com.kroegerama.jmeds.jmedsbasic;

import android.app.Application;

import org.ws4d.java.android.Util;

public class JMEDSApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Util.initAll(getApplicationContext());
    }
}
