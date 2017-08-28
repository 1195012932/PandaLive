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
import com.example.panda.model.live.bean.NotBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.NotPtr;
import com.example.panda.view.fragment.livefragment.liveview.NotView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class NotLet extends BaseFragment implements NotView {
    private LivePresenter livePresenter;
    List<NotBean.VideoBean> list = new ArrayList<>();
    List<String> lists = new ArrayList<>();
    private ListView not_list;
    private PtrClassicFrameLayout not_ptr;
    private NotAdapter adapter;
    Handler handler=new Handler();

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    private void shangla() {


    }

    private void xiahua() {



    }

    @Override
    protected void initView(View view) {
        livePresenter = new NotPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        not_list = (ListView) view.findViewById(R.id.not_list);
        not_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.not_ptr);
        adapter = new NotAdapter(getActivity(), list);
        not_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.notlet;
    }

    @Override
    public void NotView(final List<NotBean.VideoBean> NotBeen) {
        String img = NotBeen.get(0).getImg();
        System.out.println("1111111" + img);
        list.addAll(NotBeen);
        not_ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                not_ptr.autoRefresh(true);
            }
        },1000);
        not_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                xiahua();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(NotBeen);
                        adapter.notifyDataSetChanged();
                        not_ptr.refreshComplete();
                        if(!not_ptr.isLoadMoreEnable()){
                            not_ptr.setLoadMoreEnable(true);

                        }
                    }
                });
            }
        });
        not_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                shangla();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(NotBeen);
                        adapter.notifyDataSetChanged();
                        not_ptr.loadMoreComplete(true);
                    }
                });
            }
        });
    }



}
