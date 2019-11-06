package com.done.bizrecyclerviewlib.holder;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.Preconditions;

import java.lang.ref.WeakReference;

/**
 * File: com.done.bizrecyclerviewlib.holder.BizViewHolder.java
 * Description: 通用viewHolder 建议只操作原生提供的方法，需要增加三方功能建议集成此类扩展
 * 可直接用于ListView，ViewPager，GridView的holder使用
 *
 * @author Done
 * date 2018/12/12
 */

public class BizViewHolder extends RecyclerView.ViewHolder {

    private final View mItemView;

    private WeakReference<Context> mContext;

    private SparseArray<View> mViews;

    private BizViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);
        Preconditions.checkNotNull(context, "create BizRecyclerView's context is NULL");
        mContext = new WeakReference<>(context);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    public static BizViewHolder createHolder(@NonNull Context context, View itemView) {
        return new BizViewHolder(context, itemView);
    }

    public static BizViewHolder createHolder(@NonNull Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        return createHolder(context, itemView);
    }

    @Nullable
    public Context getContext() {
        return mContext == null ? null : mContext.get();
    }

    public <T extends View> T findViewById(final int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getItemView() {
        return mItemView;
    }

//------------------------------以下为基础的赋值操作，任意编写即可，建议只操作原生提供的方法，需要增加三方功能建议集成此类扩展-----------------------//

    public void setText(final int viewId, String text, float textSizeSp) {
        setText(viewId, text);
        setTextSize(viewId, textSizeSp);
    }

    public void setText(final int viewId, String text, String textColor) {
        setText(viewId, text);
        setTextColor(viewId, textColor);
    }

    public void setText(final int viewId, String text, String textColor, String textSize) {
        setText(viewId, text);
        setTextColor(viewId, textColor);
        setTextSize(viewId, textSize);
    }

    public void setText(final int viewId, String text, @ColorInt int textColor) {
        setText(viewId, text);
        setTextColor(viewId, textColor);
    }

    public void setText(final int viewId, String text) {
        if (!TextUtils.isEmpty(text)) {
            View view = findViewById(viewId);
            if (view != null) {
                if (view instanceof Button) {
                    ((Button) view).setText(text);
                } else if (view instanceof TextView) {
                    ((TextView) view).setText(text);
                }
            }
        }
    }

    public void appendText(final int viewId, String text) {
        if (!TextUtils.isEmpty(text)) {
            View view = findViewById(viewId);
            if (view != null) {
                if (view instanceof Button) {
                    ((Button) view).append(text);
                } else if (view instanceof TextView) {
                    ((TextView) view).append(text);
                }
            }
        }
    }


    public void setHtmlText(final int viewId, String html) {
        if (!TextUtils.isEmpty(html)) {
            View view = findViewById(viewId);
            if (view != null) {
                if (view instanceof Button) {
                    ((Button) view).setText(Html.fromHtml(html));
                } else if (view instanceof TextView) {
                    ((TextView) view).setText(Html.fromHtml(html));
                }
            }
        }
    }

    public void setTextColor(final int viewId, String textColor) {
        int color = -1;
        try {
            color = Color.parseColor(textColor);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        setTextColor(viewId, color);
    }

    public void setTextColor(final int viewId, @ColorInt int textColor) {
        View view = findViewById(viewId);
        if (view != null) {
            if (view instanceof Button) {
                ((Button) view).setTextColor(textColor);
            } else if (view instanceof TextView) {
                ((TextView) view).setTextColor(textColor);
            }
        }
    }

    public void setTextSize(final int viewId, float textSizeSp) {
        if (textSizeSp > 0) {
            View view = findViewById(viewId);
            if (view != null) {
                if (view instanceof Button) {
                    ((Button) view).setTextSize(textSizeSp);
                } else if (view instanceof TextView) {
                    ((TextView) view).setTextSize(textSizeSp);
                }
            }
        }
    }

    public void setTextSize(final int viewId, String textSizeSp) {
        if (!TextUtils.isEmpty(textSizeSp)) {
            float textSize = 0;
            try {
                textSize = Float.parseFloat(textSizeSp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (textSize > 0) {
                setTextSize(viewId, textSize);
            }
        }
    }

    public void setVisible(final int viewId, boolean isShow) {
        View view = findViewById(viewId);
        if (view != null) {
            if (isShow) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * if u need set view other params such as {@link View#INVISIBLE}
     *
     * @param viewId
     * @param visible
     */
    public void setVisible(final int viewId, int visible) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setVisibility(visible);
        }
    }

    public void setImageSource(final int viewId, @DrawableRes int resourceId) {
        View view = findViewById(viewId);
        if (view != null && view instanceof ImageView) {
            ((ImageView) view).setImageResource(resourceId);
        }
    }

    public void setBackgroundColor(final int viewId, @ColorInt int color) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    public void addView(final int viewGroupId, View childView) {
        View view = findViewById(viewGroupId);
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).addView(childView);
        }
    }

    public void removeView(final int viewGroupId, View childView) {
        View view = findViewById(viewGroupId);
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeView(childView);
        }
    }

    public void removeAllView(final int viewGroupId) {
        View view = findViewById(viewGroupId);
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
    }

    public int px2dip(float pxValue) {
        Context context = getContext();
        if (context != null) {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5F);
        } else {
            return -1;
        }

    }

    public int dip2px(float dipValue) {
        Context context = getContext();
        if (context != null) {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5F);
        } else {
            return -1;
        }
    }

    public int px2sp(float pxValue) {
        Context context = getContext();
        if (context != null) {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5F);
        } else {
            return -1;
        }
    }

    public int sp2px(float spValue) {
        Context context = getContext();
        if (context != null) {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5F);
        } else {
            return -1;
        }
    }
}
