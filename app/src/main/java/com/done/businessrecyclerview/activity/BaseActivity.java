package com.done.businessrecyclerview.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * File:com.done.businessrecyclerview.activity.BaseActivity
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-05
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onActivityCreateBefore();
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        onInitViewBefore();
        initView();
        initData();
    }

    public void onInitViewBefore() {

    }

    public void onActivityCreateBefore() {

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();
}
