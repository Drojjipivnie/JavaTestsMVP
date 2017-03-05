package com.drojj.javatests.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.drojj.javatests.R;
import com.drojj.javatests.presentation.view.HomeView;
import com.drojj.javatests.presentation.presenter.HomePresenter;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class HomeActivity extends MvpActivity implements HomeView {
    public static final String TAG = "HomeActivity";
    @InjectPresenter
    HomePresenter mHomePresenter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, HomeActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
