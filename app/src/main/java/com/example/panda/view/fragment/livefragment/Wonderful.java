package com.example.panda.view.fragment.livefragment;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.WonBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.WonPtr;
import com.example.panda.view.fragment.livefragment.liveview.WonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Wonderful extends BaseFragment implements WonView {
    private LivePresenter livePresenter;
    List<WonBean.VideoBean> list = new ArrayList<>();
    private ListView won_list;
    private PtrClassicFrameLayout won_ptr;
    Handler handler=new Handler();
    private WonAdapter adapter;
    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        livePresenter = new WonPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        won_list= (ListView) view.findViewById(R.id.won_list);
        won_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.won_ptr);
        adapter = new WonAdapter(getActivity(), list);
        won_list.setAdapter(adapter);
    }
    @Override
    protected int getLayout() {
        return R.layout.wonderful;
    }

    @Override
    public void WonView(final List<WonBean.VideoBean> WonBeen) {
        list.addAll(WonBeen);
        won_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                won_ptr.autoRefresh(true);
            }
        },1000);
        won_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(WonBeen);
                        adapter.notifyDataSetChanged();
                        won_ptr.refreshComplete();
                        if(!won_ptr.isLoadMoreEnable()){
                            won_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        won_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(WonBeen);
                        adapter.notifyDataSetChanged();
                        won_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
