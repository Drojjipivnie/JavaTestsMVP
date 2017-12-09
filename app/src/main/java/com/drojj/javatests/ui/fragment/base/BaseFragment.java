package com.drojj.javatests.ui.fragment.base;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.drojj.javatests.presentation.presenter.HomePresenter;
import com.drojj.javatests.ui.activity.HomeActivity;

import butterknife.Unbinder;


public abstract class BaseFragment extends MvpAppCompatFragment {
    private String mToolbarTitle;

    protected Unbinder mUnbinder;

    @Override
    public void onStart() {
        super.onStart();
        mToolbarTitle = getToolbarTitle();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mToolbarTitle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected HomePresenter getHomePresenter() {
        return ((HomeActivity) getActivity()).getHomePresenter();
    }

    protected abstract String getToolbarTitle();
}
