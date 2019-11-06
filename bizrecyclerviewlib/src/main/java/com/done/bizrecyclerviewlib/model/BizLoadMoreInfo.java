package com.done.bizrecyclerviewlib.model;

/**
 * File:com.done.bizrecyclerviewlib.model.BizLoadMoreInfo
 * Description:对于加载更多需要的基本数据
 *
 * @author maruilong
 * @date 2019-11-06
 */
public class BizLoadMoreInfo<DATA> {

    private DATA data;

    public enum STATUS {
        LOADING,
        NORMAL,
        ERROR,
        NO_MORE,
    }

}
