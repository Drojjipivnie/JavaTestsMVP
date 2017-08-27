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
import com.drojj.javatests.presentation.presenter.auth.SignUpPresenter;
import com.drojj.javatests.presentation.view.auth.SignUpView;
import com.drojj.javatests.utils.ActivityUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignUpActivity extends MvpActivity implements SignUpView {
    public static final String TAG = "SignUpActivity";
    public static final int REGISTRED = 1;
    public static final int CANCELLED = 0;
    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    @BindView(R.id.input_name_wrapper)
    TextInputLayout mNameWrapper;

    @BindView(R.id.input_email_wrapper)
    TextInputLayout mEmailWrapper;

    @BindView(R.id.input_password_wrapper)
    TextInputLayout mPasswordWrapper;

    @BindView(R.id.input_name)
    EditText mInputName;

    @BindView(R.id.input_email)
    EditText mInputEmail;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @BindView(R.id.signup_view)
    View mView;

    @BindString(R.string.loading)
    String mLoadingString;

    private ProgressDialog mProgressDialog;

    private Unbinder mViewUnbinder;

    public static Intent getIntent(final Context context) {
        return new Intent(context, SignUpActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mViewUnbinder = ButterKnife.bind(this);

        mInputPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            boolean handled = false;
            if (i == EditorInfo.IME_ACTION_SEND) {
                mSignUpPresenter.signUp(mInputName.getText().toString(), mInputEmail.getText().toString(), mInputPassword.getText().toString());
                handled = true;
            }
            return handled;
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(mLoadingString);
    }

    @Override
    public void clearAllInputs() {
        mInputEmail.setText("");
        mInputName.setText("");
        mInputPassword.setText("");
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
            case NAME:
                showError(mNameWrapper, message);
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
            case NAME:
                hideError(mNameWrapper);
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
    public void successSignUp() {
        setResult(REGISTRED);
        SignUpActivity.this.finish();
    }

    @OnClick(R.id.btn_signup)
    public void onLoginButtonClicked() {
        mSignUpPresenter.signUp(mInputName.getText().toString(), mInputEmail.getText().toString(), mInputPassword.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mViewUnbinder.unbind();
    }
}
