package com.example.panda.view.fragment.chinafragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.adapter.china.BadalingRecyclerviewAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.chian.ChianBean2;
import com.example.panda.model.entity.chian.ChianVideoBean;
import com.example.panda.utils.OkHttpsManner;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by admin on 2017/8/29.
 */

public class AttractionsFragment extends BaseFragment {

    private List<ChianBean2.LiveBean> list = new ArrayList<>();
    private String url;
    private XRecyclerView att_rv;
    private BadalingRecyclerviewAdapter adapter;
    private ChianVideoBean chianVideoBean;
    private LinearLayout ll;
    private String path = "http://vod.cntv.lxdns.com/flash/mp4video60/TMS/2017/06/14/c1777f2df24441f8aa475df6554c232e_h264818000nero_aac32.mp4";
    private JCVideoPlayer videocontroller1;

    public AttractionsFragment(String url) {
        this.url = url;
    }

    @Override
    protected void loadData() {
        att_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                        att_rv.refreshComplete();
                        att_rv.setLoadingMoreEnabled(true);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "已无更多数据！！！", Toast.LENGTH_SHORT).show();
                        att_rv.refreshComplete();
                        att_rv.setLoadingMoreEnabled(false);

                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initListener() {
        final OkHttpsManner manner = new OkHttpsManner();
        manner.getNetData(url, new OkHttpsManner.CallBacks() {
            @Override
            public void getString(String ss) {
                final Gson gson = new Gson();
                ChianBean2 chianBean2 = gson.fromJson(ss, ChianBean2.class);
                list.addAll(chianBean2.getLive());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        att_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new BadalingRecyclerviewAdapter(list, getContext());
                        att_rv.setAdapter(adapter);
                        adapter.setDj(new BadalingRecyclerviewAdapter.Dj() {
                            @Override
                            public void setDj(View view, int position, final RelativeLayout layout) {
                                OkHttpsManner manner1 = new OkHttpsManner();
                                manner1.getNetData("http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + list.get(position).getId() + "&client=androidapp", new OkHttpsManner.CallBacks() {
                                    @Override
                                    public void getString(String ss) {
                                        Gson gson1 = new Gson();
                                        chianVideoBean = gson1.fromJson(ss, ChianVideoBean.class);
                                        String hds1 = chianVideoBean.getFlv_url().getFlv2();
                                        String cdn_code = chianVideoBean.getFlv_cdn_info().getCdn_code();
                                        Log.e("PFDZ", "----" + hds1 + cdn_code);
                                        View inflate = View.inflate(getActivity(), R.layout.chianvideo, null);
                                        View inflate1 = View.inflate(getActivity(), R.layout.activity_play, null);
                                        View inflate2 = View.inflate(getActivity(), R.layout.jiecao, null);
                                        layout.addView(inflate2);
                                        videocontroller1 = inflate2.findViewById(R.id.videocontroller1);
                                        videocontroller1.setUp(hds1 + cdn_code,"直播中国");

                                    }
                                });
                            }

                        });
                    }
                });

            }
        });
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        att_rv = view.findViewById(R.id.badaling_recyclerview);
        ll = view.findViewById(R.id.ll);


    }

    @Override
    protected int getLayout() {
        return R.layout.bdl_item;
    }


    @Override
    public void onResume() {
        super.onResume();
        list.clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
    }



}
