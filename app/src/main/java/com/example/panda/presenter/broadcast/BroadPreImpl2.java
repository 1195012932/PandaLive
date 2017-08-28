package com.example.panda.presenter.broadcast;

import android.util.Log;

import com.example.panda.model.broadcast.BroadModel2;
import com.example.panda.model.broadcast.BroadModelImpl2;
import com.example.panda.model.entity.BroadBean2;
import com.example.panda.view.BroadView2;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class BroadPreImpl2 implements BroadPresenter2, Observer<BroadBean2>  {
    BroadView2 broadView;
    BroadModel2 model;
    private List<BroadBean2.ListBean> list;

    public BroadPreImpl2(BroadView2 broadView) {
        this.broadView = broadView;
        model=new BroadModelImpl2();
    }

    @Override
    public void getData2(Map<String, String> map) {
        model.RequestGet2(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BroadBean2 value) {
        BroadBean2 bean2=value;
        list = bean2.getList();
        broadView.onShow2(list);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("CW","aaaaaa"+e.toString());
    }

    @Override
    public void onComplete() {

    }
}
