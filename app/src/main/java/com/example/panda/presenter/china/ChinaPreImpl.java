package com.example.panda.presenter.china;

import android.util.Log;

import com.example.panda.model.china.ChinaModel;
import com.example.panda.model.china.ChinaModelImpl;
import com.example.panda.model.entity.chian.ChianBean;
import com.example.panda.view.ChianView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class ChinaPreImpl implements ChinaPresenter, Observer<ChianBean> {
    ChianView chianView;
    ChinaModel model;

    public ChinaPreImpl(ChianView chianView) {
        this.chianView = chianView;
        model=new ChinaModelImpl();
    }



    @Override
    public void getData(Map<String, String> map) {
        model.RequestGet(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ChianBean value) {
        ChianBean bean=value;
        List<ChianBean.AlllistBean> alllist = value.getAlllist();
        List<ChianBean.TablistBean> tablist = value.getTablist();
        chianView.onShow(alllist);
        chianView.onShow2(tablist);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("ZZ", "ZBZG"+e.toString());
    }

    @Override
    public void onComplete() {

    }
}
