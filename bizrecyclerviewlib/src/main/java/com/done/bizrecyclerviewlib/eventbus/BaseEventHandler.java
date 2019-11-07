package com.done.bizrecyclerviewlib.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.Nullable;

import com.done.bizrecyclerviewlib.util.BizLogger;

/**
 * File: com.done.bizrecyclerviewlib.eventbus.BaseEventHandler.java
 * Description: cell通信 工作在主线程，
 *
 * @author Done
 * date 2018/12/14
 */

public abstract class BaseEventHandler extends Handler {

    private static final String TAG = "BaseEventHandler";

    public static final int NOTIFY_ITEM = -1;

    public static final int DELETE_SELF = -2;

    public static final int BROADCAST_DATA = -100;

    public BaseEventHandler() {
        super(Looper.getMainLooper());
    }

    /**
     * 目前arg1 为对应item的pos
     *
     * @param msg
     */
    @Override
    public void handleMessage(Message msg) {
        BizLogger.d(TAG, "--->收到来自:" + msg.what + "," + msg.obj);
        if (msg.what == NOTIFY_ITEM) {
            notifyItem(msg.arg1);
        } else if (msg.what == DELETE_SELF) {
            deleteItem(msg.arg1);
        } else if (msg.what == BROADCAST_DATA) {
            handleData(msg.arg1, msg.obj);
        }
    }


    public final void onDestroy() {
        removeCallbacksAndMessages(null);
    }

    public final void removeMessage(int pos) {
        removeMessages(pos);
    }

    protected abstract void handleData(int pos, @Nullable Object data);

    protected abstract void notifyItem(int index);

    protected abstract void deleteItem(int index);

}
