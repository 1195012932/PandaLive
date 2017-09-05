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

public class NotLet extends BaseFragment implements NotView {
    private LivePresenter livePresenter;
    List<NotBean.VideoBean> list = new ArrayList<>();
    private ListView not_list;
    private PtrClassicFrameLayout not_ptr;
    private NotAdapter adapter;
    Handler handler=new Handler();
    int i=2;
    String url="http://api.cntv.cn/video/videolistById?vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=";
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
                        NotBean notBean=gson.fromJson(string1,NotBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        not_ptr.loadMoreComplete(true);
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
                        list.clear();
                        NotBean notBean=gson.fromJson(string1,NotBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        not_ptr.refreshComplete();
                        if(!not_ptr.isLoadMoreEnable()){
                            not_ptr.setLoadMoreEnable(true);
                            i=3;
                        }
                    }
                });
                    }
                });

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
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.clear();
//                        list.addAll(NotBeen);
//                        adapter.notifyDataSetChanged();
//
//                    }
//                });
            }
        });
        not_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                shangla();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(NotBeen);
//                        adapter.notifyDataSetChanged();
//                        not_ptr.loadMoreComplete(true);
//                    }
//                });
            }
        });
    }



}
