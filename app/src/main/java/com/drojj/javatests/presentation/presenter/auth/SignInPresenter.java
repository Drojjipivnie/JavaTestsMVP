package com.drojj.javatests.presentation.presenter.auth;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.drojj.javatests.presentation.view.auth.SignInView;
import com.drojj.javatests.utils.AuthDataValidator;
import com.drojj.javatests.utils.FirebaseExceptionTranslator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import timber.log.Timber;

import static com.drojj.javatests.presentation.view.auth.AuthView.TextInputType.EMAIL;
import static com.drojj.javatests.presentation.view.auth.AuthView.TextInputType.PASSWORD;

@InjectViewState
public class SignInPresenter extends MvpPresenter<SignInView> implements OnCompleteListener<AuthResult> {

    @Inject
    Resources resources;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getAppComponent().inject(this);
    }

    public void signIn(String email, String password) {
        hideErrors();
        getViewState().hideKeyboard();

        if (checkCredentials(email, password)) {
            getViewState().showProgressDialog();
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this);
        }
    }

    private boolean checkCredentials(String email, String password) {
        boolean isOk = true;

        if (!AuthDataValidator.validateEmail(email)) {
            Timber.d("Wrong email");
            getViewState().showError(EMAIL, resources.getString(R.string.email_not_validated));
            isOk = false;
        }

        if (password.isEmpty()) {
            Timber.d("Empty password");
            getViewState().showError(PASSWORD, resources.getString(R.string.password_not_entered));
            isOk = false;
        }

        return isOk;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        getViewState().hideProgressDialog();
        if (task.isSuccessful()) {
            Timber.d("Successful sign in. Uid is %s", task.getResult().getUser().getUid());
            getViewState().successSignIn();
        } else {
            Timber.e(task.getException(), "Fail sign in");
            getViewState().showError(EMAIL, FirebaseExceptionTranslator.getTranslatedExceptionMessage(task.getException()));
        }
    }

    private void hideErrors() {
        getViewState().hideError(EMAIL);
        getViewState().hideError(PASSWORD);
    }

    public void signUp() {
        getViewState().clearAllInputs();
        hideErrors();
        getViewState().signUp();
    }
}
