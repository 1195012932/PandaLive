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
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/8/24.
 */

public class Wonderful extends BaseFragment implements WonView {
    private LivePresenter livePresenter;
    List<WonBean.VideoBean> list = new ArrayList<>();
    private ListView won_list;
    String url="http://api.cntv.cn/video/videolistById?vsid=VSET100167308855&n=7&serviceId=panda&o=desc&of=time&p=";
    int i=2;
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
                xiahua();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(WonBeen);
//                        adapter.notifyDataSetChanged();
//                        won_ptr.refreshComplete();
//                        if(!won_ptr.isLoadMoreEnable()){
//                            won_ptr.setLoadMoreEnable(true);
//
//                        }
//                    }
//                });
            }
        });
        won_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                shangla();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(WonBeen);
//                        adapter.notifyDataSetChanged();
//                        won_ptr.loadMoreComplete(true);
//                    }
//                });
            }
        });
    }

    private void shangla() {
        final String string=url+i;
        OkHttpClient client=new OkHttpClient();
        final Request request=new Request.Builder().url(string).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final  String string1 = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        WonBean notBean=gson.fromJson(string1,WonBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        won_ptr.loadMoreComplete(true);
                        i++;
                    }
                });
            }
        });
    }

    private void xiahua() {
        i=2;
        final String string=url+i;
        OkHttpClient client=new OkHttpClient();
        final Request request=new Request.Builder().url(string).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final  String string1 = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        WonBean notBean=gson.fromJson(string1,WonBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        won_ptr.refreshComplete();
                        if(!won_ptr.isLoadMoreEnable()){
                            won_ptr.setLoadMoreEnable(true);
                            i=3;
                        }
                    }
                });
            }
        });
    }


}
