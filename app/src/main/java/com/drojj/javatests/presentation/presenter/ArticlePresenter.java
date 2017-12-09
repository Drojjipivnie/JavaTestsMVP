package com.drojj.javatests.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.drojj.javatests.database.ArticlesDAO;
import com.drojj.javatests.model.articles.item.Article;
import com.drojj.javatests.presentation.view.ArticleView;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        getArticle().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(article -> {
            mArticle = article;
            getViewState().buildArticleView(article.getDrawables());
        });
    }

    private Observable<Article> getArticle() {
        return Observable.defer(() -> {
            Article article = new GsonBuilder().create().fromJson(new ArticlesDAO().getArticleJSON(mArticleId), Article.class);
            return Observable.just(article);
        });
    }
}
