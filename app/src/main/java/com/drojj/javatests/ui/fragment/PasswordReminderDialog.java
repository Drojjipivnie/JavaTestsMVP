package com.drojj.javatests.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.presentation.presenter.PasswordReminderDialogPresenter;
import com.drojj.javatests.presentation.view.PasswordReminderDialogView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PasswordReminderDialog extends MvpDialogFragment implements PasswordReminderDialogView {
    public static final String TAG = "PasswordReminderDialog";

    @BindView(R.id.email_reg_wrapper)
    TextInputLayout mEmailTextWrapper;

    @BindView(R.id.email_reg)
    EditText mEmailText;

    @BindView(R.id.root_view_dialog)
    View mRootView;

    @BindString(R.string.please_wait)
    String mPleaseWaitString;

    private Unbinder mViewUnbinder;

    private ProgressDialog mProgressDialog;

    @InjectPresenter
    PasswordReminderDialogPresenter mPasswordReminderDialogPresenter;

    public static PasswordReminderDialog newInstance() {
        return new PasswordReminderDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_password_reminder_dialog, null, false);

        mViewUnbinder = ButterKnife.bind(this, dialogView);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(R.string.loading);
        mProgressDialog.setMessage(mPleaseWaitString);
        mProgressDialog.setCancelable(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.remember_password_title_dialog)
                .setView(dialogView)
                .setPositiveButton(R.string.send, null)
                .setNegativeButton(R.string.cancel, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(v -> mPasswordReminderDialogPresenter.sendReminderRequest(mEmailText.getText().toString()));
        });

        if (mEmailTextWrapper.getEditText() != null) {
            mEmailTextWrapper.getEditText().setOnEditorActionListener((v, actionId, event) -> {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    mPasswordReminderDialogPresenter.sendReminderRequest(mEmailText.getText().toString());
                    handled = true;
                }
                return handled;
            });
        }

        mEmailTextWrapper.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideError();
            }
        });

        return alertDialog;
    }

    @Override
    public void onDestroyView() {
        if (mEmailTextWrapper != null && mEmailTextWrapper.getEditText() != null) {
            mEmailTextWrapper.getEditText().setOnFocusChangeListener(null);
            mEmailTextWrapper.getEditText().setOnEditorActionListener(null);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mViewUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showError(String errorMessage) {
        if (mEmailTextWrapper != null) {
            mEmailTextWrapper.setError(errorMessage);
            mEmailTextWrapper.setErrorEnabled(true);
        }
    }

    @Override
    public void hideError() {
        if (mEmailTextWrapper != null) {
            mEmailTextWrapper.setError("");
            mEmailTextWrapper.setErrorEnabled(false);
        }
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), R.string.send_reminder_password, Toast.LENGTH_SHORT).show();
        this.dismiss();
    }

    @Override
    public void requestFocus() {
        if (mRootView != null) {
            mRootView.requestFocus();
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
        }
    }
}
