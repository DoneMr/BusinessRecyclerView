package com.done.businessrecyclerview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * File:com.done.businessrecyclerview.BaseActivity
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-05
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();
}
