package com.done.bizrecyclerviewlib.cell;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.eventbus.BaseEventHandler;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.cell.IBizCell.java
 * Description: Cell接口
 *
 * @author Done
 * date 2018/12/13
 */

public interface IBizCell<T> {

    void onBindViewHolder(@NonNull BizViewHolder holder, int position, @NonNull List<Object> payloads);

    void onViewRecycled(@NonNull RecyclerView.ViewHolder holder);

    void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder);

    void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder);

    void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView);

    void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView);

    void onBindViewHolder(@NonNull BizViewHolder viewHolder, int position);

    int getLayoutId();

    int getPos();

    /**
     * 上层传递过来的消息发送者，此接口基类实现，与子类之间无关
     *
     * @param baseEventHandler
     */
    void setEventHandler(BaseEventHandler baseEventHandler);

    /**
     * cell 发送消息
     *
     * @param data 数据
     */
    void postMessage(@Nullable Object data);

    void postMessageDelay(@Nullable Object data, long delayMillisecond);

    /**
     * 处理来自其他cell的消息
     * 这里回调的时候，cell是没有实质上的view存在的，唯一存在的是cell对象本身和mData。
     * 所以请不要再这里面去更新View，您可以选择更新你的mData，然后在bindView的时候去展示数据
     *
     * @param sourcePos 来源cell的pos 当pos=-100时，消息来自外部，所有cell都会受到此消息{@link BaseEventHandler#BROADCAST_DATA}
     * @param data
     */
    void handleMessage(int sourcePos, @Nullable Object data);

    /**
     * cell可以选择是否接收来自别的cell的消息，这里返回true，才能收到{@link #handleMessage(int, Object)}的回调
     * 默认返回false
     *
     * @return
     */
    boolean isReceiveMessage();

    /**
     * 通知刷新自身
     *
     * @param pos 位置
     */
    void notifySelf(int pos);

    /**
     * 干掉自己
     */
    void deleteSelf();

    /**
     * 获取cell中的数据源
     *
     * @return
     */
    T getModel();

    /**
     * 此类接口的回调需要在对应的Activity或者Fragment对adapter进行注册
     *
     * @see com.done.bizrecyclerviewlib.adpater.BizBaseAdapter
     * 以下
     * 同步activity或者Fragment的生命周期
     */
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onAny();
}
