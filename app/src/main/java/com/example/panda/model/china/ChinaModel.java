package com.example.panda.model.china;

import com.example.panda.model.entity.chian.ChianBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface ChinaModel {
    void RequestGet(Observer<ChianBean> observer);

}
