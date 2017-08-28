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
import com.example.panda.model.live.bean.RecBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.RecPtr;
import com.example.panda.view.fragment.livefragment.liveview.RecView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Record extends BaseFragment implements RecView {
    Handler handler=new Handler();
    private LivePresenter livePresenter;
    List<RecBean.VideoBean> list = new ArrayList<>();
    private ListView rec_list;
    private PtrClassicFrameLayout rec_ptr;
    private RecAdapter adapter;

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
        livePresenter = new RecPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        rec_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.rec_ptr);
        rec_list= (ListView) view.findViewById(R.id.rec_list);
        adapter = new RecAdapter(getActivity(), list);
        rec_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.record;
    }

    @Override
    public void RecView(final List<RecBean.VideoBean> RecBeen) {
        list.addAll(RecBeen);
        rec_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                rec_ptr.autoRefresh(true);
            }
        },1000);
        rec_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(RecBeen);
                        adapter.notifyDataSetChanged();
                        rec_ptr.refreshComplete();
                        if(!rec_ptr.isLoadMoreEnable()){
                            rec_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        rec_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(RecBeen);
                        adapter.notifyDataSetChanged();
                        rec_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
