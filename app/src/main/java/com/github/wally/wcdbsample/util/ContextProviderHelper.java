package com.github.wally.wcdbsample.util;

import android.content.Context;

/**
 * Package: com.github.wally.wcdb_sample.util
 * FileName: ContextProviderHelper
 * Date: on 2018/8/4  上午11:55
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class ContextProviderHelper {
    private static Context mContext;

    private static final ContextProviderHelper INSTANCE = new ContextProviderHelper();

    private ContextProviderHelper() {
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ContextProviderHelper getInstance() {
        return INSTANCE;
    }

    public Context getContext() {
        return mContext;
    }
}