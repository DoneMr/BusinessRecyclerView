package com.done.bizrecyclerviewlib.eventbus;


import androidx.annotation.Nullable;

import com.done.bizrecyclerviewlib.Preconditions;

/**
 * File: com.done.bizrecyclerviewlib.eventbus.DefaultBaseEventHandler.java
 * Description: 消息中心
 *
 * @author Done
 * date 2018/12/14
 */

public class DefaultBaseEventHandler extends BaseEventHandler {

    /**
     * 这里注册进来的监听接口只adapter对应一份
     */
    private OnMessageListener mListener;

    public DefaultBaseEventHandler(OnMessageListener onMessageListener) {
        Preconditions.checkNotNull(onMessageListener, "onMessageListener is Null");
        mListener = onMessageListener;
    }

    @Override
    protected void handleData(int pos, @Nullable Object data) {
        if (mListener != null) {
            mListener.onReceive(pos, data);
        }
    }

    @Override
    protected void notifyItem(int index) {
        if (mListener != null) {
            mListener.notifyItemDataChanged(index);
        }
    }

    @Override
    protected void deleteItem(int index) {
        if (mListener != null) {
            mListener.deleteItem(index);
        }
    }

    public interface OnMessageListener {

        void onReceive(int pos, @Nullable Object data);

        void notifyItemDataChanged(int pos);

        void deleteItem(int pos);
    }
}
