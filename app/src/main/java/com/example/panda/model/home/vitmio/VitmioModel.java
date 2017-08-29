package com.example.panda.model.home.vitmio;

import com.example.panda.model.entity.home.VitmioBean;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface VitmioModel {

    void RequstVitmio(Observer<VitmioBean> observer);
}
