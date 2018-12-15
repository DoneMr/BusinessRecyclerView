package com.done.bizrecyclerviewlib.features;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.done.bizrecyclerviewlib.util.BizLogger;

/**
 * File: com.done.bizrecyclerviewlib.features.BizDefaultAnimator.java
 * Description: xxx
 *
 * @author Done
 * @date 2018/12/15
 */

public class BizDefaultAnimator extends DefaultItemAnimator {

    private static final String TAG = "BizDefaultAnimator";

    private static final long ADD_ANIM_DURATION = 200L;
    private static final long REMOVE_ANIM_DURATION = 200L;
    private static final long MOVE_ANIM_DURATION = 200L;
    private static final long CHANGE_ANIM_DURATION = 200L;

    private AnimationListener mListener;

    public BizDefaultAnimator(int addDuration, int removeDuraion, int changeDuration, int moveDuration) {
        setAddDuration(addDuration);
        setRemoveDuration(removeDuraion);
        setMoveDuration(moveDuration);
        setChangeDuration(changeDuration);
    }

    public BizDefaultAnimator() {
        setAddDuration(ADD_ANIM_DURATION);
        setRemoveDuration(REMOVE_ANIM_DURATION);
        setMoveDuration(MOVE_ANIM_DURATION);
        setChangeDuration(CHANGE_ANIM_DURATION);
    }

    public AnimationListener getAnimationListener() {
        return mListener;
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    @Override
    public void onAddFinished(RecyclerView.ViewHolder item) {
        super.onAddFinished(item);
        if (mListener != null) {
            mListener.onAddFinished(item);
        }
    }

    @Override
    public void onMoveFinished(RecyclerView.ViewHolder item) {
        super.onMoveFinished(item);
        if (mListener != null) {
            mListener.onMoveFinished(item);
        }
    }

    @Override
    public void onRemoveFinished(RecyclerView.ViewHolder item) {
        super.onRemoveFinished(item);
        if (mListener != null) {
            mListener.onRemoveFinished(item);
        }
    }

    @Override
    public void onChangeFinished(RecyclerView.ViewHolder item, boolean oldItem) {
        super.onChangeFinished(item, oldItem);
        if (mListener != null) {
            mListener.onChangeFinished(item, oldItem);
        }
    }


    public interface AnimationListener {

        void onAddFinished(RecyclerView.ViewHolder item);

        void onMoveFinished(RecyclerView.ViewHolder item);

        void onRemoveFinished(RecyclerView.ViewHolder item);

        void onChangeFinished(RecyclerView.ViewHolder item, boolean oldItem);
    }
}
