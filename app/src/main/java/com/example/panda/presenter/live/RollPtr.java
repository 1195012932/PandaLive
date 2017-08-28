package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.view.fragment.livefragment.liveview.RollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class RollPtr implements LivePresenter,Observer<RollBean> {
    private RollView rollView;
    private LiveModel liveModel;
    List<RollBean.VideoBean> list = new ArrayList<>();
    public RollPtr(RollView rollView) {
        this.rollView = rollView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Rollget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(RollBean value) {
        RollBean rollBean=value;
        list.addAll(rollBean.getVideo());
        rollView.RollView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
