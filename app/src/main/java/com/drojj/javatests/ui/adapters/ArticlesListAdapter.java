package com.drojj.javatests.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.drojj.javatests.R;
import com.drojj.javatests.model.articles.ArticleListItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

    private final List<ArticleListItem> mItems;

    private final OnRecyclerItemClickListener<ArticleListItem> mClickListener;

    private final TextDrawable.IShapeBuilder mShapeBuilder;
    private final ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;

    public ArticlesListAdapter(List<ArticleListItem> items, OnRecyclerItemClickListener<ArticleListItem> clickListener) {
        this.mItems = items;
        this.mClickListener = clickListener;
        this.mShapeBuilder = TextDrawable.builder().beginConfig().bold().endConfig();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleListItem item = mItems.get(position);
        holder.itemView.setOnClickListener(v -> mClickListener.onClick(item));
        holder.articleListItemTitle.setText(item.getTitle());
        holder.firstLetterImageView.setImageDrawable(mShapeBuilder.buildRound(item.getTitle().substring(0, 1), mColorGenerator.getRandomColor()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_list_item_title)
        TextView articleListItemTitle;

        @BindView(R.id.article_list_item_imageview)
        ImageView firstLetterImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
