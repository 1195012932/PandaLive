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

public class Record extends BaseFragment implements RecView {
    Handler handler=new Handler();
    private LivePresenter livePresenter;
    String url="http://api.cntv.cn/video/videolistById?vsid=VSET100340574858&n=7&serviceId=panda&o=desc&of=time&p=";
    int i=2;
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
                xiahua();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(RecBeen);
//                        adapter.notifyDataSetChanged();
//                        rec_ptr.refreshComplete();
//                        if(!rec_ptr.isLoadMoreEnable()){
//                            rec_ptr.setLoadMoreEnable(true);
//
//                        }
//                    }
//                });
            }
        });
        rec_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
            shangla();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(RecBeen);
//                        adapter.notifyDataSetChanged();
//                        rec_ptr.loadMoreComplete(true);
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
                        RecBean notBean=gson.fromJson(string1,RecBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        rec_ptr.loadMoreComplete(true);
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
                        RecBean notBean=gson.fromJson(string1,RecBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        rec_ptr.refreshComplete();
                        if(!rec_ptr.isLoadMoreEnable()){
                            rec_ptr.setLoadMoreEnable(true);
                            i--;
                        }
                    }
                });
            }
        });
    }


}
