package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.RecBean;
import com.example.panda.view.fragment.livefragment.liveview.RecView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class RecPtr implements LivePresenter,Observer<RecBean> {
    private RecView recView;
    private LiveModel liveModel;
    List<RecBean.VideoBean> list = new ArrayList<>();
    public RecPtr(RecView recView) {
        this.recView = recView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Recget(this);
    }
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(RecBean value) {
        RecBean recBean=value;
        list.addAll(recBean.getVideo());
        recView.RecView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
