package com.done.businessrecyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.SenderCell.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/14
 */

public class SenderCell extends BaseBizCell<ComnuicationViewModel> implements View.OnClickListener {

    private static final String TAG = "SenderCell";

    private TextView mTvContent;

    public SenderCell(ComnuicationViewModel data) {
        super(data);
    }

    @Override
    protected void binViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        viewHolder.setText(R.id.tv_title, getClass().getSimpleName() + ",pos:" + position, "#0000FF");
        viewHolder.setText(R.id.tv_content, mData.getContent());
        mTvContent = viewHolder.findViewById(R.id.tv_content);
        mRootView.setOnClickListener(this);
    }

    @Override
    protected void onRelease(@NonNull RecyclerView.ViewHolder holder) {

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
        return R.layout.cell_sender;
    }

    @Override
    public void onClick(View v) {
        String sendMsg = "我发了一条数据给其他cell";
        postMessage(mPos, sendMsg);
        ToastUtils.showToast(v.getContext(), sendMsg);
    }

}
