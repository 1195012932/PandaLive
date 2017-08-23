package com.example.panda.model.home;

import com.example.panda.model.entity.Bean;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface HomeModel {
    void RequestGet(Observer<Bean> observer);
    void RequestPost(Map<String,String> map,Observer<Bean> observer);
}
