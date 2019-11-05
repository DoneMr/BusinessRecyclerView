package com.done.businessrecyclerview.cell;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.model.CellTextInfo;

/**
 * File:com.done.businessrecyclerview.cell.CardTextCell
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-05
 */
public class CardTextCell extends BaseBizCell<CellTextInfo> {

    private TextView mTvTitle, mTvContent;

    public CardTextCell(CellTextInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        String className = CardTextCell.class.getSimpleName();
        viewHolder.setText(R.id.tv_cell_card_title, "className:" + className + ",pos:" + mPos);
        viewHolder.setText(R.id.tv_cell_card_text, mData.getText(), mData.getTextColor());
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_card_text;
    }
}
