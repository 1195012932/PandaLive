package com.example.panda.view.activity.home;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class Interaction extends BaseActivity {


    private XRecyclerView mRecyclerView;
    private ArrayList<View> headerview = new ArrayList<>();
    private ArrayList<View> mFootViews = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_interaction;
    }


    @Override
    protected void initView() {
        mRecyclerView = (XRecyclerView) findViewById(R.id.interactin_xrecy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Toast.makeText(Interaction.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        mRecyclerView.refreshComplete();
                        mRecyclerView.setLoadingMoreEnabled(false);
                    }
                }, 1000);
            }
        });

    }


    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


}
