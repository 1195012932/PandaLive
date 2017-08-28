package com.example.panda.model.home;

import com.example.panda.model.entity.HomeBean;
import com.example.panda.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class HomeModelImpl implements HomeModel {

    @Override
    public void RequestGet(Observer<HomeBean> observer) {
        RetrofitUtils.getRetrofitUtils().getHome(observer);
    }

    @Override
    public void RequestPost(Map<String, String> map, Observer<HomeBean> observer) {
        RetrofitUtils.getRetrofitUtils().getPost(map, observer);
    }
}
