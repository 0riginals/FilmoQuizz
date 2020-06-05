package com.test.filmoquizz.dao;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.test.filmoquizz.model.User;

import java.sql.SQLException;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "filmoquizz.db";
    private static final int DATABASE_VERSION = 1;

    // Dao pour notre utilisateur
    private Dao<User, Integer> userDao = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // On crée nos tables ici si elles ne sont pas déjà créé.
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch(SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop tables", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public RuntimeExceptionDao<User, Integer> getUserRuntimeExceptionDao() {
        if(userRuntimeDao == null) {
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }
        return userRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
        userRuntimeDao = null;
    }

}
