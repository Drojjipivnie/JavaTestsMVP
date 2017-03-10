package com.drojj.javatests.ui.activity.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.presentation.presenter.auth.SignInPresenter;
import com.drojj.javatests.presentation.view.auth.SignInView;
import com.drojj.javatests.ui.activity.HomeActivity;
import com.drojj.javatests.utils.ActivityUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignInActivity extends MvpActivity implements SignInView {
    public static final String TAG = "SignInActivity";
    @InjectPresenter
    SignInPresenter mSignInPresenter;

    @BindView(R.id.login_password_wrapper)
    TextInputLayout mPasswordWrapper;

    @BindView(R.id.login_email_wrapper)
    TextInputLayout mEmailWrapper;

    @BindView(R.id.edittext_login)
    EditText mEmailField;

    @BindView(R.id.edittext_password)
    EditText mPasswordField;

    @BindView(R.id.login_view)
    View mView;

    @BindString(R.string.loading)
    String mLoadingString;

    private ProgressDialog mProgressDialog;

    private Unbinder mViewUnbinder;

    public static Intent getIntent(final Context context) {
        return new Intent(context, SignInActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mViewUnbinder = ButterKnife.bind(this);

        mEmailField.setOnEditorActionListener((textView, i, keyEvent) -> {
            boolean handled = false;
            if (i == EditorInfo.IME_ACTION_NEXT) {
                mPasswordField.requestFocus();
                handled = true;
            }
            return handled;
        });

        mPasswordField.setOnEditorActionListener((textView, i, keyEvent) -> {
            boolean handled = false;
            if (i == EditorInfo.IME_ACTION_SEND) {
                mSignInPresenter.signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                handled = true;
            }
            return handled;
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(mLoadingString);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mViewUnbinder.unbind();
    }

    @Override
    public void clearAllInputs() {
        mEmailField.setText("");
        mPasswordField.setText("");
        mView.requestFocus();
    }

    @Override
    public void showError(TextInputType type, String message) {
        switch (type) {
            case EMAIL:
                showError(mEmailWrapper, message);
                break;
            case PASSWORD:
                showError(mPasswordWrapper, message);
                break;
        }
    }

    private void showError(TextInputLayout layout, String message) {
        if (layout != null) {
            layout.setErrorEnabled(true);
            layout.setError(message);
        }
    }

    @Override
    public void hideError(TextInputType type) {
        switch (type) {
            case EMAIL:
                hideError(mEmailWrapper);
                break;
            case PASSWORD:
                hideError(mPasswordWrapper);
                break;
        }
    }

    private void hideError(TextInputLayout layout) {
        if (layout != null) {
            layout.setError("");
            layout.setErrorEnabled(false);
        }
    }

    @Override
    public void showProgressDialog() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mView.requestFocus();
        }
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboardFrom(this, this.getCurrentFocus());
    }

    @Override
    public void showReminderDialog() {

    }

    @Override
    public void makeControlsUnclickable() {
        ButterKnife.findById(this, R.id.button_login).setEnabled(false);
        ButterKnife.findById(this, R.id.link_signup).setEnabled(false);
        ButterKnife.findById(this, R.id.link_forgot_password).setEnabled(false);
    }

    @Override
    public void successSignIn() {
        SignInActivity.this.startActivity(HomeActivity.getIntent(this));
        SignInActivity.this.finish();
    }

    @Override
    public void signUp() {
        SignInActivity.this.startActivity(SignUpActivity.getIntent(this));
    }


    @OnClick(R.id.button_login)
    public void onLoginButtonClicked() {
        mSignInPresenter.signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
    }

    @OnClick(R.id.link_signup)
    public void onSignUpLinkClicked() {
        mSignInPresenter.signUp();
    }

    @OnClick(R.id.link_forgot_password)
    public void onForgotPasswordLinkClicked() {
        //TODO: call forgot password dialog
    }

}
