package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.view.fragment.livefragment.liveview.ThoseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class ThoPtr implements LivePresenter,Observer<ThoBean> {
    private ThoseView thoseView;
    private LiveModel liveModel;
    List<ThoBean.VideoBean> list = new ArrayList<>();
    public ThoPtr(ThoseView thoseView) {
        this.thoseView = thoseView;
        liveModel=new LiveModelImpl();
    }

    @Override
    public void url(Map<String, String> map) {
        liveModel.Thoget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ThoBean value) {
        ThoBean thoBean=value;
        System.out.println("tho");
        list.addAll(thoBean.getVideo());
        thoseView.ThoView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
