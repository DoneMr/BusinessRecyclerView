package com.done.businessrecyclerview.cell;

import android.view.View;

import androidx.annotation.NonNull;

import com.done.bizrecyclerviewlib.holder.BizViewHolder;
import com.done.businessrecyclerview.model.CellTextInfo;
import com.done.businessrecyclerview.util.SampleUtils;

/**
 * File:com.done.businessrecyclerview.cell.SendMessageCell
 * Description:发送消息的cell
 *
 * @author maruilong
 * @date 2019-11-07
 */
public class SendMessageCell extends CardTextCell implements View.OnLongClickListener {

    public SendMessageCell(CellTextInfo data) {
        super(data);
    }

    @Override
    protected void bindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        super.bindViewHolder(viewHolder, position);
        viewHolder.getItemView().setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        postMessage("hi~ coder(" + SampleUtils.getCurTime() + ")");
    }

    @Override
    public boolean onLongClick(View v) {
        postMessageDelay("hi~ coder(" + SampleUtils.getCurTime() + ")", 1000L);
        return true;
    }
}
