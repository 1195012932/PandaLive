package com.example.panda.view.fragment.chinafragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.adapter.china.BadalingRecyclerviewAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.chian.ChianBean2;
import com.example.panda.utils.OkHttpsManner;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/29.
 */

public class AttractionsFragment extends BaseFragment {

    private String name;
    private List<ChianBean2.LiveBean> list = new ArrayList<>();
    private String url;
    private XRecyclerView att_rv;
    private BadalingRecyclerviewAdapter adapter;

    public AttractionsFragment(String url) {
        this.url = url;
    }

    @Override
    protected void loadData() {
        att_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                        att_rv.refreshComplete();
                        att_rv.setLoadingMoreEnabled(true);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "已无更多数据！！！", Toast.LENGTH_SHORT).show();
                        att_rv.refreshComplete();
                        att_rv.setLoadingMoreEnabled(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initListener() {
        OkHttpsManner manner = new OkHttpsManner();
        manner.getNetData(url, new OkHttpsManner.CallBacks() {
            @Override
            public void getString(String ss) {
                Gson gson = new Gson();
                ChianBean2 chianBean2 = gson.fromJson(ss, ChianBean2.class);
                list.addAll(chianBean2.getLive());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        att_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new BadalingRecyclerviewAdapter(list, getContext());
                        att_rv.setAdapter(adapter);
                    }
                });

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        att_rv = view.findViewById(R.id.badaling_recyclerview);

    }

    @Override
    protected int getLayout() {
        return R.layout.bdl_item;
    }


    @Override
    public void onResume() {
        super.onResume();
        list.clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
    }

}
