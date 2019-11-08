package com.done.businessrecyclerview.activity;

import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.bizrecyclerviewlib.layoutmanager.BizLinearLayoutmanager;
import com.done.businessrecyclerview.R;
import com.done.businessrecyclerview.helper.DeleteScrollTouchHelper;
import com.done.businessrecyclerview.util.SampleUtils;
import com.done.businessrecyclerview.view.UniversalTitleBar;

import java.util.List;

/**
 * File:com.done.businessrecyclerview.activity.DeleteRecyclerViewActivity
 * Description:侧滑删除的View
 *
 * @author maruilong
 * @date 2019-11-08
 */
public class DeleteRecyclerViewActivity extends BaseActivity {

    private RecyclerView mRvDelete;
    private BizBaseAdapter mAdapter;

    private DeleteScrollTouchHelper mScrollTouchHelper;

    private UniversalTitleBar mTitle;

    @Override
    protected void initData() {
        List<IBizCell> deleteCells = SampleUtils.getDeleteCells();
        deleteCells.addAll(SampleUtils.getDeleteCells());
        if (!SampleUtils.isListEmpty(deleteCells)) {
            mAdapter.addCells(deleteCells);
        }
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.delete_title);
        mTitle.setOnToolBarClick(new UniversalTitleBar.OnToolBarClickSample() {
            @Override
            public void onLeftClick(View view) {
                onBackPressed();
            }
        });
        mRvDelete = findViewById(R.id.rv_delete);
        mAdapter = new BizDefaultAdapter();
        mRvDelete.setAdapter(mAdapter);
        mRvDelete.setLayoutManager(new BizLinearLayoutmanager(mContext, LinearLayoutManager.VERTICAL, false));
        mScrollTouchHelper = new DeleteScrollTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mScrollTouchHelper);
        itemTouchHelper.attachToRecyclerView(mRvDelete);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delete_recycler_view;
    }
}
