package com.example.panda.model.video;

import com.example.panda.view.fragment.video.entity.VideoTopBean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/29.
 */

public interface VideoTopModel {
    void RequestTop(Map<String,String> map, Observer<VideoTopBean> observer);
}
