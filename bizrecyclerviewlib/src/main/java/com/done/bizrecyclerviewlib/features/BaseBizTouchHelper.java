package com.done.bizrecyclerviewlib.features;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * File:BusinessRecyclerView.com.done.bizrecyclerviewlib.features.BaseBizTouchHelper
 * Description:xxx
 *
 * @author maruilong
 * @date 2018/12/15 星期六
 */
public abstract class BaseBizTouchHelper extends ItemTouchHelper.Callback{

    protected final RecyclerView.Adapter mAdapter;

    public BaseBizTouchHelper(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }
}
