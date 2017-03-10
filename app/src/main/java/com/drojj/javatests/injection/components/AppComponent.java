package com.drojj.javatests.injection.components;

import android.content.res.Resources;

import com.drojj.javatests.injection.modules.AppModule;
import com.drojj.javatests.presentation.presenter.auth.SignInPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    Resources getResources();

    void inject(SignInPresenter presenter);
}
