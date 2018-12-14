package com.done.businessrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.done.bizrecyclerviewlib.adpater.BizDefaultAdapter;
import com.done.bizrecyclerviewlib.adpater.BizBaseAdapter;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd, mClear, mRemove;

    private RecyclerView mRvTest;

    private BizBaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvTest = findViewById(R.id.rv_test);
        mAdd = findViewById(R.id.btn_test1);
        mClear = findViewById(R.id.btn_test2);
        mRemove = findViewById(R.id.btn_test3);
        mAdd.setOnClickListener(this);
        mClear.setOnClickListener(this);
        mRemove.setOnClickListener(this);
        mAdapter = new BizDefaultAdapter();
        mRvTest.setAdapter(mAdapter);
        mRvTest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private int addStyle = 0;

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();
        if (viewId == R.id.btn_test1) {
            if (addStyle > 1) {
                addStyle = 0;
            }
            if (addStyle == 0) {
                mAdapter.add(new ReceiverCell(new ComnuicationViewModel()));
                mAdapter.add(new SenderCell(new ComnuicationViewModel()));
            } else if (addStyle == 1) {
                List<NormalCell3> cell3s = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    NormalCell3 cell3 = new NormalCell3(null);
                    cell3s.add(cell3);
                }
                mAdapter.addCells(cell3s);
            } else if (addStyle == 2) {
                mAdapter.add(mAdapter.getItemCount() - 3, new NormalCell4(null));
            }
            addStyle++;
        } else if (viewId == R.id.btn_test2) {
            mAdapter.clear();
        } else if (viewId == R.id.btn_test3) {
            mAdapter.remove(2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = MyApplication.getRefWatcher(this);
        watcher.watch(this);
    }
}
