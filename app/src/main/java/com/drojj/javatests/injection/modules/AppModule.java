package com.drojj.javatests.injection.modules;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.drojj.javatests.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return mContext.getResources();
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase() {
        return new AppDatabase(mContext);
    }
}
