package com.drojj.javatests.app;

import android.app.Application;

import com.drojj.javatests.BuildConfig;
import com.drojj.javatests.injection.components.AppComponent;
import com.drojj.javatests.injection.components.DaggerAppComponent;
import com.drojj.javatests.injection.modules.AppModule;

import timber.log.Timber;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
