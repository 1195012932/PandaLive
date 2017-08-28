package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.view.fragment.livefragment.liveview.ProView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class ProPtr implements LivePresenter,Observer<ProBean> {
    private ProView proView;
    private LiveModel liveModel;
    List<ProBean.VideoBean> list = new ArrayList<>();
    public ProPtr(ProView proView) {
        this.proView = proView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Proget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ProBean value) {
        ProBean proBean=value;
        list.addAll(proBean.getVideo());
        proView.ProView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
