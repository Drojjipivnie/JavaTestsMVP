package com.drojj.javatests.injection.components;

import com.drojj.javatests.injection.modules.NavigationModule;
import com.drojj.javatests.presentation.presenter.ArticleCategoryPresenter;
import com.drojj.javatests.presentation.presenter.ArticleListPresenter;
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

    void inject(ArticleCategoryPresenter articleCategoryPresenter);

    void inject(ArticleListPresenter articleListPresenter);
}
