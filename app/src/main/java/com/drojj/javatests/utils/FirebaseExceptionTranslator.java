package com.drojj.javatests.utils;

import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthException;

public final class FirebaseExceptionTranslator {

    public static String getTranslatedExceptionMessage(Exception e) {
        int stringId;
        if (e instanceof FirebaseAuthException) {
            stringId = getAuthExceptionStringId((FirebaseAuthException) e);
        } else if (e instanceof FirebaseException) {
            if (e instanceof FirebaseTooManyRequestsException) {
                stringId = getTooManyRequestsExceptionStringId();
            } else if (e instanceof FirebaseNetworkException) {
                stringId = getNetworkExceptionStringId();
            } else if (e instanceof FirebaseApiNotAvailableException) {
                stringId = getApiNotAvailableExceptionStringId();
            } else {
                return e.getLocalizedMessage();
            }
        } else {
            return e.getLocalizedMessage();
        }

        return App.getAppComponent().getResources().getString(stringId);
    }

    private static int getAuthExceptionStringId(FirebaseAuthException e) {
        int stringId;
        switch (e.getErrorCode()) {
            case "ERROR_WRONG_PASSWORD":
                stringId = R.string.error_wrong_password;
                break;
            case "ERROR_USER_NOT_FOUND":
                stringId = R.string.error_user_not_found;
                break;
            case "ERROR_EMAIL_ALREADY_IN_USE":
                stringId = R.string.error_email_already_in_use;
                break;
            case "ERROR_INVALID_CUSTOM_TOKEN":
                stringId = R.string.error_invalid_custom_token;
                break;
            case "ERROR_USER_DISABLED":
                stringId = R.string.error_user_disabled;
                break;
            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                stringId = R.string.error_custom_token_mismatch;
                break;
            case "ERROR_INVALID_CREDENTIAL":
                stringId = R.string.error_invalid_credential;
                break;
            case "ERROR_INVALID_EMAIL":
                stringId = R.string.error_invalid_email;
                break;
            case "ERROR_USER_MISMATCH":
                stringId = R.string.error_user_mismatch;
                break;
            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                stringId = R.string.error_account_exisits_with_different_credential;
                break;
            case "ERROR_REQUIRES_RECENT_LOGIN":
                stringId = R.string.error_require_recent_login;
                break;
            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                stringId = R.string.error_credential_already_in_use;
                break;
            case "ERROR_USER_TOKEN_EXPIRED":
                stringId = R.string.error_user_token_expired;
                break;
            case "ERROR_INVALID_USER_TOKEN":
                stringId = R.string.error_invalid_user_token;
                break;
            case "ERROR_OPERATION_NOT_ALLOWED":
                stringId = R.string.error_opertion_not_allowed;
                break;
            case "ERROR_WEAK_PASSWORD":
                stringId = R.string.error_weak_password;
                break;
            default:
                stringId = R.string.error_default;
        }
        return stringId;
    }

    private static int getNetworkExceptionStringId() {
        return R.string.error_network_exception;
    }

    private static int getTooManyRequestsExceptionStringId() {
        return R.string.error_too_many_requests;
    }

    private static int getApiNotAvailableExceptionStringId() {
        return R.string.error_api_not_available;
    }
}
