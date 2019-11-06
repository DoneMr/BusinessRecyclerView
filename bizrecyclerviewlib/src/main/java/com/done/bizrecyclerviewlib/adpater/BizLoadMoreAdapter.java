package com.done.bizrecyclerviewlib.adpater;

import androidx.annotation.NonNull;

import com.done.bizrecyclerviewlib.Preconditions;
import com.done.bizrecyclerviewlib.cell.BaseLoadMoreCell;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.model.BizLoadMoreInfo;

import java.lang.ref.WeakReference;

/**
 * File:com.done.bizrecyclerviewlib.adpater.BizLoadMoreAdapter
 * Description:支持垂直或者横向加载的适配器
 *
 * @author maruilong
 * @date 2019-11-06
 */
public class BizLoadMoreAdapter extends BizBaseAdapter {

    public WeakReference<BaseLoadMoreCell> loadMoreCellWeakReference;

    public <LOADMORE_DATA> BizLoadMoreAdapter(@NonNull BaseLoadMoreCell<LOADMORE_DATA> baseLoadMoreCell) {
        Preconditions.checkNotNull(baseLoadMoreCell);
        loadMoreCellWeakReference = new WeakReference<BaseLoadMoreCell>(baseLoadMoreCell);
    }

    public void setStatus(BizLoadMoreInfo.STATUS status) {
        BaseLoadMoreCell loadMoreCell = getLoadMoreCell();
        int itemCount = getItemCount();
        IBizCell cell = getCell(itemCount);
        if (loadMoreCell != null) {
            switch (status) {
                case NO_MORE:
                case LOADING:
                case ERROR:
                    if (cell instanceof BaseLoadMoreCell) {
                        ((BaseLoadMoreCell) cell).setStatus(status);
                    } else {
                        add(loadMoreCell);
                        loadMoreCell.setStatus(status);
                    }
                    break;
                case NORMAL:
                    if (cell instanceof BaseLoadMoreCell) {
                        remove(itemCount);
                    }
                    break;
                default:
                    break;
            }
            loadMoreCell.setStatus(status);
        }
    }

    private BaseLoadMoreCell getLoadMoreCell() {
        return loadMoreCellWeakReference == null ? null : loadMoreCellWeakReference.get();
    }
}
