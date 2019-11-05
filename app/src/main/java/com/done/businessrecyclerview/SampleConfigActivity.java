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
        for (int i = 0; i < 20; i++) {
            CellTextInfo data = new CellTextInfo();
            data.setText("");
            data.setTextColor("#FF0000");
            mAdapter.add(new CardTextCell(data));
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
