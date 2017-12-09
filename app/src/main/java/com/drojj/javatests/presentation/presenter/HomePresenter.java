package com.drojj.javatests.presentation.presenter;

import com.drojj.javatests.app.App;
import com.drojj.javatests.presentation.view.HomeView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    @Inject
    Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getNavigationComponent().inject(this);
    }

    public void exitToSignInScreen() {
        router.finishChain();
    }

    public void showProgressBar(){
        getViewState().showProgressBar();
    }

    public void dismissProgressBar(){
        getViewState().dismissProgressBar();
    }
}
