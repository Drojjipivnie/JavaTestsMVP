package com.drojj.javatests.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PasswordReminderDialogView extends MvpView {

    void showError(String errorMessage);

    void hideError();

    void showToast();

    @StateStrategyType(SingleStateStrategy.class)
    void requestFocus();

    void showProgressDialog();

    void hideProgressDialog();
}
