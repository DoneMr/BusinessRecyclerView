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

    private SimpleItemAnimator mSimpleItemAnimator;

    private ItemTouchHelper mTouchHelper;

    private static final long ADD_ANIM_DURATION = 200L;
    private static final long REMOVE_ANIM_DURATION = 200L;
    private static final long MOVE_ANIM_DURATION = 200L;
    private static final long CHANGE_ANIM_DURATION = 200L;

    private boolean hasAnimate = true;

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
        initAnimate();
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            initTouchHelper(adapter);
        }
    }

    private void initTouchHelper(Adapter adapter) {
        mTouchHelper = new ItemTouchHelper(new BizSideScrollTouchHelper(adapter));
        mTouchHelper.attachToRecyclerView(this);
    }

    /**
     * init default item animation
     */
    private void initAnimate() {
        if (hasAnimate) {
            mSimpleItemAnimator = new DefaultItemAnimator();
            mSimpleItemAnimator.setAddDuration(ADD_ANIM_DURATION);
            mSimpleItemAnimator.setChangeDuration(CHANGE_ANIM_DURATION);
            mSimpleItemAnimator.setMoveDuration(MOVE_ANIM_DURATION);
            mSimpleItemAnimator.setRemoveDuration(REMOVE_ANIM_DURATION);
            mSimpleItemAnimator.setSupportsChangeAnimations(true);
            setItemAnimator(mSimpleItemAnimator);
        }
    }


    public boolean hasAnimate() {
        return hasAnimate;
    }
}
