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
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.ProPtr;
import com.example.panda.view.fragment.livefragment.liveview.ProView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Programs extends BaseFragment implements ProView {
    Handler handler=new Handler();
    private LivePresenter livePresenter;
    List<ProBean.VideoBean> list = new ArrayList<>();
    private ListView pro_list;
    private PtrClassicFrameLayout pro_ptr;
    private ProAdapter adapter;
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
        livePresenter = new ProPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        pro_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.pro_ptr);
        pro_list= (ListView) view.findViewById(R.id.pro_list);
        adapter = new ProAdapter(getActivity(), list);
        pro_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.programs;
    }

    @Override
    public void ProView(final List<ProBean.VideoBean> ProBeen) {
        list.addAll(ProBeen);
        pro_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                pro_ptr.autoRefresh(true);
            }
        },1000);
        pro_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(ProBeen);
                        adapter.notifyDataSetChanged();
                        pro_ptr.refreshComplete();
                        if(!pro_ptr.isLoadMoreEnable()){
                            pro_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        pro_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(ProBeen);
                        adapter.notifyDataSetChanged();
                        pro_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
