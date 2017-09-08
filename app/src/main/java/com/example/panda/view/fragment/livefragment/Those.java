package com.example.panda.view.fragment.livefragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.panda.view.fragment.video.activity.VideoTop;
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

public class Those extends BaseFragment implements ThoseView {
    private LivePresenter livePresenter;
    List<ThoBean.VideoBean> list = new ArrayList<>();
    private ListView tho_list;
    String url="http://api.cntv.cn/video/videolistById?vsid=VSET100237714751&n=7&serviceId=panda&o=desc&of=time&p=";
    int i=2;
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
        tho_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(getActivity(), VideoTop.class);
                it.putExtra("url_top",list.get(i).getVid());
                it.putExtra("title",list.get(i).getT());
                startActivity(it);
            }
        });
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
            xiahua();

            }
        });
        tho_ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
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
                        ThoBean notBean=gson.fromJson(string1,ThoBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        tho_ptr.loadMoreComplete(true);
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
                        ThoBean notBean=gson.fromJson(string1,ThoBean.class);
                        list.addAll(notBean.getVideo());
                        adapter.notifyDataSetChanged();
                        tho_ptr.refreshComplete();
                        if(!tho_ptr.isLoadMoreEnable()){
                            tho_ptr.setLoadMoreEnable(true);
                            i=3;
                        }
                    }
                });
            }
        });
    }


}
