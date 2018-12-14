package com.done.bizrecyclerviewlib.util;

import android.text.TextUtils;
import android.util.Log;

import com.done.bizrecyclerviewlib.constant.BizConstants;

/**
 * File: com.done.bizrecyclerviewlib.util.BizLogger.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/13
 */

public class BizLogger {

    public static void d(String tag, String message) {
        if (BizConstants.isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.d(tag, createThreadMessage() + message);
        }
    }

    public static void i(String tag, String message) {
        if (BizConstants.isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.i(tag, createThreadMessage() + message);
        }
    }

    public static void w(String tag, String message) {
        if (BizConstants.isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.w(tag, createThreadMessage() + message);
        }
    }

    public static void e(String tag, String message) {
        if (BizConstants.isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.e(tag, createThreadMessage() + message);
        }
    }

    private static String createThreadMessage() {
        return "[Thread-Name:" + Thread.currentThread().getName() + ",Thread-Id:" + Thread.currentThread().getId() + "]\n";
    }
}
