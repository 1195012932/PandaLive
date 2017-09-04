package com.example.panda.view.fragment.video;

import com.example.panda.view.fragment.video.entity.VideoTopBean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/29.
 */

public interface VideoTopView {
    void onShowTop(List<VideoTopBean.VideoBean.ChaptersBean> been);
    void onShowTop2(VideoTopBean.VideoBean been);
    void OnShow(VideoTopBean videoTopBean);
    void onError(String e);
}
