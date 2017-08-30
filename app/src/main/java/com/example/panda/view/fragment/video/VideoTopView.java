package com.example.panda.view.fragment.video;

import com.example.panda.view.fragment.video.entity.VideoTopBean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/29.
 */

public interface VideoTopView {
    void onShowTop(List<VideoTopBean.VideoBean> been);
    void onError(String e);
}
