package com.done.bizrecyclerviewlib.features;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;

/**
 * File:BusinessRecyclerView.com.done.bizrecyclerviewlib.features.BizSideScrollTouchHelper
 * Description:侧滑辅助滑动对象，只兼顾LinearLayoutManager{@link RecyclerView.LayoutManager}
 *
 * @author maruilong
 * @date 2018/12/14 星期五
 */
public class BizSideScrollTouchHelper extends BaseBizTouchHelper {

    public BizSideScrollTouchHelper(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = 0;
        int swipeFlag = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //GridLayoutManage继承LinearLayoutManager，小坑趟过
        if (layoutManager instanceof LinearLayoutManager && !(layoutManager instanceof GridLayoutManager)) {
            boolean isVertical = (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL);
            if (BizBaseAdapter.class.isAssignableFrom(mAdapter.getClass()) && isVertical) {
                boolean isSupportDelete = ((BizBaseAdapter) mAdapter).isSupportDelete(viewHolder.getItemViewType());
                if (isSupportDelete) {
                    swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
            }
        }
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (BizBaseAdapter.class.isAssignableFrom(mAdapter.getClass())
                && (i == ItemTouchHelper.LEFT || i == ItemTouchHelper.RIGHT)) {
            int adapterPosition = viewHolder.getAdapterPosition();
            ((BizBaseAdapter) mAdapter).remove(adapterPosition);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    public interface OnSwipeListener {

        void onSwiping(RecyclerView.ViewHolder viewHolder, int direction);
    }
}
