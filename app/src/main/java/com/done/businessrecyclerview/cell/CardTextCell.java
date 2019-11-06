package com.done.businessrecyclerview.cell;

import android.content.Intent;
import android.view.View;
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
public class CardTextCell extends BaseBizCell<CellTextInfo> implements View.OnClickListener {

    private TextView mTvTitle, mTvContent;

    public CardTextCell(CellTextInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        String className = CardTextCell.class.getSimpleName();
        viewHolder.setText(R.id.tv_cell_card_title, "className:" + className + ",pos:" + mPos);
        viewHolder.setText(R.id.tv_cell_card_text, mData.getText(), mData.getTextColor(), mData.getTextSize());
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_card_text;
    }

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();
        Intent jumpIntent = mData.getJumpIntent();
        if (jumpIntent != null) {
            v.getContext().startActivity(jumpIntent);
        }
    }
}
