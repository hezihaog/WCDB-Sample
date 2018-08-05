package com.github.wally.wcdbsample.util;

import android.content.Context;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;

/**
 * Package: com.github.wally.wcdb_sample.util
 * FileName: DBManager
 * Date: on 2018/8/4  上午11:50
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class DBManager {
    private SimpleDBHelper mDbHelper;
    private SQLiteDatabase mDb;

    private DBManager() {
        Context context = ContextProviderHelper.getInstance().getContext();
        mDbHelper = new SimpleDBHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    private static final class SingleHolder {
        private static final DBManager instance = new DBManager();
    }

    public static DBManager getInstance() {
        return SingleHolder.instance;
    }

    public void beginTransaction() {
        mDb.beginTransaction();
    }

    public void endTransaction() {
        mDb.endTransaction();
    }

    public void setTransactionSuccessful() {
        mDb.setTransactionSuccessful();
    }

    public void close() {
        mDb.close();
    }

    public void insert(Object[] args, String sql) {
        mDb.execSQL(sql, args);
    }

    public Cursor query(String sql, String[] args) {
        return mDb.rawQuery(sql, args);
    }

    public Cursor query(String sql) {
        return query(sql, null);
    }

    public boolean dropDataBase() {
        return mDbHelper.dropDataBase();
    }
}