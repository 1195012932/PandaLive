package com.example.panda.presenter.home.intera;

import com.example.panda.model.entity.home.InteraBean;
import com.example.panda.model.home.intera.InteraModel;
import com.example.panda.model.home.intera.InteraModelImpl;
import com.example.panda.view.home.InteraView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lenovo on 2017/8/23.
 */

public class InteraPreImpl implements InteraPresenter, Observer<InteraBean> {
    private InteraModel interaModel;
    private InteraView interaView;

    public InteraPreImpl(InteraView interaView) {
        this.interaView = interaView;
        interaModel = new InteraModelImpl();
    }

    /**
     * 观察者
     *
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(InteraBean value) {
        InteraBean interaBean = value;
        interaView.OninteraSuccess(interaBean);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void interaurl(Map<String, String> map) {
        interaModel.Requestintera(this);
    }
}
