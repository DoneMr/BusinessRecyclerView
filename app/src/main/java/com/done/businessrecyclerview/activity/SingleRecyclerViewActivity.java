package com.done.businessrecyclerview.activity;

import android.os.Build;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.features.BizScrollListener;
import com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager;
import com.done.businessrecyclerview.MyApplication;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.cell.CardTextCell;
import com.done.businessrecyclerview.cell.VerticalLoadMoreCell;
import com.done.businessrecyclerview.constant.SampleContants;
import com.done.businessrecyclerview.model.CellTextInfo;
import com.done.businessrecyclerview.model.LoadMoreCellInfo;
import com.done.businessrecyclerview.view.UniversalTitleBar;

/**
 * File:com.done.businessrecyclerview.activity.SingleRecyclerViewActivity
 * Description:单一列表
 *
 * @author maruilong
 * @date 2019-11-07
 */
public class SingleRecyclerViewActivity extends BaseActivity {

    private RecyclerView mRvSingle;

    private BizBaseAdapter mAdapter;

    private int mPageCount = 1;

    private UniversalTitleBar mTitleBar;

    @Override
    protected void initData() {
        addListData();
    }

    private void addListData() {
        if (mPageCount <= SampleContants.LIST_PAGE_COUNT) {
            int itemCount = mAdapter.getItemCount() - 1;
            IBizCell loadMore = mAdapter.getCell(itemCount);
            if (loadMore instanceof VerticalLoadMoreCell) {
                mAdapter.remove(itemCount);
            }
            for (int i = 0; i < SampleContants.LIST_PAGE_SIZE; i++) {
                IBizCell cell;
                if (i == SampleContants.LIST_PAGE_SIZE - 1) {
                    LoadMoreCellInfo loadMoreCellInfo = new LoadMoreCellInfo();
                    loadMoreCellInfo.loadMoreText = mPageCount == SampleContants.LIST_PAGE_SIZE ? "加载完成" : "正在加载...";
                    cell = new VerticalLoadMoreCell(loadMoreCellInfo);
                } else {
                    CellTextInfo cellTextInfo = new CellTextInfo();
                    cellTextInfo.setText("SingleRecyclerView" + i);
                    cellTextInfo.setTextSize(String.valueOf(SampleContants.LIST_PAGE_SIZE + i));
                    cellTextInfo.setTextColor("#FF00FF");
                    cell = new CardTextCell(cellTextInfo);
                }
                mAdapter.add(cell);
            }
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            addListData();
            mPageCount++;
        }
    };

    private void appendNextPage() {
        MyApplication.getHandler().removeCallbacks(runnable);
        MyApplication.getHandler().postDelayed(runnable, 2000);
    }

    @Override
    protected void initView() {
        mRvSingle = findViewById(R.id.rv_single);
        mTitleBar = findViewById(R.id.single_rv_title);
        mTitleBar.setOnToolBarClick(new UniversalTitleBar.OnToolBarClickSample() {
            @Override
            public void onRightClick(View view) {
                resetList();
            }

            @Override
            public void onLeftClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    onBackPressed();
                }
            }
        });
        mAdapter = new BizDefaultAdapter();
        mRvSingle.setAdapter(mAdapter);
        mRvSingle.setLayoutManager(new BizLinearLayoutmanager(mContext, LinearLayoutManager.VERTICAL, false));
        mRvSingle.addOnScrollListener(new BizScrollListener() {
            @Override
            public void onScrollToBottom(RecyclerView recyclerView, int newState) {
                appendNextPage();
            }
        });
    }

    private void resetList() {
        mAdapter.clear();
        mPageCount = 0;
        MyApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addListData();
            }
        }, 1000L);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_recycler_view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHandler().removeCallbacksAndMessages(null);
    }
}
