package com.done.businessrecyclerview;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * File: com.done.businessrecyclerview.MyApplication.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/14
 */

public class MyApplication extends Application {

    private RefWatcher refWatcher;

    private static Handler mHandler;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler(Looper.getMainLooper());
        refWatcher = setupLeakCanary();
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        return myApplication.refWatcher;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mHandler.removeCallbacksAndMessages(null);
    }
}
