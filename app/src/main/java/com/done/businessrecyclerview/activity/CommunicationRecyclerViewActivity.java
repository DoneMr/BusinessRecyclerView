package com.done.businessrecyclerview.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.constant.SampleContants;
import com.done.businessrecyclerview.util.SampleUtils;
import com.done.businessrecyclerview.view.UniversalTitleBar;

import java.util.List;

/**
 * File:com.done.businessrecyclerview.activity.CommunicationRecyclerViewActivity
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-07
 */
public class CommunicationRecyclerViewActivity extends BaseActivity {

    private RecyclerView mRvList;

    private BizBaseAdapter mAdapter;

    private UniversalTitleBar mTitle;

    @Override
    protected void initData() {
        List<IBizCell> cells = SampleUtils.getRandomReceiveCardTextCells("CommunicationRecyclerView", "#FFFF00");
        cells.add(1, SampleUtils.getSendMessageCell("我可以发消息给其他人哟~", "#FF0000"));
        mAdapter.addCells(cells);
    }

    @Override
    protected void initView() {
        mRvList = findViewById(R.id.rv_communicate);
        mAdapter = new BizDefaultAdapter();
        mRvList.setLayoutManager(new BizLinearLayoutmanager(mContext, LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(mAdapter);
        mTitle = findViewById(R.id.communicate_title);
        mTitle.setOnToolBarClick(new UniversalTitleBar.OnToolBarClickSample() {
            @Override
            public void onLeftClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_communication_recycler_view;
    }
}
