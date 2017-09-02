package com.drojj.javatests.injection.components;

import com.drojj.javatests.injection.modules.NavigationModule;
import com.drojj.javatests.presentation.presenter.HomePresenter;
import com.drojj.javatests.ui.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NavigationModule.class,
})
public interface NavigationComponent {

    void inject(HomeActivity activity);

    void inject(HomePresenter homePresenter);
}
