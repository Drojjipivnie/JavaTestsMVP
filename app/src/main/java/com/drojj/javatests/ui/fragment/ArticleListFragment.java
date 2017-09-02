package com.drojj.javatests.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.drojj.javatests.model.articles.ArticleListItem;
import com.drojj.javatests.presentation.presenter.ArticleListPresenter;
import com.drojj.javatests.presentation.view.ArticleListView;
import com.drojj.javatests.ui.adapters.ArticlesListAdapter;
import com.drojj.javatests.ui.adapters.OnRecyclerItemClickListener;
import com.drojj.javatests.ui.fragment.base.BaselRecyclerViewFragment;

import java.util.List;

public class ArticleListFragment extends BaselRecyclerViewFragment implements ArticleListView, OnRecyclerItemClickListener<ArticleListItem> {
    public static final String TAG = "ArticleListFragment";
    @InjectPresenter
    ArticleListPresenter mArticleListPresenter;

    @ProvidePresenter
    ArticleListPresenter providePresenter() {
        int category_id = getArguments().getInt("category_id");
        return new ArticleListPresenter(category_id);
    }

    public static ArticleListFragment newInstance(Bundle args) {
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void updateAdapter(List<ArticleListItem> mItems) {
        mRecycleView.setAdapter(new ArticlesListAdapter(mItems, this));
    }

    @Override
    public void onClick(ArticleListItem item) {
        mArticleListPresenter.showArticleWithId(item.getId(), item.getTitle());
    }

    @Override
    protected String getToolbarTitle() {
        return getArguments().getString("category_name");
    }
}
