package com.herokuapp.di.components;

import android.app.Application;

import com.herokuapp.HerokuApp;
import com.herokuapp.di.builder.ActivityBuilderModule;
import com.herokuapp.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(HerokuApp herokuApp);
}