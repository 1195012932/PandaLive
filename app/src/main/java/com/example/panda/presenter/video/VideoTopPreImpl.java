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
        vm.RequestTop(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(VideoTopBean value) {
        VideoTopBean top = value;
        video = top.getVideo();
        chapters = video.getChapters();

        videoView.onShowTop(chapters);
        videoView.onShowTop2(top.getVideo());
    }

    @Override
    public void onError(Throwable e) {
        videoView.onError(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
