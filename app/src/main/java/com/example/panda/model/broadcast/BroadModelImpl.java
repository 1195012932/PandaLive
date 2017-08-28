package com.example.panda.model.broadcast;

import com.example.panda.model.entity.BroadBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadModelImpl implements BroadModel {

    @Override
    public void RequestGet(Observer<BroadBean> observer) {
        RetrofitUtils.getRetrofitUtils().getBroads(observer);
    }
}
