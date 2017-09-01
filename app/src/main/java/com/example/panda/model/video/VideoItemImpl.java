package com.example.panda.model.video;

import com.example.panda.utils.LiveUtils;
import com.example.panda.view.fragment.video.entity.VideoItemBean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/31.
 */

public class VideoItemImpl implements VideoItemModel {



    @Override
    public void RequestGet(Map<String,String> map,Observer<VideoItemBean> observer) {
        LiveUtils.getRetrofitUtils().getVideoItem2(map,observer);
    }
}
