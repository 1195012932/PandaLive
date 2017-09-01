package com.example.panda.view.fragment.video;

import com.example.panda.view.fragment.video.entity.VideoItemBean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/31.
 */

public interface VideoItemView {
    void onShowTop(List<VideoItemBean.VideoBean> been);
    void onShowTop2(VideoItemBean.VideosetBean bean);
    void onError(String e);
}
