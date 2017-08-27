package com.example.panda.model.broadcast;

import com.example.panda.model.entity.BroadBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface BroadModel {
    void RequestGet(Observer<BroadBean> observer);

}
