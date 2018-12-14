package com.done.businessrecyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.SenderCell.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/14
 */

public class DeleteCell extends BaseBizCell<ComnuicationViewModel> {

    private static final String TAG = "ReceiverCell";

    private TextView mTvContent;


    public DeleteCell(ComnuicationViewModel data) {
        super(data);
    }

    @Override
    protected void binViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        viewHolder.setText(R.id.tv_title, getClass().getSimpleName() + ",pos:" + position, "#AEAE00");
        viewHolder.setText(R.id.tv_content, mData.getContent());
        mTvContent = viewHolder.findViewById(R.id.tv_content);
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

    }

    @Override
    public boolean isSupportDelete() {
        return true;
    }

    @Override
    public void handleMessage(int sourcePos, @Nullable Object data) {
        StringBuilder stringBuilder = new StringBuilder(mData.getContent());
        String message = "\nreceiveMessage(" + sourcePos + "):" + (data == null ? "null" : data.toString());
        stringBuilder.append(message);
        mData.setContent(stringBuilder.toString());
        notifySelf(mPos);
    }

    @Override
    public int getLayoutId() {
        return R.layout.cell_delete;
    }

}
