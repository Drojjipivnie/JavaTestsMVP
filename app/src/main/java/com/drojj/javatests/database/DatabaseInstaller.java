package com.drojj.javatests.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.drojj.javatests.app.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseInstaller {

    @Inject
    Context context;

    private static final String APP_PREFERENCES = "app_settings";

    private static final String APP_PREFERENCES_DB_SETTING = "database_version";

    private final static String mAssetsDatabasePath = "database/" + AppDatabase.DATABASE_NAME;

    private final String mLocalDatabasePath;

    private final String mDatabaseName = AppDatabase.DATABASE_NAME;

    public DatabaseInstaller() {
        App.getAppComponent().inject(this);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            mLocalDatabasePath = context.getApplicationInfo().dataDir + "/databases";
        } else {
            mLocalDatabasePath = context.getFilesDir().getPath() + context.getPackageName() + "/databases";
        }
    }

    public void install() throws IOException {
        if (!isDatabaseInstalled()) {
            Timber.d("Database not exists. Need to be installed");
            copyDatabase();
        } else {
            Timber.d("Database exists. Checking database update state. Database version must be version: %s", AppDatabase.VERSION);
            int databaseVersion = getDatabaseVersion();
            Timber.d("Current version is %s", databaseVersion);
            if (databaseVersion == AppDatabase.VERSION) {
                Timber.d("Database is at the last version. No need to update.");
            } else {
                Timber.d("Database version invalid. Installing new database.");
                copyDatabase();
            }
        }
    }

    private int getDatabaseVersion() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getInt(APP_PREFERENCES_DB_SETTING, 0);
    }

    private boolean isDatabaseInstalled() {
        File localDatabase = new File(mLocalDatabasePath + "/" + mDatabaseName);
        return localDatabase.exists();
    }

    private void copyDatabase() throws IOException {
        InputStream input = null;
        OutputStream output = null;

        try {
            input = context.getAssets().open(mAssetsDatabasePath);

            File databaseDirectory = new File(mLocalDatabasePath);
            if (!databaseDirectory.exists()) {
                databaseDirectory.mkdir();
            }

            output = new FileOutputStream(mLocalDatabasePath + "/" + mDatabaseName);

            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = input.read(mBuffer)) > 0) {
                output.write(mBuffer, 0, mLength);
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
            sharedPreferences.edit().putInt(APP_PREFERENCES_DB_SETTING, AppDatabase.VERSION).apply();
        } finally {

            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.flush();
                output.close();
            }
            Timber.d("Database installed successfully with version: %s", AppDatabase.VERSION);
        }
    }
}
