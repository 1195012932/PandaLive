package com.example.panda.model.home;

import com.example.panda.model.entity.HomeBean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface HomeModel {
    void RequestGet(Observer<HomeBean> observer);
    void RequestPost(Map<String, String> map, Observer<HomeBean> observer);
}
