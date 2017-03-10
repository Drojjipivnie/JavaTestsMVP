package com.drojj.javatests.presentation.view.auth;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AuthView extends MvpView {
    void clearAllInputs();

    void showError(TextInputType type, String message);

    void hideError(TextInputType type);

    void showProgressDialog();

    void hideProgressDialog();

    enum TextInputType {
        EMAIL, PASSWORD, NAME
    }

}
