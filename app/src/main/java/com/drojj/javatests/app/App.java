package com.drojj.javatests.app;

import android.app.Application;

import com.drojj.javatests.BuildConfig;
import com.drojj.javatests.injection.components.AppComponent;
import com.drojj.javatests.injection.components.DaggerAppComponent;
import com.drojj.javatests.injection.components.DaggerNavigationComponent;
import com.drojj.javatests.injection.components.NavigationComponent;
import com.drojj.javatests.injection.modules.AppModule;

import timber.log.Timber;

public class App extends Application {
    private static App mAppInstance;

    private static AppComponent mAppComponent;

    private static NavigationComponent getNavigationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppInstance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static NavigationComponent getNavigationComponent() {
        if (getNavigationComponent == null) {
            getNavigationComponent = DaggerNavigationComponent.builder().build();
        }
        return getNavigationComponent;
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(mAppInstance)).build();
        }
        return mAppComponent;
    }

}
