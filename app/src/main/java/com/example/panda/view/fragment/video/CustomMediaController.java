package com.example.panda.view.fragment.video;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.MediaController;

import static android.R.attr.endX;

/**
 * Created by lenovo on 2017/8/30.
 * 自定义视频控制器
 */

public class CustomMediaController extends MediaController {
    private static final int HIDEFRAM = 0;//控制提示窗口的显示
    private static final int SHOW_PROGRESS = 2;
    private final int height;

    private GestureDetector mGestureDetector;
    private ImageButton img_back;//返回按钮
    private TextView mFileName;//文件名
    private io.vov.vitamio.widget.VideoView videoView;
    private Activity activity;
    private Context context;
    private String videoname;//视频名称
    private int controllerWidth = 0;//设置mediaController高度为了使横屏时top显示在屏幕顶端


    private View mVolumeBrightnessLayout;//提示窗口
    private ImageView mOperationBg;//提示图片
    private TextView mOperationTv;//提示文字
    private AudioManager mAudioManager;

    //最大声音
    private int mMaxVolume;
    // 当前声音
    private int mVolume = -1;
    //当前亮度
    private float mBrightness = -1f;
    boolean mInstantSeeking = true;

    //返回监听
    private View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (activity != null) {
                activity.finish();
            }
        }
    };


    private View.OnClickListener scaleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (activity != null) {
                switch (activity.getResources().getConfiguration().orientation) {
                    case Configuration.ORIENTATION_LANDSCAPE://横屏
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        break;
                    case Configuration.ORIENTATION_PORTRAIT://竖屏
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        break;
                }

            }
        }
    };

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long pos;
            switch (msg.what) {
                case HIDEFRAM://隐藏提示窗口
                    mVolumeBrightnessLayout.setVisibility(View.GONE);
                    mOperationTv.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private ImageView mIvScale;
    private SeekBar seek_sheng;
    private ImageView shengying_icon;
    private LinearLayout custom_listener;
    private ImageView shoucang;
    private ImageView share;
    private boolean state_shouchang = true;
    private View byId;
    private boolean progress_turn;
    private SeekBar mediacontroller_seekbar;
    private int progress;
    private int mDuration;
    private boolean mDragging;
    private TextView mCurrentTime;
    private int currentPosition;
    private Handler mHandler = new Handler();
    private TextView current;
    private int index;

    //videoview 用于对视频进行控制的等，activity为了退出
    public CustomMediaController(Context context, io.vov.vitamio.widget.VideoView videoView, Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {
        //此处的   mymediacontroller  为我们自定义控制器的布局文件名称
        View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("mymediacontroller", "layout", getContext().getPackageName()), this);
        v.setMinimumHeight(controllerWidth);
        //获取控件
        img_back = (ImageButton) v.findViewById(getResources().getIdentifier("mediacontroller_top_back", "id", context.getPackageName()));
        mFileName = (TextView) v.findViewById(getResources().getIdentifier("mediacontroller_filename", "id", context.getPackageName()));
        seek_sheng = v.findViewById(getResources().getIdentifier("Shengyin_seekbar", "id", context.getPackageName()));
        shengying_icon = v.findViewById(getResources().getIdentifier("shengying_icon", "id", context.getPackageName()));
        custom_listener = v.findViewById(getResources().getIdentifier("custom_listener", "id", context.getPackageName()));
        //收藏
        shoucang = v.findViewById(getResources().getIdentifier("mediacontroller_favorite", "id", context.getPackageName()));
        //分享
        share = v.findViewById(getResources().getIdentifier("mediacontroller_share", "id", context.getPackageName()));
        //缩放控件
        //mIvScale = (ImageView) v.findViewById(getResources().getIdentifier("mediacontroller_scale", "id", context.getPackageName()));
        current = v.findViewById(getResources().getIdentifier("current", "id", context.getPackageName()));

        //PopupWindo位置
        byId = v.findViewById(getResources().getIdentifier("view", "id", context.getPackageName()));
        mediacontroller_seekbar = v.findViewById(getResources().getIdentifier("mediacontroller_seekbar", "id", context.getPackageName()));
        mCurrentTime = v.findViewById(getResources().getIdentifier("current", "id", context.getPackageName()));
        //当前位置
        currentPosition = (int) videoView.getCurrentPosition();
        //获取总时长
        mDuration = (int) videoView.getDuration();

        current.setText(currentPosition + "");
        if (mFileName != null) {
            mFileName.setText(videoname);
        }
        //声音控制
        mVolumeBrightnessLayout = (RelativeLayout) v.findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) v.findViewById(R.id.operation_bg);
        mOperationTv = (TextView) v.findViewById(R.id.operation_tv);
        mOperationTv.setVisibility(View.GONE);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //注册事件监听
        img_back.setOnClickListener(backListener);
        // mIvScale.setOnClickListener(scaleListener);
        shoucang.setOnClickListener(shouCang);
        share.setOnClickListener(Share);

        //seekBar控制播放音量
        seek_sheng.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int yingliang = seek_sheng.getProgress();
                index = yingliang;
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
                mOperationTv.setText(yingliang + "%");
                shengying_icon.setImageResource(R.drawable.ic_volume_up_white_36dp);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        videoView.setOnCompletionListener(dismiss);
        return v;
    }

    //注册在媒体文件播放完毕时调用的回调函数。
    private MediaPlayer.OnCompletionListener dismiss = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            custom_listener.setVisibility(VISIBLE);
            if (videoView.isPlaying()) {
                custom_listener.setVisibility(GONE);
            }
        }
    };
    // 分享监听
    private OnClickListener Share = new OnClickListener() {
        @Override
        public void onClick(View view) {

            View view1 = View.inflate(activity, R.layout.pop_share_fullscreen, null);
            final PopupWindow window = new PopupWindow(view1, controllerWidth, height);

            window.showAsDropDown(view1);
            final TextView cancelTv = view1.findViewById(R.id.cancelTv);
            window.showAsDropDown(view1);
            cancelTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    window.dismiss();
                }
            });
        }
    };

    //收藏监听
    private OnClickListener shouCang = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (state_shouchang) {
                shoucang.setImageResource(R.drawable.play_fullsrcee_collect_true);
                state_shouchang = false;
                Toast toast = Toast.makeText(activity, "已收藏，请到我的收藏查看", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                shoucang.setImageResource(R.drawable.play_fullsrcee_collect);
                state_shouchang = true;
                Toast toast = Toast.makeText(activity, "已取消收藏", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) return true;
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 手势结束
     */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;
        // 隐藏
        myHandler.removeMessages(HIDEFRAM);
        myHandler.sendEmptyMessageDelayed(HIDEFRAM, 1);
        if (progress_turn) {
            //onFinishSeekBar();
            progress_turn = false;
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        /**
         * 因为使用的是自定义的mediaController 当显示后，mediaController会铺满屏幕，
         * 所以VideoView的点击事件会被拦截，所以重写控制器的手势事件，
         * 将全部的操作全部写在控制器中，
         * 因为点击事件被控制器拦截，无法传递到下层的VideoView，
         * 所以 原来的单机隐藏会失效，作为代替，
         * 在手势监听中onSingleTapConfirmed（）添加自定义的隐藏/显示，
         *
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //当手势结束，并且是单击结束时，控制器隐藏/显示
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            //progress = getProgress();
            return true;
        }

        //滑动事件监听
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            int beginY = (int) e1.getY();
            int beginX = (int) e1.getX();
            Display disp = activity.getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            if (mOldX > windowWidth * 3.0 / 4.0) {// 右边滑动 屏幕 3/4
                onVolumeSlide((mOldY - y) / windowHeight);
            } else if (mOldX < windowWidth * 1.0 / 4.0) {// 左边滑动 屏幕 1/4
                onBrightnessSlide((mOldY - y) / windowHeight);
            } else {//左右滑动
                onSeekTo((endX - beginX) / 20);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
            mOperationTv.setVisibility(VISIBLE);
        }

        index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;
        if (index >= 10) {
            mOperationBg.setImageResource(R.drawable.ic_volume_up_white_36dp);
        } else if (index >= 5 && index < 10) {
            mOperationBg.setImageResource(R.drawable.ic_volume_up_white_36dp);
        } else if (index > 0 && index < 5) {
            mOperationBg.setImageResource(R.drawable.ic_volume_up_white_36dp);
        } else {
            mOperationBg.setImageResource(R.drawable.ic_volume_off_white_36dp);

        }
        if (index == 0) {
            shengying_icon.setImageResource(R.drawable.ic_volume_off_white_36dp);
        } else {
            shengying_icon.setImageResource(R.drawable.ic_volume_up_white_36dp);
        }
        //DecimalFormat    df   = new DecimalFormat("######0.00");
        mOperationTv.setText((int) (((double) index / mMaxVolume) * 100) + "%");
        seek_sheng.setProgress((int) (((double) index / mMaxVolume) * 100));
        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = activity.getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;

            // 显示
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
            mOperationTv.setVisibility(VISIBLE);

        }


        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        activity.getWindow().setAttributes(lpa);

        mOperationTv.setText((int) (lpa.screenBrightness * 100) + "%");
        if (lpa.screenBrightness * 100 >= 90) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 80 && lpa.screenBrightness * 100 < 90) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 70 && lpa.screenBrightness * 100 < 80) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 60 && lpa.screenBrightness * 100 < 70) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 50 && lpa.screenBrightness * 100 < 60) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 40 && lpa.screenBrightness * 100 < 50) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 30 && lpa.screenBrightness * 100 < 40) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 20 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        } else if (lpa.screenBrightness * 100 >= 10 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.ic_brightness_6_white_36dp);
        }

    }

    /*
    *滑动改变播放进度
    *
    *@parampercent
    */

    private void onSeekTo(float percent) {
        //计算并显示 前进后退
        if (!progress_turn) {
            onStartSeekBar();
            progress_turn = true;
        }
        int change = (int) (percent);
        if (change > 0) {
            mOperationBg.setImageResource(R.drawable.player_fast_forward);
        } else {
            mOperationBg.setImageResource(R.drawable.player_fast_backward);
        }
        mOperationTv.setVisibility(View.VISIBLE);

        mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        if (progress + change > 0) {
            if ((progress + change < 1000))
                mOperationTv.setText(setSeekBarChange(progress + change) + "/" + StringUtils.generateTime(videoView.getDuration()));
            else
                mOperationTv.setText(setSeekBarChange(1000) + "/" + StringUtils.generateTime(videoView.getDuration()));
        } else {
            mOperationTv.setText(setSeekBarChange(0) + "/" + StringUtils.generateTime(videoView.getDuration()));

        }
    }


    /**
     * 设置视频文件名
     *
     * @param name
     */
    public void setVideoName(String name) {
        videoname = name;
        if (mFileName != null) {
            mFileName.setText(name);
        }
    }

    /**
     * 隐藏或显示
     */
    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    /**
     * 播放/暂停
     */
    private void playOrPause() {
        if (videoView != null) {
            if (videoView.isPlaying()) {
                videoView.pause();
                //custom_listener.setVisibility(VISIBLE);
            } else {
                videoView.start();
            }
        }
    }

    /*
     * 获取进度条进度
     *
     * @return*/

    public int getProgress() {
        if (mediacontroller_seekbar != null)
            return mediacontroller_seekbar.getProgress();
        return 0;
    }

    /**
     * 改变进度条进度
     *
     * @param progress
     * @return
     */
    public String setSeekBarChange(int progress) {

        if (mediacontroller_seekbar == null) return "";
        mediacontroller_seekbar.setProgress(progress);
        long newposition = (mDuration * progress) / 1000;
        String time = StringUtils.generateTime(newposition);
        if (mInstantSeeking) {
            //设置播放位置
            videoView.seekTo(newposition);
        }
        if (mCurrentTime != null) {
            mCurrentTime.setText(time);
        }
        if (mCurrentTime != null) {
            mCurrentTime.setText(time);
        }
        return time;
    }


    /**
     * 进度条开始改变
     */
    public void onStartSeekBar() {
        if (mAudioManager == null) {
            return;
        }
        mDragging = true;
        show(3600000);
        mHandler.removeMessages(SHOW_PROGRESS);
        if (mInstantSeeking)
            mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        if (mCurrentTime != null) {
            mCurrentTime.setText("");
        }
    }

    /**
     * 进度条变化停止
     */
    public void onFinishSeekBar() {
        if (mediacontroller_seekbar == null) {
            return;
        }

        if (!mInstantSeeking) {
            //设置播放位置
            videoView.seekTo((mDuration * mediacontroller_seekbar.getProgress()) / 1000);
            System.out.println("MediaController-" + (mDuration * mediacontroller_seekbar.getProgress()) / 1000);
            System.out.println("MediaController-" + mediacontroller_seekbar.getProgress());
        }
        if (mCurrentTime != null) {
            mCurrentTime.setText("");
            mCurrentTime.setVisibility(View.GONE);
        }
        int sDefaultTimeout = 1000;
        show(sDefaultTimeout);
        mHandler.removeMessages(SHOW_PROGRESS);
        mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        mDragging = false;
        mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
    }

}
