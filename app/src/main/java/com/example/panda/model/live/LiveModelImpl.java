package com.example.panda.model.live;

import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class LiveModelImpl implements LiveModel{

    @Override
    public void liveget(Observer<LiveStreaing> observer) {
        RetrofitUtils.getRetrofitUtils().getLives(observer);
    }

    @Override
    public void livestreaming(Map<String, String> map, Observer<LiveStreaing> observer) {
        RetrofitUtils.getRetrofitUtils().getLive(map,observer);
    }
}
