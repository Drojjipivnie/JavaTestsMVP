package com.drojj.javatests.database.base;

import com.drojj.javatests.app.App;
import com.drojj.javatests.database.AppDatabase;

import javax.inject.Inject;

public abstract class BaseDAO {
    @Inject
    protected AppDatabase database;

    public BaseDAO() {
        App.getAppComponent().inject(this);
    }
}
