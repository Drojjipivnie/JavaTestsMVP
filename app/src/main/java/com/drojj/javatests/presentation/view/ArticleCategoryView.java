package com.drojj.javatests.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.drojj.javatests.model.articles.ArticleCategoryItem;

import java.util.List;

public interface ArticleCategoryView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateAdapter(List<ArticleCategoryItem> mItems);

}
