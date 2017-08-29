package com.example.panda.model.video;

import com.example.panda.view.fragment.video.entity.VideoTopBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/29.
 */

public interface VideoTopModel {
    void RequestTop(Observer<VideoTopBean> observer);
}
