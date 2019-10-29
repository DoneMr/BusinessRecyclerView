package com.done.bizrecyclerviewlib.adpater;


import androidx.annotation.NonNull;

import com.done.bizrecyclerviewlib.Preconditions;
import com.done.bizrecyclerviewlib.util.BizLogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File: com.done.bizrecyclerviewlib.adpater.ViewTypeHelper.java
 * Description: 用于维护RV的ViewType，永不销毁已经存在的Class对应的ViewType
 *
 * @author Done
 * date 2018/12/13
 */

public class ViewTypeHelper {

    private static final String TAG = "ViewTypeHelper";

    private static final int INDEX_UNIT = 1;

    public static final int FAIL_VIEW_TYPE = -1;

    private volatile static ViewTypeHelper instance;

    private Map<String, ViewType> mViewTypes;

    private int mCurType;

    public ViewTypeHelper() {
        this.mViewTypes = new ConcurrentHashMap<>();
    }

    public synchronized void computeType(@NonNull Class<?> clazz) {
        Preconditions.checkNotNull(clazz, "please check your class, this is null");
        String className = clazz.getName();
        if (!mViewTypes.containsKey(className)) {
            mCurType += INDEX_UNIT;
            ViewType viewType = new ViewType();
            viewType.setClazz(clazz);
            viewType.setType(mCurType);
            mViewTypes.put(className, viewType);
        }
        BizLogger.d(TAG, "computeType--->" + mViewTypes.get(className).getClazz().getName() + "(" + mViewTypes.size() + "):" + mViewTypes.get(className).getType());
    }

    public synchronized int getViewType(@NonNull Class<?> clazz) {
        Preconditions.checkNotNull(clazz, "please check your class, this is null");
        String className = clazz.getName();
        int viewType = FAIL_VIEW_TYPE;
        if (!mViewTypes.containsKey(className)) {
            Preconditions.throwIllegalArgumentException("we have no this cell viewType, check your code");
        } else {
            viewType = mViewTypes.get(className).getType();
        }
        BizLogger.d(TAG, "getViewType--->" + mViewTypes.get(className).getClazz().getName() + "(" + mViewTypes.size() + "):" + mViewTypes.get(className).getType());
        return viewType;
    }

    public synchronized ViewType getViewTypeObj(@NonNull Class<?> clazz) {
        Preconditions.checkNotNull(clazz, "please check your class, this is null");
        String className = clazz.getName();
        ViewType viewType = null;
        if (!mViewTypes.containsKey(className)) {
            Preconditions.throwIllegalArgumentException("we have no this cell viewType, check your code");
        } else {
            viewType = mViewTypes.get(className);
        }
        return viewType;
    }

    public synchronized void clear() {
        mViewTypes.clear();
        mCurType = 0;
    }

    public static class ViewType {
        private Class<?> clazz;
        private int type;

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
