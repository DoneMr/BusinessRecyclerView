package com.done.bizrecyclerviewlib.features;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * File:BusinessRecyclerView.com.done.bizrecyclerviewlib.features.BaseBizTouchHelper
 * Description:xxx
 *
 * @author maruilong
 * date 2018/12/15 星期六
 */
public abstract class BaseBizTouchHelper extends ItemTouchHelper.Callback {

    protected final RecyclerView.Adapter mAdapter;

    public BaseBizTouchHelper(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }
}
