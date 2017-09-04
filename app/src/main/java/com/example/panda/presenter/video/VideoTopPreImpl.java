package com.example.panda.presenter.video;

import com.example.panda.model.video.VideoTopImpl;
import com.example.panda.model.video.VideoTopModel;
import com.example.panda.view.fragment.video.VideoTopView;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/29.
 */
//http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/31/e6c80dae83884dc3a18bbf279b1815b0_h264418000nero_aac32-1.mp4
//http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/28/7d826d24b4e443ad88dd59ad03d50dfe_h264418000nero_aac32.mp4
public class VideoTopPreImpl implements VideoTopPre, Observer<VideoTopBean> {

    VideoTopView videoView;
    VideoTopModel vm;
    private VideoTopBean.VideoBean video;
    List<VideoTopBean.VideoBean> been = new ArrayList<>();
    private List<VideoTopBean.VideoBean.ChaptersBean> chapters;

    public VideoTopPreImpl(VideoTopView videoView) {
        this.videoView = videoView;
        vm = new VideoTopImpl();
    }



    @Override
    public void getData(Map<String, String> map) {
        vm.RequestTop(map,this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(VideoTopBean value) {
        VideoTopBean top = value;
        video = value.getVideo();
        chapters = video.getChapters();
        videoView.onShowTop3(chapters);
        videoView.onShowTop2(video);
        videoView.OnShow(value);
    }

    @Override
    public void onError(Throwable e) {
        videoView.onError(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
