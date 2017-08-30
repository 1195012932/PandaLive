package com.example.panda.view.activity.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.adapter.home.InteractionAdapter;
import com.example.panda.base.BaseActivity;
import com.example.panda.model.entity.home.InteraBean;
import com.example.panda.presenter.home.intera.InteraPreImpl;
import com.example.panda.presenter.home.intera.InteraPresenter;
import com.example.panda.view.home.InteraView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class Interaction extends BaseActivity implements InteraView {


    private XRecyclerView mRecyclerView;
    private ArrayList<View> headerview = new ArrayList<>();
    private ArrayList<View> mFootViews = new ArrayList<>();
    private InteractionAdapter interactionAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_interaction;
    }


    @Override
    protected void initView() {
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");

        InteraPresenter intera = new InteraPreImpl(this);
        intera.interaurl(map);

        mRecyclerView = (XRecyclerView) findViewById(R.id.interactin_xrecy);
        ImageView interafinsh = (ImageView) findViewById(R.id.intera_finsh);
        interafinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.SemiCircleSpin);
        mRecyclerView.setLoadingMoreEnabled(false);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                        mRecyclerView.setLoadingMoreEnabled(true);
                    }
                }, 1000);
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


    @Override
    public void OninteraSuccess(InteraBean interaBean) {
        final List<InteraBean.InteractiveBean> interactive = interaBean.getInteractive();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        interactionAdapter = new InteractionAdapter(interactive, Interaction.this);
        mRecyclerView.setAdapter(interactionAdapter);

        interactionAdapter.setOnItemClickListener(new InteractionAdapter.OnItemClickListener() {
            @Override
            public void onitemclickListerner(int pos) {
                Intent intent = new Intent(Interaction.this, InteracationWeb.class);
                String url = interactive.get(pos).getUrl();
                String title = interactive.get(pos).getTitle();
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OninteraError() {

    }

}
