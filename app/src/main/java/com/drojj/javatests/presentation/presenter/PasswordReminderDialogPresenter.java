package com.drojj.javatests.presentation.presenter;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.drojj.javatests.presentation.view.PasswordReminderDialogView;
import com.drojj.javatests.utils.AuthDataValidator;
import com.drojj.javatests.utils.FirebaseExceptionTranslator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

@InjectViewState
public class PasswordReminderDialogPresenter extends MvpPresenter<PasswordReminderDialogView> implements OnCompleteListener<Void> {

    @Inject
    Resources resources;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getAppComponent().inject(this);
    }

    public void sendReminderRequest(String email) {
        if (email.isEmpty() || !AuthDataValidator.validateEmail(email)) {
            getViewState().requestFocus();
            getViewState().showError(resources.getString(R.string.reminder_email_not_validated));
            return;
        }

        getViewState().showProgressDialog();

        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (!task.isSuccessful()) {
            getViewState().requestFocus();
            getViewState().showError(FirebaseExceptionTranslator.getTranslatedExceptionMessage(task.getException()));
        } else {
            getViewState().showToast();
        }
        getViewState().hideProgressDialog();
    }


}
