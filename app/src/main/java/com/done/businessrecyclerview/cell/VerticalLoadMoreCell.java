package com.done.businessrecyclerview.cell;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.model.LoadMoreCellInfo;

/**
 * File:com.done.businessrecyclerview.cell.VerticalLoadMoreCell
 * Description:垂直滑动列表的上拉加载cell
 *
 * @author maruilong
 * @date 2019-11-07
 */
public class VerticalLoadMoreCell extends BaseBizCell<LoadMoreCellInfo> {

    private TextView mText;

    public VerticalLoadMoreCell(LoadMoreCellInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        mText = viewHolder.findViewById(R.id.tv_load_more_text);
        viewHolder.setText(R.id.tv_load_more_text, mData.loadMoreText);
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_vertical_load_more;
    }
}
