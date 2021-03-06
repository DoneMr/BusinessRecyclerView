package com.done.bizrecyclerviewlib.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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

    protected BizViewHolder mHolder;

    private BaseEventHandler mBaseEventHandler;

    public BaseBizCell(T data) {
        this.mData = data;
    }

    @Override
    public void onBindViewHolder(@NonNull BizViewHolder holder, int position, @NonNull List<Object> payloads) {
        mPos = holder.getAdapterPosition();
        mHolder = holder;
        mRootView = holder.getItemView();
        bindViewHolder(holder, position);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        BizLogger.d(TAG, getClass().getName() + " onViewRecycled");
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
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
        bindViewHolder(viewHolder, position);
    }

    @Override
    public int getPos() {
        return mPos;
    }

    @Override
    public void postMessage(@Nullable Object data) {
        postMessageDelay(data, 0);
    }

    @Override
    public void postMessageDelay(@Nullable Object data, long delayMillisecond) {
        mBaseEventHandler.sendMessageDelayed(mBaseEventHandler.obtainMessage(BaseEventHandler.BROADCAST_DATA, mPos, 0, data), delayMillisecond);
    }

    @Override
    public void deleteSelf() {
        mBaseEventHandler.sendMessage(mBaseEventHandler.obtainMessage(BaseEventHandler.DELETE_SELF, mPos, 0, null));
    }

    @Override
    public void setEventHandler(BaseEventHandler baseEventHandler) {
        mBaseEventHandler = baseEventHandler;
    }

    @Override
    public boolean isReceiveMessage() {
        return false;
    }

    @Override
    public void handleMessage(int sourcePos, @Nullable Object data) {
    }

    @Override
    public final void notifySelf(int pos) {
        mBaseEventHandler.obtainMessage(BaseEventHandler.NOTIFY_ITEM, pos, 0).sendToTarget();
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

    protected abstract void bindViewHolder(@NonNull BizViewHolder viewHolder, int position);

    protected abstract void onRelease(@NonNull RecyclerView.ViewHolder holder);

}
