package com.drojj.javatests.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.drojj.javatests.presentation.presenter.HomePresenter;
import com.drojj.javatests.presentation.view.HomeView;
import com.drojj.javatests.ui.activity.auth.SignInActivity;
import com.drojj.javatests.ui.fragment.ArticleCategoryFragment;
import com.drojj.javatests.ui.fragment.ArticleFragment;
import com.drojj.javatests.ui.fragment.ArticleListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class HomeActivity extends MvpAppCompatActivity implements HomeView, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "HomeActivity";

    @InjectPresenter
    HomePresenter mHomePresenter;

    @BindView(R.id.toolbar_main)
    Toolbar mToolbarMain;

    @BindView(R.id.navigation_view_main)
    NavigationView mNavigationViewMain;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.progress_bar_main)
    ProgressBar mProgressBar;

    @Inject
    NavigatorHolder navigatorHolder;

    private Unbinder mUnbinder;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragment_container_main) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case ArticleCategoryFragment.TAG:
                    return ArticleCategoryFragment.newInstance();
                case ArticleListFragment.TAG:
                    return ArticleListFragment.newInstance((Bundle) data);
                case ArticleFragment.TAG:
                    return ArticleFragment.newInstance((Bundle) data);
                default:
                    throw new RuntimeException("Unknown screen key");
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            FirebaseAuth.getInstance().signOut();
            startActivity(SignInActivity.getIntent(HomeActivity.this));
            HomeActivity.this.finish();
        }

        @Override
        public void applyCommand(Command command) {
            super.applyCommand(command);
        }
    };

    public static Intent getIntent(final Context context) {
        return new Intent(context, HomeActivity.class);
    }

    public HomePresenter getHomePresenter() {
        return mHomePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getNavigationComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        mUnbinder = ButterKnife.bind(this);

        setSupportActionBar(mToolbarMain);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbarMain, 0, 0);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationViewMain.setNavigationItemSelectedListener(this);

        View v = mNavigationViewMain.getHeaderView(0);
        TextView name = ButterKnife.findById(v, R.id.navigation_header_name);
        TextView email = ButterKnife.findById(v, R.id.navigation_header_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());

        if (savedInstanceState == null) {
            navigator.applyCommand(new Replace(ArticleCategoryFragment.TAG, 1));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navigation_log_out) {
            mHomePresenter.exitToSignInScreen();
            return false;
        } else {
            //router.showSystemMessage("some " + item.getTitle());
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
