package com.drojj.javatests.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.drojj.javatests.presentation.view.SplashView;
import com.drojj.javatests.presentation.presenter.SplashPresenter;

import com.drojj.javatests.R;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.ui.activity.auth.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends MvpActivity implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter.checkAuthorized(FirebaseAuth.getInstance().getCurrentUser());
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        SplashActivity.this.startActivity(isAuthorized ? HomeActivity.getIntent(this) : SignInActivity.getIntent(this));
        SplashActivity.this.finish();
    }
}
