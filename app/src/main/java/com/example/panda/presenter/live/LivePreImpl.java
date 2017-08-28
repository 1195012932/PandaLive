package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.view.LiveView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class LivePreImpl implements LivePresenter, Observer<LiveStreaing> {
    private LiveView liveView;
    private LiveModel liveModel;
    List<LiveStreaing.LiveBean> list = new ArrayList<>();
    public LivePreImpl(LiveView liveView) {
        this.liveView = liveView;
        liveModel = new LiveModelImpl();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(LiveStreaing value) {
        LiveStreaing liveStreaing = value;
        list = liveStreaing.getLive();
        liveView.LiveStreaming(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void url(Map<String, String> map) {
        liveModel.liveget(this);
    }
}
