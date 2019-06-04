package com.done.businessrecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * File: com.done.businessrecyclerview.ToastUtils.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/14
 */

public class ToastUtils {

    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(message);
        }
        mToast.show();
    }
}
