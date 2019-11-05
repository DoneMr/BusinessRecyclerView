package com.done.businessrecyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.NormalCell1.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/13
 */

public class NormalCell1 extends BaseBizCell<NormalCellViewType> {

    public NormalCell1(NormalCellViewType data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        String htmlString = "<font>" + this.getClass().getSimpleName() + ",</font>" + "<font color=\"#FF0000\">" + "pos：" + position + "</font>";
        viewHolder.setHtmlText(R.id.tv_title, htmlString);
        if (mData != null) {
            viewHolder.appendText(R.id.tv_title, "\nviewType:" + mData.getViewType());
        }
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_normal_1;
    }

}
