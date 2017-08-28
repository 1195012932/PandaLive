package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.NotBean;
import com.example.panda.view.fragment.livefragment.liveview.NotView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/28.
 */

public class NotPtr implements LivePresenter, Observer<NotBean> {

    private NotView notView;
    private LiveModel liveModel;
    List<NotBean.VideoBean> list = new ArrayList<>();

    public NotPtr(NotView notView) {
        this.notView = notView;
        liveModel = new LiveModelImpl();
    }

    @Override
    public void url(Map<String, String> map) {
        liveModel.Notget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(NotBean value) {
        NotBean notBean=value;
        list.addAll(notBean.getVideo());
        notView.NotView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
