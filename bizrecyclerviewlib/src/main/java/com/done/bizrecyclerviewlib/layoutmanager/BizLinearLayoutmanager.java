package com.done.bizrecyclerviewlib.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * File: com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/15
 */

public class BizLinearLayoutmanager extends LinearLayoutManager {
    public BizLinearLayoutmanager(Context context) {
        super(context);
    }

    public BizLinearLayoutmanager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public BizLinearLayoutmanager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }
}
