package com.done.bizrecyclerviewlib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;

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

    private static final long ADD_ANIM_DURAION = 200L;
    private static final long REMOVE_ANIM_DURAION = 200L;
    private static final long MOVE_ANIM_DURAION = 200L;
    private static final long CHANGE_ANIM_DURAION = 200L;

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
        mSimpleItemAnimator = new DefaultItemAnimator();
        mSimpleItemAnimator.setAddDuration(ADD_ANIM_DURAION);
        mSimpleItemAnimator.setChangeDuration(CHANGE_ANIM_DURAION);
        mSimpleItemAnimator.setMoveDuration(MOVE_ANIM_DURAION);
        mSimpleItemAnimator.setRemoveDuration(REMOVE_ANIM_DURAION);
        mSimpleItemAnimator.setSupportsChangeAnimations(false);
        setItemAnimator(mSimpleItemAnimator);
    }
}
