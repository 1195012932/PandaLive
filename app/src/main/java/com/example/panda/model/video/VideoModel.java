package com.example.panda.model.video;

import com.example.panda.model.entity.VideoBean;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface VideoModel {
    void RequestGet(Observer<VideoBean> observer);

}
