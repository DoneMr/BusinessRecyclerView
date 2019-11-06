package com.done.bizrecyclerviewlib.adpater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.BizRecyclerView;
import com.done.bizrecyclerviewlib.Preconditions;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.eventbus.BaseEventHandler;
import com.done.bizrecyclerviewlib.eventbus.DefaultBaseEventHandler;
import com.done.bizrecyclerviewlib.features.BaseBizSnapHelper;
import com.done.bizrecyclerviewlib.features.BizDefaultAnimator;
import com.done.bizrecyclerviewlib.features.BizPagerSnapHelper;
import com.done.bizrecyclerviewlib.features.BizSideScrollTouchHelper;
import com.done.bizrecyclerviewlib.holder.BizViewHolder;

import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.adpater.BizBaseAdapter.java
 * Description: adapter基类
 *
 * @author Done
 * date 2018/12/13
 */

public abstract class BizBaseAdapter extends RecyclerView.Adapter<BizViewHolder>
        implements DefaultBaseEventHandler.OnMessageListener,
        BizDefaultAnimator.AnimationListener, ILifecycleProxy {

    private TypePool mTypePool;

    private BaseEventHandler mEventHandler;

    private boolean isAnimate = false;

    private boolean pageScroll = false;

    private ItemTouchHelper mItemTouchHelper;

    private BizDefaultAnimator mItemAnimator;

    private BaseBizSnapHelper mSnapHelper;

    private NOTIFY_STATUS mCurStatus = NOTIFY_STATUS.DEFAULT;

    private RecyclerView mRecyclerView;

    public enum NOTIFY_STATUS {
        DEFAULT,
        ADD,
        MOVE,
        REMOVE,
        CHANGE
    }

    public BizBaseAdapter() {
        this.mTypePool = new BizTypePool();
        mEventHandler = new DefaultBaseEventHandler(this);
        mItemTouchHelper = new ItemTouchHelper(new BizSideScrollTouchHelper(this));
        mItemAnimator = new BizDefaultAnimator();
        mItemAnimator.setAnimationListener(this);
        mSnapHelper = new BizPagerSnapHelper();
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
        mRecyclerView = recyclerView;
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        if (recyclerView instanceof BizRecyclerView) {
            isAnimate = ((BizRecyclerView) recyclerView).hasAnimate();
            recyclerView.setItemAnimator(mItemAnimator);
            pageScroll = ((BizRecyclerView) recyclerView).isPageScroll();
            if (pageScroll) {
                mSnapHelper.attachToRecyclerView(recyclerView);
            }
        }
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
    public void deleteItem(int pos) {
        List<IBizCell> allCells = mTypePool.getAllCells();
        if (pos <= allCells.size()) {
            remove(pos);
        }
    }

    @Override
    public void notifyItemDataChanged(int pos) {
        notifyDataChange(pos);
    }

    /**
     * 外部无需调用此方法，此方法仅供{@link BizRecyclerView}调用
     *
     * @param isAnimate
     */
    public void initItemAnimator(boolean isAnimate) {
        this.isAnimate = isAnimate;
    }

    /**
     * 外部无需调用此方法，此方法仅供{@link BizRecyclerView}调用
     *
     * @param isPageScroll
     */
    public void initPageScroll(boolean isPageScroll) {
        this.pageScroll = isPageScroll;
        //这里无需关注RV本身是否是BizRv，RV本身支持
        if (isPageScroll) {
            mSnapHelper.attachToRecyclerView(mRecyclerView);
        } else {
            mSnapHelper.attachToRecyclerView(null);
        }
    }

    @Override
    public void onAddFinished(RecyclerView.ViewHolder item) {
        if (mCurStatus == NOTIFY_STATUS.ADD) {
            postNotify();
        }
    }

    @Override
    public void onMoveFinished(RecyclerView.ViewHolder item) {

    }

    @Override
    public void onRemoveFinished(RecyclerView.ViewHolder item) {
        if (mCurStatus == NOTIFY_STATUS.REMOVE) {
            postNotify();
        }
    }

    @Override
    public void onChangeFinished(RecyclerView.ViewHolder item, boolean oldItem) {

    }

    private void postNotify() {
        if (mEventHandler != null) {
            mEventHandler.post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onCreate() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onCreate();
            }
        }
    }

    @Override
    public void onStart() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onCreate();
            }
        }
    }

    @Override
    public void onResume() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onPause();
            }
        }
    }

    @Override
    public void onStop() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onStop();
            }
        }
    }

    @Override
    public void onDestroy() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onDestroy();
            }
        }
    }

    @Override
    public void onAny() {
        synchronized (BizBaseAdapter.class) {
            List<IBizCell> allCells = mTypePool.getAllCells();
            for (IBizCell allCell : allCells) {
                allCell.onAny();
            }
        }
    }

    //-------------------------对外提供的可操作的方法-----------------------//

    public <Cell extends IBizCell> int getCellViewType(Cell cell) {
        return mTypePool.getViewType(cell.getClass());
    }

    public <Cell extends IBizCell> void add(Cell cell) {
        setCellHandler(cell);
        int pos = mTypePool.addCell(cell);
        setAnimStatus(NOTIFY_STATUS.ADD);
        notifyItemRangeChanged(pos, 1);
    }

    private void setAnimStatus(NOTIFY_STATUS status) {
        mCurStatus = status;
    }

    public <Cell extends IBizCell> void add(int pos, Cell cell) {
        setCellHandler(cell);
        mTypePool.addCell(pos, cell);
        setAnimStatus(NOTIFY_STATUS.ADD);
        int itemCount = getItemCount() - pos < 1 ? 1 : getItemCount() - pos;
        if (isAnimate) {
            notifyItemRangeChanged(pos, 1);
        } else {
            postNotify();
        }
    }

    public <Cell extends IBizCell> void addCells(List<Cell> cells) {
        for (Cell cell : cells) {
            setCellHandler(cell);
        }
        int startPos = mTypePool.addCells((List<IBizCell>) cells);
        setAnimStatus(NOTIFY_STATUS.ADD);
        if (isAnimate) {
            notifyItemRangeChanged(startPos, cells.size());
        } else {
            postNotify();
        }
    }

    private <Cell extends IBizCell> void setCellHandler(Cell cell) {
        Preconditions.checkNotNull(cell);
        cell.setEventHandler(mEventHandler);
    }


    /**
     * 移除列表中第一个此类型的cell
     *
     * @param cell
     */
    public <Cell extends IBizCell> void remove(Cell cell) {
        int index = mTypePool.firstIndexOf(cell);
        remove(index);
    }

    public void remove(int start, int end) {
        setAnimStatus(NOTIFY_STATUS.REMOVE);
        for (int i = start; i < end + 1; i++) {
            mEventHandler.removeMessage(i);
            mTypePool.removeCell(i);
        }
        postNotify();
    }

    public void remove(int index) {
        mEventHandler.removeMessage(index);
        mTypePool.removeCell(index);
        if (isAnimate) {
            setAnimStatus(NOTIFY_STATUS.REMOVE);
            notifyItemRangeRemoved(index, 1);
        } else {
            postNotify();
        }
    }

    public void clear() {
        setAnimStatus(NOTIFY_STATUS.REMOVE);
        mTypePool.clear();
        postNotify();
    }

    public final boolean isSupportDelete(int viewType) {
        IBizCell cellForType = mTypePool.getCellForType(viewType);
        return cellForType != null && cellForType.isSupportDelete();
    }

    public final void broadcastAllCell(@Nullable Object data) {
        List<IBizCell> allCells = mTypePool.getAllCells();
        for (int i = 0; i < allCells.size(); i++) {
            IBizCell cell = allCells.get(i);
            cell.handleMessage(BaseEventHandler.BROADCAST_DATA, data);
        }
    }

    public IBizCell getCell(int pos) {
        return mTypePool.getCell(pos);
    }

    public void notifyDataChange(int index) {
        notifyItemRangeChanged(index, 1);
    }

}
