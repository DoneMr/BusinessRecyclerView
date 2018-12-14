package com.done.bizrecyclerviewlib.adpater;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.done.bizrecyclerviewlib.Preconditions;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.eventbus.BaseEventHandler;
import com.done.bizrecyclerviewlib.eventbus.DefaultBaseEventHandler;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.adpater.BizBaseAdapter.java
 * Description: adapter基类
 *
 * @author Done
 * @date 2018/12/13
 */

public abstract class BizBaseAdapter<Cell extends IBizCell> extends RecyclerView.Adapter<BizViewHolder> implements DefaultBaseEventHandler.OnMessageListener {

    private TypePool mTypePool;

    private BaseEventHandler mEventHandler;

    public BizBaseAdapter() {
        this.mTypePool = new BizTypePool();
        mEventHandler = new DefaultBaseEventHandler(this);
    }

    @Override
    public void onBindViewHolder(@NonNull BizViewHolder holder, int position, @NonNull List<Object> payloads) {
        mTypePool.getCell(position).onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return mTypePool.getViewType(position);
    }

    /**
     * //TODO
     *
     * @param hasStableIds
     */
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    /**
     * //TODO
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onViewRecycled(@NonNull BizViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition < 0 || adapterPosition >= mTypePool.getCount()) {
            return;
        }
        mTypePool.getCell(adapterPosition).onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BizViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition < 0 || adapterPosition >= mTypePool.getCount()) {
            return;
        }
        mTypePool.getCell(adapterPosition).onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BizViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition < 0 || adapterPosition >= mTypePool.getCount()) {
            return;
        }
        mTypePool.getCell(adapterPosition).onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public BizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int indexViewType) {
        List<IBizCell> allCells = mTypePool.getAllCells();
        for (IBizCell allCell : allCells) {
            int viewType = mTypePool.getViewType(allCell.getClass());
            if (viewType == indexViewType) {
                return BizViewHolder.createHolder(viewGroup.getContext(), viewGroup, allCell.getLayoutId());
            }
        }
        Preconditions.throwBugException("not saved this viewType, it is bug");
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BizViewHolder viewHolder, int position) {
        mTypePool.getCell(position).onBindViewHolder(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mTypePool.getCount();
    }

    @Override
    public void onReceive(int pos, @Nullable Object data) {
        List<IBizCell> allCells = mTypePool.getAllCells();
        for (int i = 0; i < allCells.size(); i++) {
            IBizCell cell = allCells.get(i);
            if (cell.getPos() != pos) {
                cell.handleMessage(pos, data);
            }
        }
    }

    @Override
    public void notifyItemDataChanged(int pos) {
        notifyDataChange(pos);
    }

    //-------------------------对外提供的可操作的方法-----------------------//

    public int getCellViewType(Cell cell) {
        return mTypePool.getViewType(cell.getClass());
    }

    public void add(Cell cell) {
        Preconditions.checkNotNull(cell);
        cell.setEventHandler(mEventHandler);
        int pos = mTypePool.addCell(cell);
        notifyItemRangeChanged(pos, 1);
    }

    public void add(int pos, Cell cell) {
        Preconditions.checkNotNull(cell);
        cell.setEventHandler(mEventHandler);
        mTypePool.addCell(pos, cell);
        notifyItemRangeChanged(pos, getItemCount() - pos);
    }

    public void addCells(List<Cell> cells) {
        for (Cell cell : cells) {
            Preconditions.checkNotNull(cell);
            cell.setEventHandler(mEventHandler);
        }
        int startPos = mTypePool.addCells((List<IBizCell>) cells);
        notifyItemRangeChanged(startPos, cells.size());
    }


    /**
     * 移除列表中第一个此类型的cell
     *
     * @param cell
     */
    public void remove(Cell cell) {
        int index = mTypePool.firstIndexOf(cell);
        remove(index);
    }

    public void remove(int start, int end) {
        for (int i = start; i < end + 1; i++) {
            remove(i);
        }
    }

    public void remove(int index) {
        mEventHandler.removeMessage(index);
        mTypePool.removeCell(index);
        notifyItemRangeRemoved(index, getItemCount() - index);
    }

    public void clear() {
        mTypePool.clear();
        notifyDataSetChanged();
    }

    public IBizCell getCell(int pos) {
        return mTypePool.getCell(pos);
    }

    public void notifyDataChange(int index) {
        notifyItemRangeChanged(index, 1);
    }

}
