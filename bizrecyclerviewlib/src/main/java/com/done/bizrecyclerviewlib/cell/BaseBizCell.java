package com.done.bizrecyclerviewlib.cell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.done.bizrecyclerviewlib.eventbus.BaseEventHandler;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.bizrecyclerviewlib.util.BizLogger;

import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.cell.BaseBizCell.java
 * Description: cell基类，调用者需要实现自己的实例cell并继承改类
 *
 * @author Done
 * date 2018/12/13
 */

public abstract class BaseBizCell<T> implements IBizCell<T> {

    private static final String TAG = "BaseBizCell";

    protected T mData;

    protected int mPos;

    protected View mRootView;

    private BaseEventHandler mBaseEventHandler;

    public BaseBizCell(T data) {
        this.mData = data;
    }

    @Override
    public void onBindViewHolder(@NonNull BizViewHolder holder, int position, @NonNull List<Object> payloads) {
        mRootView = holder.getItemView();
        binViewHolder(holder, position);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        BizLogger.d(TAG, getClass().getName() + " onViewRecycled");
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        mPos = holder.getAdapterPosition();
        BizLogger.d(TAG, getClass().getName() + " onViewAttachedToWindow:" + mPos);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        onRelease(holder);
        BizLogger.d(TAG, getClass().getName() + " onViewDetachedFromWindow");
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        BizLogger.d(TAG, getClass().getName() + " onAttachedToRecyclerView");
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        BizLogger.d(TAG, getClass().getName() + " onDetachedFromRecyclerView");
    }

    @Override
    public void onBindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        binViewHolder(viewHolder, position);
    }

    @Override
    public int getPos() {
        return mPos;
    }

    @Override
    public void postMessage(int pos, @Nullable Object data) {
        postMessageDelay(pos, data, 0);
    }

    @Override
    public void postMessageDelay(int pos, @Nullable Object data, long delayMillisecond) {
        mBaseEventHandler.sendMessageDelayed(mBaseEventHandler.obtainMessage(pos, data), delayMillisecond);
    }

    @Override
    public void setEventHandler(BaseEventHandler baseEventHandler) {
        mBaseEventHandler = baseEventHandler;
    }

    @Override
    public void handleMessage(int sourcePos, @Nullable Object data) {
    }

    @Override
    public final void notifySelf(int pos) {
        mBaseEventHandler.obtainMessage(pos, BaseEventHandler.NOTIFY_ITEM, 0).sendToTarget();
    }

    @Override
    public boolean isSupportDelete() {
        return false;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAny() {

    }

    @Override
    public T getModel() {
        return mData;
    }

    public final void refreshSelf() {
        notifySelf(mPos);
    }

    protected abstract void binViewHolder(@NonNull BizViewHolder viewHolder, int position);

    protected abstract void onRelease(@NonNull RecyclerView.ViewHolder holder);

}
