package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.Brod;
import com.example.panda.view.fragment.livefragment.liveview.BrodView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/28.
 */

public class BrodPtr implements LivePresenter, Observer<Brod> {
    private BrodView brodView;
    private LiveModel liveModel;
    List<Brod.ListBean> list = new ArrayList<>();
    public BrodPtr(BrodView brodView) {
        this.brodView = brodView;
        liveModel = new LiveModelImpl();
    }

    @Override
    public void url(Map<String, String> map) {
        liveModel.brodget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Brod value) {
        Brod brod=value;
        list.addAll(brod.getList());
        brodView.BrodView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
