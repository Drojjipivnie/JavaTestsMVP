package com.drojj.javatests.ui.adapters;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drojj.javatests.R;
import com.drojj.javatests.app.App;
import com.drojj.javatests.model.articles.ArticleCategoryItem;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesCategoryAdapter extends RecyclerView.Adapter<ArticlesCategoryAdapter.ViewHolder> {

    private final Resources mResources;

    private final List<ArticleCategoryItem> mItems;

    private final OnRecyclerItemClickListener<ArticleCategoryItem> mClickListener;

    public ArticlesCategoryAdapter(List<ArticleCategoryItem> mItems, OnRecyclerItemClickListener<ArticleCategoryItem> clickListener) {
        this.mItems = mItems;
        mClickListener = clickListener;
        mResources = App.getAppComponent().getResources();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_category_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleCategoryItem item = mItems.get(position);
        holder.itemView.setOnClickListener(v -> mClickListener.onClick(mItems.get(position)));
        holder.articleCategoryItemName.setText(item.getName());
        holder.articleCategoryItemDescription.setText(item.getDescription());
        try {
            holder.articleCategoryItemImageview.setImageDrawable(Drawable.createFromStream(mResources.getAssets().open(item.getPicturePath()), null));
        } catch (IOException e) {
            throw new RuntimeException("Unknow image source " + item.getPicturePath());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article_category_item_imageview)
        ImageView articleCategoryItemImageview;
        @BindView(R.id.article_category_item_name)
        TextView articleCategoryItemName;
        @BindView(R.id.article_category_item_description)
        TextView articleCategoryItemDescription;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
