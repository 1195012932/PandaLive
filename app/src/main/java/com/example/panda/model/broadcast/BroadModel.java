package com.example.panda.model.broadcast;

import com.example.panda.model.entity.Broad_Bean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface BroadModel {
    void RequestGet(Observer<Broad_Bean> observer);
    void RequestPost(Map<String,String> map, Observer<Broad_Bean> observer);
}
