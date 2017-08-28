package com.example.panda.presenter.live;

import com.example.panda.model.live.LiveModel;
import com.example.panda.model.live.LiveModelImpl;
import com.example.panda.model.live.bean.TopBean;
import com.example.panda.view.fragment.livefragment.liveview.TopView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/8/26.
 */

public class TopPtr implements LivePresenter,Observer<TopBean> {
    private TopView topView;
    private LiveModel liveModel;
    List<TopBean.VideoBean> list = new ArrayList<>();
    public TopPtr(TopView topView) {
        this.topView = topView;
        liveModel=new LiveModelImpl();
    }
    @Override
    public void url(Map<String, String> map) {
        liveModel.Topget(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(TopBean value) {
        TopBean topBean=value;
        System.out.println("top");
        list.addAll(topBean.getVideo());
        topView.TopView(list);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
