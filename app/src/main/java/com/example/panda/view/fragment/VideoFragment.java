package com.example.panda.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.example.panda.R;
import com.example.panda.adapters.MyAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.presenter.video.VideoPreImpl;
import com.example.panda.presenter.video.VideoPresenter;
import com.example.panda.view.VideoView;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.activity.VideoItActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements View.OnClickListener, VideoView, AdapterView.OnItemClickListener {


    private ImageView preson_sign;
    private List<VideoBean.BigImgBean> imgBeen = new ArrayList<>();
    private List<VideoBean.ListBean> listBeen = new ArrayList<>();
    private VideoPresenter vp;
    private ImageView banner;
    private TextView tv_videoTitle;
    private ListView video_list;
    private PtrClassicFrameLayout video_ptr;
    private String title;
    private PtrFrameLayout ptr;
    private MyAdapter myAdapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        preson_sign.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        vp = new VideoPreImpl(this);
        preson_sign = (ImageView) view.findViewById(R.id.preson_sign);
        banner = (ImageView) view.findViewById(R.id.banner);
        tv_videoTitle = (TextView) view.findViewById(R.id.tv_videoTitle);
        video_list = (ListView) view.findViewById(R.id.video_list);
        ptr = (PtrFrameLayout) view.findViewById(R.id.ptr);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        vp.getData(map);


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), PersonActivity.class));
    }

    @Override
    public void onShowBigImage(List<VideoBean.BigImgBean> list) {
        this.imgBeen = list;
        title = list.get(0).getTitle();
        tv_videoTitle.setText(title);
        Glide.with(getActivity()).load(list.get(0).getImage()).error(R.mipmap.panda_sign).into(banner);
    }

    @Override
    public void OnShowList(List<VideoBean.ListBean> been) {
        //this.listBeen = been;
        View view = View.inflate(getActivity(), R.layout.xlistview_header_github, null);
        ptr.setHeaderView(view);
        ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr.autoRefresh();
            }
        }, 1000);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshDown();
            }
        });
        ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                refreshUP();
            }
        });
        myAdapter = new MyAdapter(getActivity(), been);
        video_list.setAdapter(myAdapter);
        video_list.setOnItemClickListener(this);
    }

    private void refreshUP() {

        myAdapter.notifyDataSetChanged();
    }

    private void refreshDown() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "aaaaaaaaa", Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
            }
        }, 2000);

    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        start(getActivity());
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, VideoItActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }
}
