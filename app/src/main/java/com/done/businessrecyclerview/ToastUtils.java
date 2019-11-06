package com.done.businessrecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * File: com.done.businessrecyclerview.ToastUtils.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/14
 */

public class ToastUtils {

    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(message);
        mToast.show();
    }
}
