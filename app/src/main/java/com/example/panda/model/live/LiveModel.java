package com.example.panda.model.live;

import com.example.panda.model.live.bean.LiveStreaing;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface LiveModel {
    void liveget(Observer<LiveStreaing> observer);
    void livestreaming(Map<String,String> map,Observer<LiveStreaing> observer);
}
