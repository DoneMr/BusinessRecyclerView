package com.done.businessrecyclerview.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.done.bizrecyclerviewlib.BizRecyclerView;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.cell.CardTextCell;
import com.done.businessrecyclerview.constant.SampleContants;
import com.done.businessrecyclerview.model.CellTextInfo;

public class SampleConfigActivity extends BaseActivity {

    private BizRecyclerView mRvConfig;

    private BizDefaultAdapter mAdapter;

    @Override
    protected void initData() {
        String[] stringArray = mContext.getResources().getStringArray(R.array.samples);
        if (stringArray.length > 0) {
            for (int i = 0; i < stringArray.length; i++) {
                CellTextInfo data = new CellTextInfo();
                String text = stringArray[i];
                Intent jumpIntent = null;
                if (SampleContants.SINGLE_RECYCLERVIEW.equals(text)) {
                    jumpIntent = new Intent();
                    jumpIntent.setClassName(mContext.getPackageName(),
                            "com.done.businessrecyclerview.activity.SingleRecyclerViewActivity");
                } else if (SampleContants.COMMUNICATION_RECYCLERVIEW.equals(text)) {
                    jumpIntent = new Intent();
                    jumpIntent.setClassName(mContext.getPackageName(),
                            "com.done.businessrecyclerview.activity.CommunicationRecyclerViewActivity");
                } else if (SampleContants.DELETE_RECYCLERVIEW.equals(text)) {

                } else if (SampleContants.ALL_FEATURE_RECYCLERVIEW.equals(text)) {

                } else if (SampleContants.MULTI_RECYCLERVIEW.equals(text)) {

                } else if (SampleContants.LAYOUTMANAGER.equals(text)) {

                }
                data.setText(text);
                data.setTextColor("#FF0000");
                data.setTextSize("20");
                data.setJumpIntent(jumpIntent);
                mAdapter.add(new CardTextCell(data));
            }
        }
    }

    @Override
    protected void initView() {
        mAdapter = new BizDefaultAdapter();
        mRvConfig = findViewById(R.id.rv_config);
        mRvConfig.setAdapter(mAdapter);
        mRvConfig.setLayoutManager(new BizLinearLayoutmanager(mContext, LinearLayoutManager.VERTICAL, false));
        getLifecycle().addObserver(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample_config;
    }
}
