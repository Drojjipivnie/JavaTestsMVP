package com.drojj.javatests.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.drojj.javatests.R;
import com.drojj.javatests.model.articles.item.Drawable;
import com.drojj.javatests.presentation.presenter.ArticlePresenter;
import com.drojj.javatests.presentation.view.ArticleView;
import com.drojj.javatests.ui.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFragment extends BaseFragment implements ArticleView {
    public static final String TAG = "ArticleFragment";
    @InjectPresenter
    ArticlePresenter mArticlePresenter;

    @BindView(R.id.article_root_layout)
    LinearLayout mArticleRootLayout;


    @ProvidePresenter
    ArticlePresenter providePresenter() {
        int articleId = getArguments().getInt("article_id");
        return new ArticlePresenter(articleId);
    }

    public static ArticleFragment newInstance(Bundle args) {
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void buildArticleView(List<Drawable> drawables) {
        for (Drawable drawable : drawables) {
            mArticleRootLayout.addView(drawable.getView(getContext()));
        }
    }

    @Override
    protected String getToolbarTitle() {
        return getArguments().getString("article_title");
    }
}
