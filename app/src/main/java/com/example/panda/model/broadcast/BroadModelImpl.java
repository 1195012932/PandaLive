package com.example.panda.model.broadcast;

import com.example.panda.model.entity.Broad_Bean;
import com.example.panda.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadModelImpl implements BroadModel {

    @Override
    public void RequestGet(Observer<Broad_Bean> observer) {
        RetrofitUtils.getRetrofitUtils().getBroadGet(observer);
    }

    @Override
    public void RequestPost(Map<String, String> map, Observer<Broad_Bean> observer) {
        RetrofitUtils.getRetrofitUtils().getPost(map,observer);
    }
}
