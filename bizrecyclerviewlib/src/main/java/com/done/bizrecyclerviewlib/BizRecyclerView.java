package com.done.bizrecyclerviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;

/**
 * File: com.done.bizrecyclerviewlib.BizRecyclerView.java
 * Description: 便于修改，抽出来
 *
 * @author Done
 * date 2018/12/12
 */

public class BizRecyclerView extends RecyclerView {

    private Context mContext;

    /**
     * 是否提供item动画，默认为true
     */
    private boolean mHasAnimate = true;

    /**
     * 是否启动pageScroll方式
     */
    private boolean mPageScroll = false;

    private RecyclerView.Adapter mAdapter;

    public BizRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public BizRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BizRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BizRecyclerView);
            mHasAnimate = typedArray.getBoolean(R.styleable.BizRecyclerView_biz_has_animate, true);
            mPageScroll = typedArray.getBoolean(R.styleable.BizRecyclerView_biz_page_scroll, false);
            typedArray.recycle();
        }
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        mAdapter = adapter;
        initAnimate();
        initPageScroll();
    }

    public boolean ismHasAnimate() {
        return mHasAnimate;
    }

    public void setHasAnimate(boolean mHasAnimate) {
        this.mHasAnimate = mHasAnimate;
        initAnimate();
    }

    public boolean isPageScroll() {
        return mPageScroll;
    }

    public void setPageScroll(boolean pageScroll) {
        this.mPageScroll = pageScroll;
        initPageScroll();
    }

    /**
     * init default item animation
     */
    private void initAnimate() {
        if (mAdapter != null && BizBaseAdapter.class.isAssignableFrom(mAdapter.getClass())) {
            ((BizBaseAdapter) mAdapter).initItemAnimator(mHasAnimate);
        }
    }

    /**
     * 外部无需调用此方法，此方法仅供{@link BizRecyclerView}调用
     */
    public void initPageScroll() {
        if (mAdapter != null && BizBaseAdapter.class.isAssignableFrom(mAdapter.getClass())) {
            ((BizBaseAdapter) mAdapter).initPageScroll(mPageScroll);
        }
    }


    public boolean hasAnimate() {
        return mHasAnimate;
    }
}
