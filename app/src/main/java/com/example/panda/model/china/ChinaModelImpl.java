package com.example.panda.model.china;

import com.example.panda.model.entity.chian.ChianBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class ChinaModelImpl implements ChinaModel {

    @Override
    public void RequestGet(Observer<ChianBean> observer) {
        RetrofitUtils.getRetrofitUtils().getChian(observer);
    }
}
