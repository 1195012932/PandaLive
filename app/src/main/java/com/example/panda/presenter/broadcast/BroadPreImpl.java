package com.example.panda.presenter.broadcast;

import com.example.panda.model.broadcast.BroadModel;
import com.example.panda.model.broadcast.BroadModelImpl;
import com.example.panda.model.entity.Broad_Bean;
import com.example.panda.view.BroadView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadPreImpl implements BroadPresenter, Observer<Broad_Bean> {
    BroadView broadView;
    BroadModel model;
    private List<Broad_Bean.DataBean> data;

    public BroadPreImpl(BroadView broadView) {
        this.broadView = broadView;
        model = new BroadModelImpl();
    }

    @Override
    public void getData(Map<String, String> map) {
        model.RequestGet(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Broad_Bean value) {
        Broad_Bean bean = value;
        data = bean.getData();
        broadView.onShow(data);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
