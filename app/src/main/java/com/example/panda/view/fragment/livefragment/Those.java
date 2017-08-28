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
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.ThoPtr;
import com.example.panda.view.fragment.livefragment.liveview.ThoseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Those extends BaseFragment implements ThoseView {
    private LivePresenter livePresenter;
    List<ThoBean.VideoBean> list = new ArrayList<>();
    private ListView tho_list;
    private PtrClassicFrameLayout tho_ptr;
    Handler handler=new Handler();
    private ThoAdapter adapter;
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
        livePresenter = new ThoPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        tho_list= (ListView) view.findViewById(R.id.tho_list);
        tho_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.tho_ptr);
        adapter = new ThoAdapter(getActivity(), list);
        tho_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.those;
    }

    @Override
    public void ThoView(final List<ThoBean.VideoBean> ThoBeen) {
        list.addAll(ThoBeen);
        tho_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                tho_ptr.autoRefresh(true);
            }
        },1000);
        tho_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(ThoBeen);
                        adapter.notifyDataSetChanged();
                        tho_ptr.refreshComplete();
                        if(!tho_ptr.isLoadMoreEnable()){
                            tho_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        tho_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(ThoBeen);
                        adapter.notifyDataSetChanged();
                        tho_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }


}
