package com.example.panda.model.home.marvell;

import com.example.panda.model.entity.home.MarvellousBean;

import io.reactivex.Observer;

/**
 * Created by XXASUS on 2017/8/29.
 */

public interface MarvellModel {
    void RequestMarvellGet(Observer<MarvellousBean> observer);
}
