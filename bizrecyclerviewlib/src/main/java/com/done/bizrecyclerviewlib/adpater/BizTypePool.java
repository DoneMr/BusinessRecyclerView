package com.done.bizrecyclerviewlib.adpater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.done.bizrecyclerviewlib.Preconditions;
import com.done.bizrecyclerviewlib.cell.IBizCell;

import java.util.ArrayList;
import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.adpater.BizTypePool.java
 * Description: 维护Cell和其对应的Type
 *
 * @author Done
 * date 2018/12/13
 */

public final class BizTypePool implements TypePool {

    private static final String TAG = "BizTypePool";

    private List<IBizCell> mCellList;

    private ViewTypeHelper mViewTypeHelper;

    public BizTypePool() {
        mCellList = new ArrayList<>();
        mViewTypeHelper = new ViewTypeHelper();
    }

    @Override
    public int getCount() {
        return mCellList.size();
    }

    @Override
    public int getViewType(int position) {
        if (position >= mCellList.size()) {
            Preconditions.throwIllegalArgumentException("check your argument, out of index");
        }
        Class<?> clazz = mCellList.get(position).getClass();
        Preconditions.checkNotNull(clazz, "The arguments is null");
        int index = mViewTypeHelper.getViewType(clazz);
        if (ViewTypeHelper.FAIL_VIEW_TYPE == index) {
            Preconditions.throwIllegalArgumentException("check your code, do not add this type before");
        }
        return index;
    }

    @Override
    public int getViewType(@NonNull Class<?> clazz) {
        Preconditions.checkNotNull(clazz, "The arguments is null");
        int index = mViewTypeHelper.getViewType(clazz);
        if (ViewTypeHelper.FAIL_VIEW_TYPE == index) {
            Preconditions.throwIllegalArgumentException("check your code, do not add this type before");
        }
        return index;
    }

    @Override
    public int firstIndexOf(IBizCell cell) {
        return mCellList.indexOf(cell);
    }

    @Override
    public int addCell(IBizCell cell) {
        mCellList.add(cell);
        mViewTypeHelper.computeType(cell.getClass());
        return mCellList.size() - 1;
    }

    @Override
    public void addCell(int index, IBizCell cell) {
        if (index >= mCellList.size()) {
            Preconditions.throwIllegalArgumentException("index is out of cell List Size");
        }
        mCellList.add(index, cell);
        mViewTypeHelper.computeType(cell.getClass());
    }

    @Override
    public int addCells(List<IBizCell> cells) {
        int startPos = mCellList.size();
        mCellList.addAll(Preconditions.checkNotNull(cells, "The arguments is null"));
        for (IBizCell cell : cells) {
            mViewTypeHelper.computeType(cell.getClass());
        }
        return startPos;
    }

    @Override
    public List<IBizCell> getAllCells() {
        return mCellList;
    }

    @Override
    public void addLastCell(IBizCell cell) {

    }

    @Override
    public void addFirstCell(IBizCell cell) {

    }

    @Override
    public IBizCell removeCell(int index) {
        if (index >= mCellList.size()) {
            Preconditions.throwIllegalArgumentException("index out of cell list");
        }
        return mCellList.remove(index);
    }

    @Override
    public void removeCell(IBizCell cell) {
        mCellList.remove(cell);
    }

    @Override
    public void clear() {
        mCellList.clear();
    }

    @NonNull
    @Override
    public Class<?> getClass(int index) {
        return mCellList.get(index).getClass();
    }

    @NonNull
    @Override
    public IBizCell getCell(int index) {
        return mCellList.get(index);
    }

    @Nullable
    @Override
    public IBizCell getCellForType(int viewType) {
        for (IBizCell iBizCell : mCellList) {
            //这里不需要计较是哪个位置中的cell，adapter只需要对应type的cell用以判断是否支持侧滑删除
            if (viewType == mViewTypeHelper.getViewType(iBizCell.getClass())) {
                return iBizCell;
            }
        }
        return null;
    }
}
