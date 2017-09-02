package com.drojj.javatests.model.articles;

public class ArticleCategoryItem {

    private int mID;

    private String mName;

    private String mDescription;

    private String mPicturePath;

    public ArticleCategoryItem(int id, String name, String description, String picturePath) {
        this.mID = id;
        this.mName = name;
        this.mDescription = description;
        this.mPicturePath = picturePath;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPicturePath() {
        return mPicturePath;
    }

    public int getID() {
        return mID;
    }
}
