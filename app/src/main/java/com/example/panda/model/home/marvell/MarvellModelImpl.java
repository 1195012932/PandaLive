package com.example.panda.model.home.marvell;

import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class MarvellModelImpl implements MarvellModel {


    @Override
    public void RequestMarvellGet(Observer<MarvellousBean> observer) {
        RetrofitUtils.getRetrofitUtils().getmarvell(observer);
    }
}
