package com.example.panda.model.home.vitmio;

import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.utils.RetrofitUtils;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class VitmioModelImpl implements VitmioModel {

    @Override
    public void RequstVitmio(Observer<VitmioBean> observer) {
        RetrofitUtils.getRetrofitUtils().getvitmio(observer);
    }
}
