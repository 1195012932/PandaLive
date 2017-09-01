package com.example.panda.view.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.adapter.MyBroadAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.home.BroadBean;
import com.example.panda.model.entity.BroadBean2;
import com.example.panda.presenter.broadcast.BroadPreImpl;
import com.example.panda.presenter.broadcast.BroadPreImpl2;
import com.example.panda.presenter.broadcast.BroadPresenter;
import com.example.panda.presenter.broadcast.BroadPresenter2;
import com.example.panda.view.BroadView;
import com.example.panda.view.BroadView2;
import com.example.panda.view.activity.BroadActivity;
import com.example.panda.view.activity.PersonActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 这里是熊猫播报
 * ：黄振通
 */
public class BroadcastFragment extends BaseFragment implements BroadView,BroadView2, View.OnClickListener {

    private BroadPresenter presenter;
    private String title;
    private BroadPresenter bp;
    private BroadPresenter2 bp2;
    private ImageView sign;
    private XRecyclerView xrecy;
    private View view2;
    private ImageView banner;
    private TextView tv1;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        sign.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        bp = new BroadPreImpl(this);
        bp2 = new BroadPreImpl2(this);
        sign = view.findViewById(R.id.broadcast_sign);
        xrecy = view.findViewById(R.id.broad_xrecy);
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        map2.put("param", "http://api.cntv.cn/");
        bp.getData(map);
        bp2.getData2(map);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_broadcast;

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), PersonActivity.class));
    }


    @Override
    public void onShow(BroadBean.DataBean been) {
        final List<BroadBean.DataBean.BigImgBean> bigImg = been.getBigImg();
        String title = bigImg.get(0).getTitle();
        view2 = View.inflate(getActivity(), R.layout.video_header, null);
        banner = view2.findViewById(R.id.banner);
        tv1 = view2.findViewById(R.id.tv_videoTitle);
        tv1.setText(title);
        Glide.with(getActivity()).load(bigImg.get(0).getImage()).error(R.mipmap.panda_sign).into(banner);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadActivity.class);
                intent.putExtra("name", bigImg.get(0).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onhidden() {

    }


    @Override
    public void onShow2(final List<BroadBean2.ListBean> been) {
        Log.e("ListSJ",been.get(0).getTitle());
        final MyBroadAdapter myAdapter = new MyBroadAdapter(getActivity(), been);
        xrecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xrecy.setAdapter(myAdapter);
        xrecy.setLoadingMoreEnabled(false);
        xrecy.addHeaderView(view2);
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                        xrecy.refreshComplete();
                        xrecy.setLoadingMoreEnabled(true);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "铲屎君正在全力录制····", Toast.LENGTH_SHORT).show();
                        xrecy.refreshComplete();
                        xrecy.setLoadingMoreEnabled(false);
                    }
                }, 1);
            }
        });
        myAdapter.setOnItemClickListener(new MyBroadAdapter.Listener() {
            @Override
            public void click(View v, int position) {
                Intent intent = new Intent(getActivity(), BroadActivity.class);
                intent.putExtra("name", been.get(position).getUrl());
                intent.putExtra("title", been.get(position).getTitle());
                intent.putExtra("img", been.get(position).getPicurl());
                intent.putExtra("data", been.get(position).getFocus_date());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onhidden2() {

    }

}
