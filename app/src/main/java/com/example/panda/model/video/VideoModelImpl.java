package com.example.panda.model.video;

import com.example.panda.model.entity.VideoBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class VideoModelImpl implements VideoModel {

    @Override
    public void RequestGet(Observer<VideoBean> observer) {
        RetrofitUtils.getRetrofitUtils().getVideos(observer);
    }
}
