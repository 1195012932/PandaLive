package com.example.panda.model.video;

import com.example.panda.utils.TopUtils;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/29.
 */

public class VideoTopImpl implements VideoTopModel {
    @Override
    public void RequestTop(Observer<VideoTopBean> observer) {
        TopUtils.getRetrofitUtils().getVideoTop(observer);
    }
}
