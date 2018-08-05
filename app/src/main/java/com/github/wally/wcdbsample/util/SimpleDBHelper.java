package com.github.wally.wcdbsample.util;

import android.content.Context;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Package: com.github.wally.wcdb_sample.util
 * FileName: SimpleDBHelper
 * Date: on 2018/8/4  上午11:39
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class SimpleDBHelper extends SQLiteOpenHelper {
    private Context mContext;
    // 数据库 db 文件名称
    private static final String DEFAULT_NAME = "sample.db";
    // 默认版本号
    private static final int DEFAULT_VERSION = 1;

    public SimpleDBHelper(Context context) {
        super(context, DEFAULT_NAME, "wally".getBytes(Charset.forName("utf-8")), null, DEFAULT_VERSION, null);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建一张表
        String sql = "CREATE TABLE IF NOT EXISTS person (\n" +
                "\t`id` char(32) NOT NULL,\n" +
                "\t`person_name` varchar(100) DEFAULT NULL,\n" +
                "\t`sex` varchar(20) DEFAULT NULL,\n" +
                "\t`age` int(11) DEFAULT NULL,\n" +
                "  `create_time` datetime DEFAULT NULL,\n" +
                "  `update_time` datetime DEFAULT NULL,\n" +
                "  `version` int(11) DEFAULT NULL,\n" +
                "  `delete_flag` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropDataBase();
    }

    public boolean dropDataBase() {
        File file = mContext.getDatabasePath(DEFAULT_NAME);
        return SQLiteDatabase.deleteDatabase(file);
    }
}