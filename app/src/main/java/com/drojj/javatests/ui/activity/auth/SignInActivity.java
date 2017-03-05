package com.drojj.javatests.ui.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.drojj.javatests.R;
import com.drojj.javatests.presentation.view.auth.SignInView;
import com.drojj.javatests.presentation.presenter.auth.SignInPresenter;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class SignInActivity extends MvpActivity implements SignInView {
    public static final String TAG = "SignInActivity";
    @InjectPresenter
    SignInPresenter mSignInPresenter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, SignInActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
