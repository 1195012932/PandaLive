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
import com.example.panda.model.live.bean.TopBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.TopPtr;
import com.example.panda.view.fragment.livefragment.liveview.TopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class TOP extends BaseFragment implements TopView {
    private LivePresenter livePresenter;
    List<TopBean.VideoBean> list = new ArrayList<>();
    private ListView top_list;
    private PtrClassicFrameLayout top_ptr;
    Handler handler=new Handler();
    private TopAdapter adapter;

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
        livePresenter = new TopPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        top_list= (ListView) view.findViewById(R.id.top_list);
        top_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.top_ptr);
        adapter = new TopAdapter(getActivity(), list);
        top_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.top;
    }

    @Override
    public void TopView(final List<TopBean.VideoBean> TopBeen) {
        list.addAll(TopBeen);
        top_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                top_ptr.autoRefresh(true);
            }
        },1000);
        top_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(TopBeen);
                        adapter.notifyDataSetChanged();
                        top_ptr.refreshComplete();
                        if(!top_ptr.isLoadMoreEnable()){
                            top_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        top_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(TopBeen);
                        adapter.notifyDataSetChanged();
                        top_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
