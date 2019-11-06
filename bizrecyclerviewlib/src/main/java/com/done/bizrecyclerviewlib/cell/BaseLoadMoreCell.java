package com.done.bizrecyclerviewlib.cell;

import com.done.bizrecyclerviewlib.model.BizLoadMoreInfo;

/**
 * File:com.done.bizrecyclerviewlib.cell.BaseLoadMoreCell
 * Description:加载更多的cell
 *
 * @author maruilong
 * @date 2019-11-06
 */
public abstract class BaseLoadMoreCell<LOADMORE_DATA> extends BaseBizCell<BizLoadMoreInfo<LOADMORE_DATA>> {

    public BaseLoadMoreCell(BizLoadMoreInfo<LOADMORE_DATA> data) {
        super(data);
    }

    public void setStatus(BizLoadMoreInfo.STATUS status) {
        setCellStatus(status);
    }

    private void setCellStatus(BizLoadMoreInfo.STATUS status) {
        switch (status) {
            case NORMAL:
                dealNormalStatus();
                break;
            case ERROR:
                dealErrorStatus();
                break;
            case LOADING:
                dealLoadingStatus();
                break;
            case NO_MORE:
                dealNoMoreStatus();
                break;
            default:
                break;
        }
    }

    protected abstract void dealNoMoreStatus();

    protected abstract void dealLoadingStatus();

    protected abstract void dealErrorStatus();

    protected void dealNormalStatus() {
        deleteSelf();
    }
}
