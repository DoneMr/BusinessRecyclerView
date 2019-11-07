package com.done.businessrecyclerview.cell;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.model.CellTextInfo;
import com.done.businessrecyclerview.util.SampleUtils;

/**
 * File:com.done.businessrecyclerview.cell.CardTextCell
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-05
 */
public class CardTextCell extends BaseBizCell<CellTextInfo> implements View.OnClickListener {

    private TextView mTvTitle, mTvContent, mTvReceive;

    public CardTextCell(CellTextInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        String className = CardTextCell.class.getSimpleName();
        mTvContent = viewHolder.findViewById(R.id.tv_cell_card_text);
        mTvReceive = viewHolder.findViewById(R.id.tv_receive_text);
        viewHolder.setText(R.id.tv_cell_card_title, "className:" + className + ",pos:" + mPos);
        viewHolder.setText(R.id.tv_cell_card_text, mData.getText(), mData.getTextColor(), mData.getTextSize());
        viewHolder.getItemView().setOnClickListener(this);
        viewHolder.setVisible(R.id.ll_receive_message_area, mData.isShowReceive);
        if (mData.isShowReceive) {
            if (!TextUtils.isEmpty(mData.receiveText)) {
                viewHolder.setText(R.id.tv_receive_text, mData.receiveText, "#FF0000");
            } else {
                viewHolder.setText(R.id.tv_receive_text, "未接收到任何消息", "#AEAEAE");
            }
        }
    }

    @Override
    public boolean isSupportLeftDrag() {
        return true;
    }

    @Override
    public boolean isSupportRightDrag() {
        return false;
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public boolean isReceiveMessage() {
        return mData.isShowReceive;
    }

    @Override
    public void handleMessage(int sourcePos, @Nullable Object data) {
        StringBuilder stringBuilder = new StringBuilder(mData.receiveText);
        stringBuilder.append("\n")
                .append(SampleUtils.getStringMessage(sourcePos, data));
        mData.receiveText = stringBuilder.toString();
        refreshSelf();
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
