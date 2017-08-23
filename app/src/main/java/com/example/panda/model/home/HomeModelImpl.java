package com.example.panda.model.home;

import com.example.panda.model.entity.Bean;
import com.example.panda.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class HomeModelImpl implements HomeModel{

    @Override
    public void RequestGet(Observer<Bean> observer) {
    RetrofitUtils.getRetrofitUtils().getGet(observer);
    }

    @Override
    public void RequestPost(Map<String, String> map, Observer<Bean> observer) {
        RetrofitUtils.getRetrofitUtils().getPost(map,observer);
    }

}
