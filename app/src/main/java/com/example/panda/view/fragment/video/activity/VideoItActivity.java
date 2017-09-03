package com.example.panda.view.fragment.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.presenter.video.VideoItemPre;
import com.example.panda.presenter.video.VideoItemPreImpl;
import com.example.panda.view.fragment.video.VideoItemView;
import com.example.panda.view.fragment.video.adapter.MyAdapter;
import com.example.panda.view.fragment.video.entity.VideoItemBean;
import com.example.panda.view.fragment.xListview.MyXListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.widget.VideoView;

public class VideoItActivity extends AppCompatActivity implements VideoItemView, View.OnClickListener {

    private static final String TAG = "VideoItActivity";
    private TextView common_title_left;
    private TextView item_title;
    private VideoView item_video;
    private TextView item_shou, item_info;
    private MyXListView item_listView;
    private PtrClassicFrameLayout item_ptr;
    private Intent intent;
    private String id;

    private String url = "http://api.cntv.cn/video/videolistById?vsid=" + id + "&n=7&serviceId=panda&o=desc&of=time&p=" + 1;
    private VideoItemPre itemPre;
    private boolean isChecked = true;
    private ImageView lpanda_show;
    private int page = 1;
    private Map<String, String> map;
    List<VideoItemBean.VideoBean> videoBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_it);
        intent = getIntent();
        id = intent.getStringExtra("id");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        common_title_left.setOnClickListener(this);
    }

    private void initData() {

    }


    private void initView() {
        itemPre = new VideoItemPreImpl(this);
        page++;
        map = new HashMap();
        map.put("param", "http://api.cntv.cn/video/");
        map.put("vsid", id);
        map.put("p", 1 + "");
        Log.e("hahahhhhhhhhhhhhhh", "http://api.cntv.cn/video/videolistById?vsid=" + id + "&n=7&serviceId=panda&o=desc&of=time&p=1");

        common_title_left = (TextView) findViewById(R.id.common_title_left);
        item_title = (TextView) findViewById(R.id.item_title);
        item_video = (VideoView) findViewById(R.id.item_video);
        item_shou = (TextView) findViewById(R.id.item_shou);
        item_info = (TextView) findViewById(R.id.item_info);
        lpanda_show = (ImageView) findViewById(R.id.lpanda_show);
        item_listView = (MyXListView) findViewById(R.id.item_listView);
        itemPre.getData(map);
    }

    @Override
    public void onShowTop(final List<VideoItemBean.VideoBean> been) {
        videoBeen.addAll(been);
        final MyAdapter myAdapter = new MyAdapter(VideoItActivity.this, videoBeen);
        item_listView.setAdapter(myAdapter);
        item_listView.setPullLoadEnable(true);
        item_listView.setPullRefreshEnable(true);
        item_listView.setXListViewListener(new MyXListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                        item_listView.stopRefresh();
                    }
                });
            }

            @Override
            public void onLoadMore() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        map.put("param", "http://api.cntv.cn/video/");
                        map.put("vsid", id);
                        map.put("p", page + "");
                        itemPre.getData(map);
                        videoBeen.clear();
                        videoBeen.addAll(been);
                        myAdapter.notifyDataSetChanged();
                        item_listView.stopRefresh();
                    }
                });
            }
        });
    }

    @Override
    public void onShowTop2(final VideoItemBean.VideosetBean bean) {
        Log.e(TAG, "onShowTop2: " + bean.get_$0().getName());
        item_title.setText(bean.get_$0().getName());
        item_shou.setText("首播时间:" + bean.get_$0().getSbsj());

        lpanda_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked == true) {
                    lpanda_show.setImageResource(R.drawable.lpanda_off);
                    item_info.setVisibility(View.GONE);
                    isChecked = false;
                } else {
                    lpanda_show.setImageResource(R.drawable.lpanda_show);
                    item_info.setVisibility(View.VISIBLE);
                    item_info.setText(bean.get_$0().getDesc());
                    isChecked = true;
                }

            }
        });
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
