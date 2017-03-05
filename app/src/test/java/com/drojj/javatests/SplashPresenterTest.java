package com.drojj.javatests;


import com.drojj.javatests.presentation.presenter.SplashPresenter;
import com.drojj.javatests.presentation.view.SplashView;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SplashPresenterTest {

    @Mock
    SplashView splashView;

    private SplashPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new SplashPresenter();
        presenter.getAttachedViews().add(splashView);
    }

    @Test
    public void splash_shouldAuthorizedStateFalse() {
        FirebaseUser user = null;
        presenter.checkAuthorized(user);
        Mockito.verify(splashView).setAuthorized(false);
    }

    @Test
    public void splash_shouldAuthorizedStateTrue() {
        FirebaseUser user = Mockito.mock(FirebaseUser.class);
        presenter.checkAuthorized(user);
        Mockito.verify(splashView).setAuthorized(true);
    }
}
