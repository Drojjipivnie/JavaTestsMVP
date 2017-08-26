package com.drojj.javatests.presentation.presenter.auth;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.drojj.javatests.presentation.view.auth.SignUpView;
import com.drojj.javatests.utils.AuthDataValidator;
import com.drojj.javatests.utils.FirebaseExceptionTranslator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import javax.inject.Inject;

import timber.log.Timber;

import static com.drojj.javatests.presentation.view.auth.AuthView.TextInputType.EMAIL;
import static com.drojj.javatests.presentation.view.auth.AuthView.TextInputType.NAME;
import static com.drojj.javatests.presentation.view.auth.AuthView.TextInputType.PASSWORD;

@InjectViewState
public class SignUpPresenter extends MvpPresenter<SignUpView> implements OnCompleteListener<AuthResult> {

    private String mUserName;

    @Inject
    Resources resources;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getComponent().inject(this);
    }

    public void signUp(String name, String email, String password) {
        hideErrors();

        getViewState().hideKeyboard();

        if (checkCredentials(name, email, password)) {
            mUserName = name;
            getViewState().showProgressDialog();
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this);
        }
    }

    private boolean checkCredentials(String name, String email, String password) {
        boolean isOk = true;

        if (!AuthDataValidator.validatePassword(password)) {
            isOk = false;
            if (password.isEmpty()) {
                getViewState().showError(PASSWORD, resources.getString(R.string.password_not_entered));
            } else {
                getViewState().showError(PASSWORD, resources.getString(R.string.password_not_validated));
            }
        }

        if (!AuthDataValidator.validateEmail(email)) {
            getViewState().showError(EMAIL, resources.getString(R.string.email_not_validated));
            isOk = false;
        }

        if (!AuthDataValidator.validateName(name)) {
            getViewState().showError(NAME, resources.getString(R.string.name_not_validated));
            isOk = false;
        }

        return isOk;
    }

    private void hideErrors() {
        getViewState().hideError(EMAIL);
        getViewState().hideError(PASSWORD);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Timber.d("Successful sign up. Uid is %s", task.getResult().getUser().getUid());

            FirebaseAuth.getInstance().getCurrentUser()
                    .updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(mUserName).build())
                    .addOnCompleteListener(task1 -> {
                        getViewState().hideProgressDialog();
                        getViewState().successSignUp();
                    });
        } else {
            getViewState().hideProgressDialog();
            Timber.e(task.getException(), "Failed sign up");
            getViewState().showError(EMAIL, FirebaseExceptionTranslator.getTranslatedExceptionMessage(task.getException()));
        }
    }
}
