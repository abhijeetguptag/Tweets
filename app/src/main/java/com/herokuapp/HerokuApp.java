package com.herokuapp;

import android.app.Activity;
import android.app.Application;

import com.herokuapp.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class HerokuApp extends Application implements HasActivityInjector {

    private static HerokuApp sInstance;

    @Inject
    protected DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    public static HerokuApp getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(HerokuApp app) {
        sInstance = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
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

