package com.example.panda.view.fragment.video.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.presenter.video.VideoItemPre;
import com.example.panda.presenter.video.VideoItemPreImpl;
import com.example.panda.presenter.video.VideoTopPreImpl;
import com.example.panda.utils.Netwoke;
import com.example.panda.utils.OkHttpsManner;
import com.example.panda.view.fragment.video.CustomMediaController;
import com.example.panda.view.fragment.video.VideoItemView;
import com.example.panda.view.fragment.video.adapter.MyAdapter;
import com.example.panda.view.fragment.video.entity.VideoItemBean;
import com.example.panda.view.fragment.video.entity.VideoTopBean;
import com.example.panda.view.fragment.xListview.MyXListView;
import com.example.panda.view.home.KanDianUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoItActivity extends AppCompatActivity implements VideoItemView, View.OnClickListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private static final String TAG = "VideoItActivity";
    private TextView common_title_left;
    private TextView item_title;
    private VideoView item_video;
    private TextView item_shou, item_info;
    private MyXListView item_listView;
    private Intent intent;
    private String id;
    private ImageView collect;
    // private String url = "http://api.cntv.cn/video/videolistById?vsid=" + id + "&n=7&serviceId=panda&o=desc&of=time&p=" + 1;
    private VideoItemPre itemPre;
    private boolean isChecked = true;
    private ImageView lpanda_show;
    private int page = 1;
    private Map<String, String> map;
    List<VideoItemBean.VideoBean> videoBeen = new ArrayList<>();
    private MyAdapter myAdapter;
    private Uri uri;
    private CustomMediaController mCustomMediaController;
    private ProgressBar pb;
    private TextView downloadRateView;
    private TextView loadRateView;
    private String t;
    LinearLayout custom_listener;
    private VideoTopPreImpl videoTop;
    boolean flag = true;
    private KanDianDao look;
    private String name;
    private String title;
    private String urls;
    private String time;
    private String img;
    private VideoTopBean videoTopBean;
    private String urlss;
    private String title1;
    private Netwoke netwoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_it);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = VideoItActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        urls = intent.getStringExtra("url");
        time = intent.getStringExtra("time");
        img = intent.getStringExtra("img");

        initView();
        initListener();
    }

    /**
     * 销毁播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (item_video != null) {
            item_video.pause();
        }
    }

    private void initListener() {
        common_title_left.setOnClickListener(this);
    }

    private void initView() {
        KanDianUtils ss = KanDianUtils.ss();
        look = ss.look(VideoItActivity.this);
        itemPre = new VideoItemPreImpl(this);
        loadData(page);

        common_title_left = (TextView) findViewById(R.id.common_title_left);
        item_title = (TextView) findViewById(R.id.item_title);
        item_video = (VideoView) findViewById(R.id.item_video);
        item_shou = (TextView) findViewById(R.id.item_shou);
        item_info = (TextView) findViewById(R.id.item_info);
        lpanda_show = (ImageView) findViewById(R.id.lpanda_show);
        item_listView = (MyXListView) findViewById(R.id.item_listView);
        mCustomMediaController = new CustomMediaController(this, item_video, this);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        custom_listener = (LinearLayout) findViewById(R.id.custom_listener2);
        mCustomMediaController.setVideoName(title1);

        collect = (ImageView) findViewById(R.id.collect);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == true) {
                    collect.setImageResource(R.drawable.collect_yes);
                    look.insert(new KanDian(null, name, title, img, id, time));
                    flag = false;
                } else {
                    KanDian unique = look.queryBuilder().where(KanDianDao.Properties.Title.eq(title)).build().unique();
                    look.delete(unique);
                    collect.setImageResource(R.drawable.collect_no);
                    Toast.makeText(VideoItActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                    flag = true;
                }
            }
        });
    }

    private void loadData(int page) {
        map = new HashMap();
        map.put("param", "http://api.cntv.cn/video/");
        map.put("vsid", id);
        map.put("p", page + "");
        Log.e("hahahhhhhhhhhhhhhh", "http://api.cntv.cn/video/videolistById?vsid=" + id + "&n=7&serviceId=panda&o=desc&of=time&p=" + page);
        itemPre.getData(map);
    }

    //初始化数据
    private void initData(String urlss) {
        uri = Uri.parse(urlss);
        Log.e(TAG, "initData: " + uri);
        item_video.setVideoPath(urlss);//设置视频播放地址
        mCustomMediaController.show(5000);
        item_video.setMediaController(mCustomMediaController);
        item_video.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        item_video.requestFocus();
        item_video.setOnInfoListener(this);
        item_video.setOnBufferingUpdateListener(this);
        View v = View.inflate(VideoItActivity.this, R.layout.mymediacontroller, null);
        int currentPosition = (int) item_video.getCurrentPosition();
        TextView current = (TextView) v.findViewById(R.id.current);
        current.setText(currentPosition + "");
        item_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
       // item_video.setOnCompletionListener(dismiss);
    }

    //注册在媒体文件播放完毕时调用的回调函数。
    private MediaPlayer.OnCompletionListener dismiss = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            custom_listener.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
            if (item_video.isPlaying()) {
                custom_listener.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
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
    }

    @Override
    public void onShowTop(final List<VideoItemBean.VideoBean> been) {
        videoBeen.addAll(been);
        getUrl(0);
        myAdapter = new MyAdapter(VideoItActivity.this, videoBeen);
        item_listView.setAdapter(myAdapter);
        item_listView.setPullLoadEnable(true);
        item_listView.setPullRefreshEnable(true);
        item_listView.setXListViewListener(new MyXListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });

        item_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e(TAG, "onItemClick: " + videoBeen.get(i - 1).getVid());
                getUrl(i - 1);
            }
        });
    }

    private void getUrl(final int i) {
        //getnetwoke(i);
        String mapurl = "http://115.182.9.189/api/getVideoInfoForCBox.do?pid=" + videoBeen.get(i).getVid();
        OkHttpsManner.getInstance().getNetData(mapurl, new OkHttpsManner.CallBacks() {
            @Override
            public void getString(String ss) {
                Gson gson = new Gson();
                videoTopBean = gson.fromJson(ss, VideoTopBean.class);
                urlss = videoTopBean.getVideo().getChapters().get(0).getUrl();
                title1 = videoTopBean.getTitle();
                Log.e(TAG, "getString: " + urlss);
                Toast.makeText(VideoItActivity.this, "当前条目数" + i, Toast.LENGTH_SHORT).show();
                initData(urlss);
                Log.e(TAG, "getString: " + urlss);
            }
        });
    }

    private void loadMore() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                page++;
                loadData(page);
                item_listView.stopRefresh();
                return;
            }
        });
    }

    private void refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                videoBeen.clear();
                page = 1;
                loadData(page);
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
                    lpanda_show.setImageResource(R.drawable.lpanda_show);
                    item_info.setVisibility(View.GONE);
                    isChecked = false;
                } else {
                    lpanda_show.setImageResource(R.drawable.lpanda_off);

                    item_info.setVisibility(View.VISIBLE);
                    item_info.setText(bean.get_$0().getDesc());
                    isChecked = true;
                }
            }
        });
    }

    @Override
    public void onError(String e) {
        Toast.makeText(this, "错误原因:" + e, Toast.LENGTH_SHORT).show();
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

    //判断网络状态
    private void getnetwoke(int i) {
        if (netwoke == null) {
            netwoke = new Netwoke();
        }
        String getnetwoke = netwoke.getnetwoke(this);
        Toast.makeText(this, getnetwoke, Toast.LENGTH_SHORT).show();
        if (!getnetwoke.equals("您现在的网络状态是WIFI")) {
            item_video.pause();
            setNetwork(i);
        }
    }

    private void setNetwork(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("您正在使用移动数据网络,所产生的流量费由当地运营商收取,是否继续?");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                item_video.pause();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                if (i>0){
                    getUrl(i-1);
                }else {
                    getUrl(0);
                }
            }
        });
        builder.create();
        builder.show();
    }

}
