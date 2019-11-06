package com.done.businessrecyclerview;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.done.bizrecyclerviewlib.BizRecyclerView;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager;
import com.done.businessrecyclerview.cell.CardTextCell;
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
                data.setText(stringArray[i]);
                data.setTextColor("#FF0000");
                data.setTextSize("20");
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample_config;
    }
}
