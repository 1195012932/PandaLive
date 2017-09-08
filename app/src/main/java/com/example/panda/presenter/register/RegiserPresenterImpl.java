package com.example.panda.presenter.register;

import com.example.panda.model.register.RegisterModel;
import com.example.panda.model.register.RegisterModelImpl;
import com.example.panda.view.register.RegisterView;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class RegiserPresenterImpl implements RegisterPresenter {
    private RegisterModel registerModel;
    private RegisterView registerView;


    public RegiserPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void getRegisterPresen(String phone, String imageyz, String JSESSIONID) {
        registerModel.GanCode(this, phone, imageyz, JSESSIONID);
    }

    @Override
    public void setRegisterPresen(String s) {
        registerView.onErrors(s);
    }
}
