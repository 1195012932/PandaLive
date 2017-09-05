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

public class Original extends BaseFragment implements OriginView {
    private LivePresenter livePresenter;
String url="http://api.cntv.cn/video/videolistById?vsid=VSET100332640004&n=7&serviceId=panda&o=desc&of=time&p=";
    List<OriBean.VideoBean> list = new ArrayList<>();
    private ListView ori_list;
    private PtrClassicFrameLayout ori_ptr;
    Handler handler=new Handler();
    private OriAdapter adapter;
    int i=2;
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
                    xiahua();
            }
        });
        ori_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                shangla();
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
                        OriBean notBean=gson.fromJson(string1,OriBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        ori_ptr.loadMoreComplete(true);
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
                        OriBean notBean=gson.fromJson(string1,OriBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        ori_ptr.refreshComplete();
                        if(!ori_ptr.isLoadMoreEnable()){
                            ori_ptr.setLoadMoreEnable(true);
                            i=3;
                        }
                    }
                });
            }
        });
    }


}
