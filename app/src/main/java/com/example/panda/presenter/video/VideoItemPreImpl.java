package com.example.panda.presenter.video;

import com.example.panda.model.video.VideoItemImpl;
import com.example.panda.model.video.VideoItemModel;
import com.example.panda.view.fragment.video.VideoItemView;
import com.example.panda.view.fragment.video.entity.VideoItemBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/31.
 */

public class VideoItemPreImpl implements VideoItemPre, Observer<VideoItemBean> {
    VideoItemView itemView;
    VideoItemModel itemModel;
    private List<VideoItemBean.VideoBean> video;
    private VideoItemBean.VideosetBean videoset;

    public VideoItemPreImpl(VideoItemView itemView) {
        this.itemView = itemView;
        itemModel=new VideoItemImpl();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(VideoItemBean value) {
        video = value.getVideo();
        videoset = value.getVideoset();
        itemView.onShowTop2(videoset);
        itemView.onShowTop(video);
    }

    @Override
    public void onError(Throwable e) {
    itemView.onError(e.toString());
    }

    @Override
    public void onComplete() {

    }



    @Override
    public void getData(Map<String,String> map) {
        itemModel.RequestGet(map,this);
    }
}
