package com.drojj.javatests.injection.components;

import com.drojj.javatests.injection.modules.AppModule;
import com.drojj.javatests.ui.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

}
