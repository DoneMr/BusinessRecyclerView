package com.done.bizrecyclerviewlib.features;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.done.bizrecyclerviewlib.Preconditions;

/**
 * File: com.done.bizrecyclerviewlib.features.BaseBizSnapHelper.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/17
 */

public abstract class BaseBizSnapHelper extends SnapHelper {

    protected RecyclerView mRecyclerView;

    private RecyclerView.OnScrollListener mScrollListener;

    public BaseBizSnapHelper() {
        this.mScrollListener = new BizScrollListener();
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        return new int[0];
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        return 0;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        if (recyclerView != null) {
            mRecyclerView = recyclerView;
            mRecyclerView.addOnScrollListener(mScrollListener);
        }
    }

    private class BizScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            BaseBizSnapHelper.this.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            BaseBizSnapHelper.this.onScrolled(recyclerView, dx, dy);
        }
    }

    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

    }

    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

    }
}
