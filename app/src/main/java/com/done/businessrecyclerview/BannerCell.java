package com.done.businessrecyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.BannerCell.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/17
 */

public class BannerCell extends BaseBizCell<BannerCell.ViewModel> {

    private BizBaseAdapter mAdapter;

    private RecyclerView mRecyclerView;

    public BannerCell(ViewModel data) {
        super(data);
    }

    @Override
    protected void binViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        if (mRecyclerView == null) {
            mRecyclerView = viewHolder.findViewById(R.id.rv_banner);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
        }
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        if (adapter == null || !(adapter instanceof BizBaseAdapter)) {
            mAdapter = new BizDefaultAdapter();
        } else {
            mAdapter = (BizBaseAdapter) adapter;
        }
        mAdapter.clear();
        for (int i = 0; i < mData.getBgColor().length; i++) {
            BannerContentCell contentCell = new BannerContentCell(mData.getBgColor()[i]);
            mAdapter.add(contentCell);
        }
        if (mAdapter != adapter) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public boolean isSupportDelete() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_main_banner;
    }

    public static class ViewModel {
        private int[] bgColor;

        public int[] getBgColor() {
            return bgColor;
        }

        public void setBgColor(int[] bgColor) {
            this.bgColor = bgColor;
        }
    }
}
