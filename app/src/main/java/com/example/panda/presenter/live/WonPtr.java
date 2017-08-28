package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.WonBean;
import com.example.panda.view.fragment.livefragment.liveview.WonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class WonPtr implements LivePresenter,Observer<WonBean> {
    private WonView wonView;
    private LiveModel liveModel;
    List<WonBean.VideoBean> list = new ArrayList<>();
    public WonPtr(WonView wonView) {
        this.wonView = wonView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Wonget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }
    @Override
    public void onNext(WonBean value) {
WonBean wonBean=value;
        list.addAll(wonBean.getVideo());
        wonView.WonView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
