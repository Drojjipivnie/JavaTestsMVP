package com.drojj.javatests.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.drojj.javatests.model.articles.ArticleCategoryItem;
import com.drojj.javatests.presentation.presenter.ArticleCategoryPresenter;
import com.drojj.javatests.presentation.view.ArticleCategoryView;
import com.drojj.javatests.ui.adapters.ArticlesCategoryAdapter;
import com.drojj.javatests.ui.fragment.base.BaselRecyclerViewFragment;

import java.util.List;

public class ArticleCategoryFragment extends BaselRecyclerViewFragment implements ArticleCategoryView {
    public static final String TAG = "ArticleCategoryFragment";
    @InjectPresenter
    ArticleCategoryPresenter mArticleCategoryPresenter;

    public static ArticleCategoryFragment newInstance() {
        return new ArticleCategoryFragment();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void updateAdapter(List<ArticleCategoryItem> mItems) {
        mRecycleView.setAdapter(new ArticlesCategoryAdapter(mItems));
    }
}
