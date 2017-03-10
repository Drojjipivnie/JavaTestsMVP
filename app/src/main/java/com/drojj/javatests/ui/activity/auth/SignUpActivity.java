package com.drojj.javatests.ui.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.presentation.presenter.auth.SignUpPresenter;
import com.drojj.javatests.presentation.view.auth.SignUpView;

public class SignUpActivity extends MvpActivity implements SignUpView {
    public static final String TAG = "SignUpActivity";
    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, SignUpActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void clearAllInputs() {

    }

    @Override
    public void showError(TextInputType type, String message) {

    }

    @Override
    public void hideError(TextInputType type) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
