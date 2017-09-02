package com.drojj.javatests.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.drojj.javatests.BuildConfig;

public class AppDatabase extends SQLiteOpenHelper {

    static final int VERSION = BuildConfig.VERSION_CODE;

    static final String DATABASE_NAME = "Database.db";

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
