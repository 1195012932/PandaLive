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
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.RollPtr;
import com.example.panda.view.fragment.livefragment.liveview.RollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Roll extends BaseFragment implements RollView {
    Handler handler=new Handler();
    private LivePresenter livePresenter;
    List<RollBean.VideoBean> list = new ArrayList<>();
    private ListView roll_list;
    private PtrClassicFrameLayout roll_ptr;
    private RollAdapter adapter;
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
        livePresenter = new RollPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        roll_list= (ListView) view.findViewById(R.id.roll_list);
        roll_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.roll_ptr);
        adapter = new RollAdapter(getActivity(),list);
        roll_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.roll;
    }

    @Override
    public void RollView(final List<RollBean.VideoBean> RollBeen) {
        list.addAll(RollBeen);
        roll_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                roll_ptr.autoRefresh(true);
            }
        },1000);
        roll_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(RollBeen);
                        adapter.notifyDataSetChanged();
                        roll_ptr.refreshComplete();
                        if(!roll_ptr.isLoadMoreEnable()){
                            roll_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        roll_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(RollBeen);
                        adapter.notifyDataSetChanged();
                        roll_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
