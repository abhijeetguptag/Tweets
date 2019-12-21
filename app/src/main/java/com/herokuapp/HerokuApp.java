package com.herokuapp;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

public class HerokuApp extends Application {

    private static HerokuApp sInstance;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    public static HerokuApp getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(HerokuApp app) {
        sInstance = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}

