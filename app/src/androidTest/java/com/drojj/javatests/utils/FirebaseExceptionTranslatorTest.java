package com.drojj.javatests.utils;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.drojj.javatests.R;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class FirebaseExceptionTranslatorTest extends Assert {

    Resources resources;

    Map<Exception, Integer> map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        resources = InstrumentationRegistry.getTargetContext().getResources();

        map.put(new FirebaseAuthException("ERROR_WRONG_PASSWORD", "test"), R.string.error_wrong_password);
        map.put(new FirebaseAuthException("ERROR_USER_NOT_FOUND", "test"), R.string.error_user_not_found);
        map.put(new FirebaseAuthException("ERROR_EMAIL_ALREADY_IN_USE", "test"), R.string.error_email_already_in_use);
        map.put(new FirebaseAuthException("ERROR_INVALID_CUSTOM_TOKEN", "test"), R.string.error_invalid_custom_token);
        map.put(new FirebaseAuthException("ERROR_USER_DISABLED", "test"), R.string.error_user_disabled);
        map.put(new FirebaseAuthException("ERROR_CUSTOM_TOKEN_MISMATCH", "test"), R.string.error_custom_token_mismatch);
        map.put(new FirebaseAuthException("ERROR_INVALID_CREDENTIAL", "test"), R.string.error_invalid_credential);
        map.put(new FirebaseAuthException("ERROR_USER_MISMATCH", "test"), R.string.error_user_mismatch);
        map.put(new FirebaseAuthException("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "test"), R.string.error_account_exisits_with_different_credential);
        map.put(new FirebaseAuthException("ERROR_REQUIRES_RECENT_LOGIN", "test"), R.string.error_require_recent_login);
        map.put(new FirebaseAuthException("ERROR_CREDENTIAL_ALREADY_IN_USE", "test"), R.string.error_credential_already_in_use);
        map.put(new FirebaseAuthException("ERROR_USER_TOKEN_EXPIRED", "test"), R.string.error_user_token_expired);
        map.put(new FirebaseAuthException("ERROR_INVALID_USER_TOKEN", "test"), R.string.error_invalid_user_token);
        map.put(new FirebaseAuthException("ERROR_OPERATION_NOT_ALLOWED", "test"), R.string.error_opertion_not_allowed);
        map.put(new FirebaseAuthException("ERROR_WEAK_PASSWORD", "test"), R.string.error_weak_password);
        map.put(new FirebaseAuthException("Some other error", "test"), R.string.error_default);

        map.put(new FirebaseTooManyRequestsException("test"), R.string.error_too_many_requests);
        map.put(new FirebaseNetworkException("test"), R.string.error_network_exception);
        map.put(new FirebaseApiNotAvailableException("test"), R.string.error_api_not_available);
    }

    @Test
    public void test_allExceptionsTranslatedCorrectly() throws Exception {
        for (Exception exception : map.keySet()) {
            String a = FirebaseExceptionTranslator.getTranslatedExceptionMessage(exception);
            assertEquals(a, resources.getString(map.get(exception)));
        }
    }

    @After
    public void tearDown() throws Exception {
        resources = null;
        map = null;
    }
}
