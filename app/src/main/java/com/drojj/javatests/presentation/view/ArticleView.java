package com.drojj.javatests.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.drojj.javatests.model.articles.item.Drawable;

import java.util.List;

public interface ArticleView extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void buildArticleView(List<Drawable> drawables);
}
