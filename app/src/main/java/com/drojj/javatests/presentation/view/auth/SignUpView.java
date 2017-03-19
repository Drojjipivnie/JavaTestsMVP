package com.drojj.javatests.presentation.view.auth;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SignUpView extends AuthView {
    @StateStrategyType(SkipStrategy.class)
    void successSignUp();
}
