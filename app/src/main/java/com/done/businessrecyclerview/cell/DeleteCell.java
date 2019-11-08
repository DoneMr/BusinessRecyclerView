package com.done.businessrecyclerview.cell;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.model.CellTextInfo;

/**
 * File:com.done.businessrecyclerview.cell.DeleteCell
 * Description:侧滑删除
 *
 * @author maruilong
 * @date 2019-11-08
 */
public class DeleteCell extends BaseBizCell<CellTextInfo> {

    public DeleteCell(CellTextInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_delete;
    }
}
