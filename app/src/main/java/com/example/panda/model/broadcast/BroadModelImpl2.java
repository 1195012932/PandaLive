package com.example.panda.model.broadcast;

import com.example.panda.model.entity.BroadBean2;
import com.example.panda.utils.LiveUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadModelImpl2 implements BroadModel2 {

    @Override
    public void RequestGet2(Observer<BroadBean2> observer) {
        LiveUtils.getRetrofitUtils().getBroads2(observer);
    }
}
