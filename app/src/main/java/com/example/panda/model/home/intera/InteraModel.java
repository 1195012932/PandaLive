package com.example.panda.model.home.intera;

import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.home.InteraBean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface InteraModel {
    void Requestintera(Observer<InteraBean> observer);

    void RequestPost(Map<String, String> map, Observer<HomeBean> observer);

}
