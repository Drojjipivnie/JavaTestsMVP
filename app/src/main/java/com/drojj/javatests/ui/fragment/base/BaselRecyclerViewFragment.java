package com.drojj.javatests.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drojj.javatests.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaselRecyclerViewFragment extends BaseFragment {

    @BindView(R.id.fragment_recycle_view)
    protected RecyclerView mRecycleView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(getLayoutManager());
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();
}
