package com.drojj.javatests.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.database.ArticlesDAO;
import com.drojj.javatests.model.articles.ArticleCategoryItem;
import com.drojj.javatests.presentation.view.ArticleCategoryView;

import java.util.List;

@InjectViewState
public class ArticleCategoryPresenter extends MvpPresenter<ArticleCategoryView> {

    private List<ArticleCategoryItem> mItems;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mItems = new ArticlesDAO().getCategories();
        getViewState().updateAdapter(mItems);
    }

}
