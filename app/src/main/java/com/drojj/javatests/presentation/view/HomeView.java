package com.drojj.javatests.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface HomeView extends MvpView {

    void showProgressBar();

    void dismissProgressBar();

    @StateStrategyType(SkipStrategy.class)
    void startSignInActivity();

}
