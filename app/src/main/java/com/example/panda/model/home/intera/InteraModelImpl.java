package com.example.panda.model.home.intera;

import com.example.panda.model.entity.home.InteraBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class InteraModelImpl implements InteraModel {
    
    @Override
    public void Requestintera(Observer<InteraBean> observer) {
        RetrofitUtils.getRetrofitUtils().getintera(observer);
    }


}
