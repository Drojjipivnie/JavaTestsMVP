package com.drojj.javatests.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.drojj.javatests.database.base.BaseDAO;
import com.drojj.javatests.model.articles.ArticleCategoryItem;

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
}
