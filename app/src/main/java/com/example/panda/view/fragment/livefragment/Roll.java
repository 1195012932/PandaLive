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

public class Roll extends BaseFragment implements RollView {
    Handler handler=new Handler();
    private LivePresenter livePresenter;
    String url="http://api.cntv.cn/video/videolistById?vsid=VSET100219009515&n=7&serviceId=panda&o=desc&of=time&p=";
    int i=2;
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
                xiahua();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(RollBeen);
//                        adapter.notifyDataSetChanged();
//                        roll_ptr.refreshComplete();
//                        if(!roll_ptr.isLoadMoreEnable()){
//                            roll_ptr.setLoadMoreEnable(true);
//
//                        }
//                    }
//                });
            }
        });
        roll_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
        shangla();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.addAll(RollBeen);
//                        adapter.notifyDataSetChanged();
//                        roll_ptr.loadMoreComplete(true);
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
                        RollBean notBean=gson.fromJson(string1,RollBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        roll_ptr.loadMoreComplete(true);
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
                        RollBean notBean=gson.fromJson(string1,RollBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        roll_ptr.refreshComplete();
                        if(!roll_ptr.isLoadMoreEnable()){
                            roll_ptr.setLoadMoreEnable(true);
                            i=3;
                        }
                    }
                });
            }
        });
    }


}
