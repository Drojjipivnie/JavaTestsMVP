package com.drojj.javatests.injection.components;

import com.drojj.javatests.injection.modules.AppModule;
import com.drojj.javatests.presentation.presenter.auth.SignInPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(SignInPresenter presenter);
}
