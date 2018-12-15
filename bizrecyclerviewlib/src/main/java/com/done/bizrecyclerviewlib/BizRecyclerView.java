package com.done.bizrecyclerviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.features.BizDefaultAnimator;
import com.done.bizrecyclerviewlib.features.BizSideScrollTouchHelper;

/**
 * File: com.done.bizrecyclerviewlib.BizRecyclerView.java
 * Description: 便于修改，抽出来
 *
 * @author Done
 * @date 2018/12/12
 */

public class BizRecyclerView extends RecyclerView {

    private Context mContext;

    private boolean hasAnimate = true;

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
            hasAnimate = typedArray.getBoolean(R.styleable.BizRecyclerView_has_animate, true);
            typedArray.recycle();
        }
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        mAdapter = adapter;
        initAnimate();
    }

    public boolean isHasAnimate() {
        return hasAnimate;
    }

    public void setHasAnimate(boolean hasAnimate) {
        this.hasAnimate = hasAnimate;
        initAnimate();
    }

    /**
     * init default item animation
     */
    private void initAnimate() {
        if (mAdapter != null && BizBaseAdapter.class.isAssignableFrom(mAdapter.getClass())) {
            ((BizBaseAdapter) mAdapter).initItemAnimator(hasAnimate);
        }
    }


    public boolean hasAnimate() {
        return hasAnimate;
    }
}
