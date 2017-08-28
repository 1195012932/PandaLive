package com.example.panda.presenter.home;

import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.home.HomeModel;
import com.example.panda.model.home.HomeModelImpl;
import com.example.panda.view.HomeView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lenovo on 2017/8/23.
 */

public class HomePreImpl implements HomePresenter, Observer<HomeBean> {
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePreImpl(HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModelImpl();
    }

    /**
     * 观察者
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HomeBean value) {
        HomeBean homeBean = value;
        homeView.OnSuccess(homeBean);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void homeurl(Map<String, String> map) {
        homeModel.RequestGet(this);
    }
}
