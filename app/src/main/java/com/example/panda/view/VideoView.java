package com.example.panda.view;

import com.example.panda.model.entity.VideoBean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/24.
 */

public interface VideoView {
    void onShowBigImage(List<VideoBean.BigImgBean> list);
    void OnShowList(List<VideoBean.ListBean> been);
    void onError(String e);
}
