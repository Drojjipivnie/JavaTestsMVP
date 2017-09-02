package com.drojj.javatests.presentation.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.app.App;
import com.drojj.javatests.database.ArticlesDAO;
import com.drojj.javatests.model.articles.ArticleListItem;
import com.drojj.javatests.presentation.view.ArticleListView;
import com.drojj.javatests.ui.fragment.ArticleFragment;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class ArticleListPresenter extends MvpPresenter<ArticleListView> {
    @Inject
    Router router;

    private final int mCategoryId;

    private List<ArticleListItem> mItems;

    public ArticleListPresenter(int categoryId) {
        super();
        mCategoryId = categoryId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mItems = new ArticlesDAO().getArticleListById(mCategoryId);
        getViewState().updateAdapter(mItems);
        App.getNavigationComponent().inject(this);
    }

    public void showArticleWithId(int id, String articleTitle) {
        Bundle bundle = new Bundle();
        bundle.putInt("article_id", id);
        bundle.putString("article_title", articleTitle);
        router.navigateTo(ArticleFragment.TAG, bundle);
    }
}
