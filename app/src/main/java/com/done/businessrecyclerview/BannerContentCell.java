package com.done.businessrecyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.BannerContentCell.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/17
 */

public class BannerContentCell extends BaseBizCell<Integer> {
    public BannerContentCell(Integer data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        viewHolder.setBackgroundColor(R.id.ll_banner_content, mData);
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_banner;
    }
}
