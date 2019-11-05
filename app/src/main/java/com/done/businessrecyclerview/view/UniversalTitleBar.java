package com.done.businessrecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.done.businessrecyclerview.R;

/**
 * File:com.done.businessrecyclerview.view.UniversalTitleBar
 * Description:顶部bar
 *
 * @author maruilong
 * @date 2019-11-05
 */
public class UniversalTitleBar extends FrameLayout implements View.OnClickListener {

    private Context mContext;

    private View mRootView;

    private ImageView mIvLeft, mIvRight;
    private TextView mTvTitle;

    private OnToolBarClick onToolBarClick;

    public UniversalTitleBar(@NonNull Context context) {
        this(context, null);
    }

    public UniversalTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UniversalTitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public OnToolBarClick getOnToolBarClick() {
        return onToolBarClick;
    }

    public void setOnToolBarClick(OnToolBarClick onToolBarClick) {
        this.onToolBarClick = onToolBarClick;
    }

    private void initView(Context context) {
        mContext = context;
        mRootView = inflate(mContext, R.layout.view_universal_toolbar, this);
        mIvLeft = mRootView.findViewById(R.id.iv_left);
        mIvRight = mRootView.findViewById(R.id.iv_right);
        mTvTitle = mRootView.findViewById(R.id.tv_title);
    }

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();
        if (viewId == R.id.iv_left) {
            if (onToolBarClick != null) {
                onToolBarClick.onLeftClick(v);
            }
        } else if (viewId == R.id.iv_right) {
            if (onToolBarClick != null) {
                onToolBarClick.onRightClick(v);
            }
        } else if (viewId == R.id.tv_title) {
            if (onToolBarClick != null) {
                onToolBarClick.onTitleClick(v);
            }
        }
    }

    public static class OnToolBarClickSample implements OnToolBarClick {

        @Override
        public void onLeftClick(View view) {

        }

        @Override
        public void onRightClick(View view) {

        }

        @Override
        public void onTitleClick(View view) {

        }
    }

    public interface OnToolBarClick {

        void onLeftClick(View view);

        void onRightClick(View view);

        void onTitleClick(View view);
    }
}
