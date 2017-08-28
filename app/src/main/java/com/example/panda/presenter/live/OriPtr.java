package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.OriBean;
import com.example.panda.view.fragment.livefragment.liveview.OriginView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class OriPtr implements LivePresenter,Observer<OriBean> {
    private OriginView originView;
    private LiveModel liveModel;
    List<OriBean.VideoBean> list = new ArrayList<>();
    public OriPtr(OriginView originView) {
        this.originView = originView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Origet(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(OriBean value) {
        OriBean notBean=value;
        list.addAll(notBean.getVideo());
        originView.OriView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
