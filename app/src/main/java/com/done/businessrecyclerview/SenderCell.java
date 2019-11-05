package com.done.businessrecyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.cell.BaseBizCell;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

/**
 * File: com.done.businessrecyclerview.SenderCell.java
 * Description: xxx
 *
 * @author Done
 * date 2018/12/14
 */

public class SenderCell extends BaseBizCell<ComnuicationViewModel> implements View.OnClickListener {

    private static final String TAG = "SenderCell";

    private TextView mTvContent;

    public SenderCell(ComnuicationViewModel data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        viewHolder.setText(R.id.tv_title, getClass().getSimpleName() + ",pos:" + position, "#0000FF");
        viewHolder.setText(R.id.tv_content, mData.getContent());
        mTvContent = viewHolder.findViewById(R.id.tv_content);
        mRootView.setOnClickListener(this);
        mRootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String sendMsg = "我延时发了一条数据给其他cell";
                postMessageDelay(mPos, sendMsg, 5 * 1000);
                return true;
            }
        });
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
        refreshSelf();
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
