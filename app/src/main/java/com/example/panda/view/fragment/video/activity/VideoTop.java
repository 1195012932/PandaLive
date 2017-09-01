package com.example.panda.view.fragment.video.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.presenter.video.VideoTopPre;
import com.example.panda.presenter.video.VideoTopPreImpl;
import com.example.panda.view.fragment.video.CustomMediaController;
import com.example.panda.view.fragment.video.VideoTopView;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoTop extends AppCompatActivity implements VideoTopView,MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private static final String TAG = "VdieoTop";
    private AudioManager am;
    private boolean state_shouchang = true;
    private String title;

    private String urls;
    private VideoTopPre videoTop;
    private VideoView buffer;
    private ProgressBar probar;
    private TextView download_rate;
    private TextView load_rate;
    private VideoView mVideoView;
    private CustomMediaController mCustomMediaController;
    private ProgressBar pb;
    private TextView downloadRateView;
    private TextView loadRateView;
    private Uri uri;
    private String mapurl;
    String path = "http://vod.cntv.lxdns.com/flash/mp4video60/TMS/2017/06/14/c1777f2df24441f8aa475df6554c232e_h264818000nero_aac32.mp4";//路径
    private LinearLayout custom_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = VideoTop.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        urls = intent.getStringExtra("url_top");
        initView();
    }

    /**
     * 销毁播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
        mVideoView.pause();
        }
    }
    private void initView() {
        videoTop = new VideoTopPreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://115.182.35.91/api/");
        videoTop.getData(map);
        mVideoView = (VideoView) findViewById(R.id.buffer);
        mCustomMediaController =new CustomMediaController(this, mVideoView,this);
        mCustomMediaController.setVideoName(title);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        custom_listener = (LinearLayout) findViewById(R.id.custom_listener2);
        Toast.makeText(VideoTop.this, urls, Toast.LENGTH_SHORT).show();
        System.out.println(urls);

    }
    //初始化数据
    private void initData(String url) {
        Log.e(TAG, "initData: " );
        uri = Uri.parse(url);
        mVideoView.setVideoURI(uri);//设置视频播放地址
        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnCompletionListener(dismiss);
    }
    //注册在媒体文件播放完毕时调用的回调函数。
    private MediaPlayer.OnCompletionListener dismiss=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            custom_listener.setVisibility(View.VISIBLE);
            if (mVideoView.isPlaying()) {
                custom_listener.setVisibility(View.GONE);
            }
        }
    };
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
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
    public void onShowTop(List<VideoTopBean.VideoBean.ChaptersBean> been) {
        String url = been.get(0).getUrl();
        Log.e(TAG, "视频地址1" + url);
        initData(url);
    }

    @Override
    public void onShowTop2(VideoTopBean.VideoBean been) {
        mapurl = been.getChapters()
                .get(0).getUrl();
        Toast.makeText(this, mapurl, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "视频地址2" + mapurl);
    }

    @Override
    public void onError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }
}