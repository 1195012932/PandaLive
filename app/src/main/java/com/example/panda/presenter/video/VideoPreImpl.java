package com.example.panda.presenter.video;

import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.video.VideoModel;
import com.example.panda.model.video.VideoModelImpl;
import com.example.panda.view.fragment.video.VideoView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class VideoPreImpl implements VideoPresenter, Observer<VideoBean> {
    VideoView videoView;
    VideoModel vm;
    private List<VideoBean.BigImgBean> bigImg;
    private List<VideoBean.ListBean> list;

    public VideoPreImpl(VideoView videoView) {
        this.videoView = videoView;
        vm = new VideoModelImpl();
    }

    @Override
    public void getData(Map<String, String> map) {
        vm.RequestGet(this);

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(VideoBean value) {
        VideoBean bean = value;
        bigImg = bean.getBigImg();
        list = bean.getList();
        videoView.onShowBigImage(bigImg);
        videoView.OnShowList(list);
    }

    @Override
    public void onError(Throwable e) {
        videoView.onError(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
