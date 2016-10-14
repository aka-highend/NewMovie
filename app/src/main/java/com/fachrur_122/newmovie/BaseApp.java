package com.fachrur_122.newmovie;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class BaseApp extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        appContext = getApplicationContext();
    }

    @NonNull
    public static Context getAppContext() { return appContext; }

}
