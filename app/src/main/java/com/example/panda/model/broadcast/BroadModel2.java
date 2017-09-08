package com.example.panda.model.broadcast;

import com.example.panda.model.entity.BroadBean2;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface BroadModel2 {
    void RequestGet2(Observer<BroadBean2> observer);
}
