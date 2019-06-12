package com.done.bizrecyclerviewlib;

import android.support.annotation.NonNull;

import com.done.bizrecyclerviewlib.exception.BizBugException;

/**
 * File: com.done.bizrecyclerviewlib.Preconditions.java
 * Description: 异常专业户
 *
 * @author Done
 * date 2018/12/12
 */
@SuppressWarnings("WeakerAccess")
public final class Preconditions {

    @NonNull
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    @NonNull
    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    /**
     * 常规检测发生异常，扔出去，不属于框架错误
     *
     * @param message
     */
    @NonNull
    public static void throwIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

    /**
     * 自检异常，扔出去，方便调试
     *
     * @param message
     */
    @NonNull
    public static void throwBugException(String message) {
        throw new BizBugException(message);
    }


    private Preconditions() {
    }

}
