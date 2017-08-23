package com.example.panda.presenter.home;

import com.example.panda.model.entity.Bean;
import com.example.panda.model.home.HomeModel;
import com.example.panda.model.home.HomeModelImpl;
import com.example.panda.view.HomeView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/8/23.
 */

public class HomePreImpl implements HomePresenter, Observer<Bean> {

    HomeView homeView;
    HomeModel model;
    private List<Bean.TabBean> tab;

    public HomePreImpl(HomeView homeView) {
        this.homeView = homeView;
        model = new HomeModelImpl();
    }

    @Override
    public void getData(Map<String, String> map) {
        model.RequestGet(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Bean value) {
        Bean bean = value;
        tab = bean.getTab();
        homeView.onShow(tab);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
