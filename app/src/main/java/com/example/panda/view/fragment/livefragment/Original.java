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
import com.example.panda.model.live.bean.OriBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.OriPtr;
import com.example.panda.view.fragment.livefragment.liveview.OriginView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Original extends BaseFragment implements OriginView {
    private LivePresenter livePresenter;
    List<OriBean.VideoBean> list = new ArrayList<>();
    private ListView ori_list;
    private PtrClassicFrameLayout ori_ptr;
    Handler handler=new Handler();
    private OriAdapter adapter;

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
        livePresenter = new OriPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        ori_list= (ListView) view.findViewById(R.id.ori_list);
        ori_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.ori_ptr);
        adapter = new OriAdapter(getActivity(), list);
        ori_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.original;
    }

    @Override
    public void OriView(final List<OriBean.VideoBean> NotBeen) {
        list.addAll(NotBeen);
        ori_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                ori_ptr.autoRefresh(true);
            }
        },1000);
        ori_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(NotBeen);
                        adapter.notifyDataSetChanged();
                        ori_ptr.refreshComplete();
                        if(!ori_ptr.isLoadMoreEnable()){
                            ori_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        ori_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(NotBeen);
                        adapter.notifyDataSetChanged();
                        ori_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
