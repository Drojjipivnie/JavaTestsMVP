package com.drojj.javatests.model.articles;

public class ArticleListItem {
    private final int mId;
    private final String mTitle;

    public ArticleListItem(int id, String title) {
        this.mId = id;
        this.mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
