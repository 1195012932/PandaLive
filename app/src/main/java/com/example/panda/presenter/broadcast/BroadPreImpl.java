package com.example.panda.presenter.broadcast;

import android.util.Log;

import com.example.panda.model.broadcast.BroadModel;
import com.example.panda.model.broadcast.BroadModelImpl;
import com.example.panda.model.entity.BroadBean;
import com.example.panda.view.BroadView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadPreImpl implements BroadPresenter, Observer<BroadBean>  {
    BroadView broadView;
    BroadModel model;

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
    public void onNext(BroadBean value) {
        BroadBean bean = value;
        BroadBean.DataBean data = bean.getData();
        broadView.onShow(data);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "MLGB"+e.toString());
    }

    @Override
    public void onComplete() {

    }
}
