package com.drojj.javatests.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.database.ArticlesDAO;
import com.drojj.javatests.model.articles.item.Article;
import com.drojj.javatests.presentation.view.ArticleView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@InjectViewState
public class ArticlePresenter extends MvpPresenter<ArticleView> {

    private final int mArticleId;

    private Article mArticle;

    public ArticlePresenter(int articleId) {
        super();
        mArticleId = articleId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        String json = new ArticlesDAO().getArticleJSON(mArticleId);
        try {
            mArticle = new ObjectMapper().readValue(json, Article.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing json", e);
        }

        getViewState().buildArticleView(mArticle.getDrawables());
    }
}
