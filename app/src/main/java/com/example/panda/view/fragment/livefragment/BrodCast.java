package com.example.panda.view.fragment.livefragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.BroadcastBeen;
import com.example.panda.model.live.bean.Brod;
import com.example.panda.presenter.live.BrodPtr;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.view.fragment.livefragment.liveview.BrodView;
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
 * Created by admin on 2017/8/28.
 */

public class BrodCast extends BaseFragment implements BrodView {
    private LivePresenter livePresenter;
    List<Brod.ListBean> list = new ArrayList<>();
    List<BroadcastBeen.HlsUrlBean> lists = new ArrayList<>();
    List<BroadcastBeen.HlsCdnInfoBean> listss = new ArrayList<>();
    private RecyclerView bord_recy;
    //String string="http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd"+ids+"&client=androidapp";
    private String ids;
    private String title;

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
        livePresenter = new BrodPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        bord_recy=view.findViewById(R.id.bord_recy);
        bord_recy.setLayoutManager(new GridLayoutManager(getActivity(),3));
        BordAdapter adapter=new BordAdapter(getActivity(),list);
        RecyclerAdapterWithHF hf = new RecyclerAdapterWithHF(adapter);
        bord_recy.setAdapter(hf);
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                title = list.get(position).getTitle();
                ids = list.get(position).getId();
                huoqushuju();
            }
        });
    }

    private void huoqushuju() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd"+ids+"&client=androidapp").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson=new Gson();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BroadcastBeen been=gson.fromJson(string,BroadcastBeen.class);
//                        lists.addAll((Collection<? extends BroadcastBeen.HlsUrlBean>) been.getHls_url());
                        String hls3 = been.getHls_url().getHls1();
                        String cdn_code = been.getHls_cdn_info().getCdn_code();
                        String video=hls3+cdn_code;
                        LiveStreaming.MyReceiver myReceiver = new LiveStreaming.MyReceiver();
                        IntentFilter it=new IntentFilter();
                        it.addAction("aaa");
                        getActivity().registerReceiver(myReceiver,it);
                        Intent it1=new Intent();
                        it1.putExtra("name",video);
                        it1.putExtra("title",title);
                        it1.setAction("aaa");
                        getActivity().sendBroadcast(it1);
//                        getActivity().sendBroadcast(intent);
                        Toast.makeText(getActivity(), "发送广播成功"+it1, Toast.LENGTH_SHORT).show();
                        videos(video,title);
                    }
                });
            }
        });
    }
   public void videos(String video,String title){

    };
    @Override
    protected int getLayout() {
        return R.layout.brodcast;
    }

    @Override
    public void BrodView(List<Brod.ListBean> BrodBeen) {
        String title = BrodBeen.get(0).getTitle();
        System.out.println("99999999"+title);
        list.addAll(BrodBeen);
    }


}
