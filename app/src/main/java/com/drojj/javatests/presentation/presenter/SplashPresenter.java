package com.drojj.javatests.presentation.presenter;

import com.drojj.javatests.presentation.view.SplashView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import timber.log.Timber;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {
    public void checkAuthorized(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            Timber.d("Current FireBase user is: \nEmail - %s\nName - %s\nUid - %s", firebaseUser.getEmail(), firebaseUser.getDisplayName(), firebaseUser.getUid());
            getViewState().setAuthorized(true);
        } else {
            Timber.d("Current FireBase user is NULL");
            getViewState().setAuthorized(false);
        }
    }
}
