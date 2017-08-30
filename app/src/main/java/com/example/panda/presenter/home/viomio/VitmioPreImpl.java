package com.example.panda.presenter.home.viomio;

import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.model.home.vitmio.VitmioModel;
import com.example.panda.model.home.vitmio.VitmioModelImpl;
import com.example.panda.view.home.VitmioView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lenovo on 2017/8/23.
 */

public class VitmioPreImpl implements VitmioPresenter, Observer<VitmioBean> {
    private VitmioModel vitmioModel;
    private VitmioView vitmioView;

    public VitmioPreImpl(VitmioView vitmioView) {
        this.vitmioView = vitmioView;
        vitmioModel = new VitmioModelImpl();
    }

    /**
     * 观察者
     *
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(VitmioBean value) {
        VitmioBean vitmioBean = value;
        vitmioView.OnvitSucces(vitmioBean);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void vitmiourl(Map<String, String> map) {
        vitmioModel.RequstVitmio(this);
    }
}
