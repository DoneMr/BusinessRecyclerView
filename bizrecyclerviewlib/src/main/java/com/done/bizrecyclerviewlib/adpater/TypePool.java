package com.done.bizrecyclerviewlib.adpater;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.done.bizrecyclerviewlib.cell.IBizCell;

import java.util.List;

/**
 * File: com.done.bizrecyclerviewlib.adpater.TypePool.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/13
 */

public interface TypePool {
    /**
     * 返回cell数量
     *
     * @return items' count
     */
    int getCount();

    /**
     * 用于type返回时候使用，考虑到recyclerView本身的缓存优化，特此命名
     *
     * @return
     */
    int getViewType(int position);

    /**
     * 用于type返回时候使用，考虑到recyclerView本身的缓存优化，特此命名
     *
     * @return
     */
    int getViewType(@NonNull Class<?> clazz);

    int firstIndexOf(IBizCell cell);

    /**
     * 添加cell
     *
     * @param cell
     */
    int addCell(IBizCell cell);

    void addCell(int index, IBizCell cell);

    int addCells(List<IBizCell> cells);

    List<IBizCell> getAllCells();

    /**
     * 追加到列表的末尾，这里只用于footer，多次调用的话只最后一次生效
     *
     * @param cell
     */
    void addLastCell(IBizCell cell);

    /**
     * 同上，位置为第0个
     *
     * @param cell
     */
    void addFirstCell(IBizCell cell);

    /**
     * 移除指定index的cell
     *
     * @param index 索引
     */
    IBizCell removeCell(int index);

    /**
     * 移除列表中的第一个改cell类型的cell，such as remove(a) [a,b,a,c]->[b,a,c]
     *
     * @param cell
     */
    void removeCell(IBizCell cell);

    void clear();

    /**
     * 拿到指定的类，目前没什么大用，便于以后扩展使用
     *
     * @param index
     * @return
     */
    @NonNull
    Class<?> getClass(int index);

    /**
     * 获取指定的cell
     *
     * @param index
     * @return
     */
    @NonNull
    IBizCell getCell(int index);

    /**
     * 通过ViewType拿到指定的Cell
     *
     * @param viewType
     * @return
     */
    @Nullable
    IBizCell getCellForType(int viewType);
}
