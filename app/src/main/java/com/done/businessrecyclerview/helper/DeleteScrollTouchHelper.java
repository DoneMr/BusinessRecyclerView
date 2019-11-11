package com.done.businessrecyclerview.helper;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.features.BaseBizTouchHelper;

/**
 * File:BusinessRecyclerView.com.done.bizrecyclerviewlib.features.DeleteScrollTouchHelper
 * Description:侧滑辅助滑动对象，只兼顾LinearLayoutManager{@link RecyclerView.LayoutManager}
 *
 * @author maruilong
 * date 2018/12/14 星期五
 */
public class DeleteScrollTouchHelper extends BaseBizTouchHelper {

    public DeleteScrollTouchHelper(RecyclerView.Adapter adapter) {
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
                swipeFlag = ItemTouchHelper.LEFT;
            }
        }
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.scrollTo(0, 0);
    }

    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return super.canDropOver(recyclerView, current, target);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return dX < 100;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    }

    private float dX = 0;

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            this.dX = dX;
            viewHolder.itemView.scrollTo(-(int) dX, 0);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
