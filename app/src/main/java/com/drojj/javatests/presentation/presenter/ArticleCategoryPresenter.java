package com.drojj.javatests.presentation.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.app.App;
import com.drojj.javatests.database.ArticlesDAO;
import com.drojj.javatests.model.articles.ArticleCategoryItem;
import com.drojj.javatests.presentation.view.ArticleCategoryView;
import com.drojj.javatests.ui.fragment.ArticleListFragment;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class ArticleCategoryPresenter extends MvpPresenter<ArticleCategoryView> {

    @Inject
    Router router;

    private List<ArticleCategoryItem> mItems;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mItems = new ArticlesDAO().getCategories();
        getViewState().updateAdapter(mItems);
        App.getNavigationComponent().inject(this);
    }

    public void showArticleListFromCategoryWithId(int id, String categoryName) {
        Bundle bundle = new Bundle();
        bundle.putInt("category_id", id);
        bundle.putString("category_name", categoryName);
        router.navigateTo(ArticleListFragment.TAG, bundle);
    }
}
