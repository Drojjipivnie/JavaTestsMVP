package com.drojj.javatests.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.drojj.javatests.database.base.BaseDAO;
import com.drojj.javatests.model.articles.ArticleCategoryItem;
import com.drojj.javatests.model.articles.ArticleListItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticlesDAO extends BaseDAO {

    public List<ArticleCategoryItem> getCategories() {
        List<ArticleCategoryItem> items = new ArrayList<>();

        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = null;

        try {
            cursor = writableDatabase.rawQuery("SELECT * FROM ARTICLE_CATEGORIES", null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToNext();
            } else {
                return Collections.emptyList();
            }

            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String image_path = cursor.getString(3);
                items.add(new ArticleCategoryItem(id, name, description, image_path));
            } while (cursor.moveToNext());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return items;
    }

    public List<ArticleListItem> getArticleListById(int categoryId) {
        List<ArticleListItem> items = new ArrayList<>();

        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = null;

        try {
            cursor = writableDatabase.rawQuery("SELECT _id, title FROM ARTICLES WHERE category_id = ?", new String[]{String.valueOf(categoryId)});
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToNext();
            } else {
                return Collections.emptyList();
            }

            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                items.add(new ArticleListItem(id, title));
            } while (cursor.moveToNext());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return items;
    }

    public String getArticleJSON(int articleId) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = null;
        String json = "";

        try {
            cursor = writableDatabase.rawQuery("SELECT body_json FROM ARTICLES WHERE _id = ?", new String[]{String.valueOf(articleId)});
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToNext();
            } else {
                return "";
            }

            json = cursor.getString(0);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return json;
    }
}
