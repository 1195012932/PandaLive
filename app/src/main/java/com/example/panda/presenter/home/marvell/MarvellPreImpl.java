package com.example.panda.presenter.home.marvell;

import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.model.home.marvell.MarvellModel;
import com.example.panda.model.home.marvell.MarvellModelImpl;
import com.example.panda.view.home.MarvellView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lenovo on 2017/8/23.
 */

public class MarvellPreImpl implements MarvellPresenter, Observer<MarvellousBean> {
    private MarvellModel marvellModel;
    private MarvellView marvellView;

    public MarvellPreImpl(MarvellView marvellView) {
        this.marvellView = marvellView;
        marvellModel = new MarvellModelImpl();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(MarvellousBean value) {
        MarvellousBean marvellousBean = value;
        marvellView.OnSuccess(marvellousBean);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }


    @Override
    public void marvellousurl(Map<String, String> map) {
        marvellModel.RequestMarvellGet(this);
    }
}
