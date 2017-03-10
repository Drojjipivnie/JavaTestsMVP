package com.drojj.javatests.presentation.view.auth;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SignInView extends AuthView {
    void showReminderDialog();

    void makeControlsUnclickable();

    @StateStrategyType(SkipStrategy.class)
    void successSignIn();

    @StateStrategyType(SkipStrategy.class)
    void signUp();
}
