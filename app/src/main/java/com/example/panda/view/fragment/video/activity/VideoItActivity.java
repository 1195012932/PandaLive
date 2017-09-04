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
        KanDianUtils ss = KanDianUtils.ss();
        look = ss.look(VideoItActivity.this);
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

    //初始化数据
    private void initData(String url) {

        uri = Uri.parse(url);
        Log.e(TAG, "initData: " + uri);
        item_video.setVideoURI(uri);//设置视频播放地址
        mCustomMediaController.show(5000);
        item_video.setMediaController(mCustomMediaController);
        item_video.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        item_video.requestFocus();
        item_video.setOnInfoListener(this);
        item_video.setOnBufferingUpdateListener(this);
        item_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        item_video.setOnCompletionListener(dismiss);
    }

    //注册在媒体文件播放完毕时调用的回调函数。
    private MediaPlayer.OnCompletionListener dismiss = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            custom_listener.setVisibility(View.VISIBLE);
            if (item_video.isPlaying()) {
                custom_listener.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (item_video.isPlaying()) {
                    item_video.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                item_video.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
        tv_search_btn = (TextView) findViewById(R.id.tv_search_btn);
        tv_search_btn.setOnClickListener(this);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnClickListener(this);
        iv_clear_searchedit = (ImageView) findViewById(R.id.iv_clear_searchedit);
        iv_clear_searchedit.setOnClickListener(this);
        layout_search = (RelativeLayout) findViewById(R.id.layout_search);
        layout_search.setOnClickListener(this);
        common_title_center = (TextView) findViewById(R.id.common_title_center);
        common_title_center.setOnClickListener(this);
        cctv_common_title_center = (TextView) findViewById(R.id.cctv_common_title_center);
        cctv_common_title_center.setOnClickListener(this);
        common_title_right = (TextView) findViewById(R.id.common_title_right);
        common_title_right.setOnClickListener(this);
        common_title_right2 = (TextView) findViewById(R.id.common_title_right2);
        common_title_right2.setOnClickListener(this);
        jieshao = (TextView) findViewById(R.id.jieshao);
        jieshao.setOnClickListener(this);
        video_img = (ImageView) findViewById(R.id.video_img);

        video_img1 = (TextView) findViewById(R.id.video_img1);

        pe_listview_item_detail_bottom = (LinearLayout) findViewById(R.id.pe_listview_item_detail_bottom);
        pe_listview_item_detail_bottom.setOnClickListener(this);
        video_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true){
                    video_img.setImageResource(R.drawable.collect_yes);
//                  look.insert(new KanDian(null,title,data,intExtra,name));
                    flag=false;
                }
            }
        });
    }

    @Override
    public void onShowTop(final List<VideoItemBean.VideoBean> been) {
        videoBeen.addAll(been);
        myAdapter = new MyAdapter(VideoItActivity.this, videoBeen);
        item_listView.setAdapter(myAdapter);
        item_listView.setPullLoadEnable(true);
        item_listView.setPullRefreshEnable(true);
        item_listView.setXListViewListener(new MyXListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }

            @Override
            public void onLoadMore() {
                LoadMore(been);
            }
        });
        item_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                t = videoBeen.get(i).getT();
                 Toast.makeText(VideoItActivity.this, been.get(i).getVid(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onItemClick: " +been.get(i-1).getVid());
                videoTop = new VideoTopPreImpl(VideoItActivity.this);
                Map<String, String> map = new HashMap<>();
                map.put("param", "http://115.182.9.189/api/");
                map.put("pid", been.get(i-1).getVid());
                videoTop.getData(map);
            }
        });
    }

    private void LoadMore(final List<VideoItemBean.VideoBean> been) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                videoBeen.clear();
                page++;

                map.put("param", "http://api.cntv.cn/video/");
                map.put("vsid", id);
                map.put("p", page + "");
                itemPre.getData(map);
                for (int i = 0; i < page; i++) {
                    videoBeen.addAll(been);
                }
                Log.e(TAG, "run: " + videoBeen.size());

                item_listView.stopRefresh();
            }
        });
    }

    private void Refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myAdapter.notifyDataSetChanged();
                item_listView.stopRefresh();
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
    public void onShowTop3(List<VideoTopBean.VideoBean.ChaptersBean> been) {
        Log.e(TAG, "onShowTop3: "+been.get(0).getUrl());
        String urls=been.get(0).getUrl();
        initData(urls);
    }

    @Override
    public void onShowTop2(VideoTopBean.VideoBean been) {

    }

    @Override
    public void OnShow(VideoTopBean videoTopBean) {

    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (item_video != null) {
            item_video.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }
}
