package com.drojj.javatests.ui.fragment.base;

import com.arellomobile.mvp.MvpAppCompatFragment;

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

    protected abstract String getToolbarTitle();
}
