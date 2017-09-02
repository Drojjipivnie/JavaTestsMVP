package com.drojj.javatests.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.database.DatabaseInstaller;
import com.drojj.javatests.presentation.presenter.SplashPresenter;
import com.drojj.javatests.presentation.view.SplashView;
import com.drojj.javatests.ui.activity.auth.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends MvpActivity implements SplashView {

    Disposable disposable;

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable<Void> observable = Observable.create(e -> {
            new DatabaseInstaller().install();
            e.onComplete();
        });

        disposable = observable.subscribe((v) -> {
        }, (throwable) -> {
            Toast.makeText(this, getString(R.string.database_installer_error, throwable.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
        }, () -> {
            mSplashPresenter.checkAuthorized(FirebaseAuth.getInstance().getCurrentUser());
        });

    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        SplashActivity.this.startActivity(isAuthorized ? HomeActivity.getIntent(this) : SignInActivity.getIntent(this));
        SplashActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
