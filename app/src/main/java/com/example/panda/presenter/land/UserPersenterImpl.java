package com.example.panda.presenter.land;

import android.util.Log;

import com.example.panda.model.land.PassModel;
import com.example.panda.model.land.PassModelImpl;
import com.example.panda.view.land.view.LandView;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class UserPersenterImpl implements UserPersenter {
    private PassModelImpl passModel;
    private LandView landView;

    public UserPersenterImpl(LandView landView) {
        this.landView = landView;
        passModel = new PassModel();

    }

    @Override
    public void getGusn(String name, String password) {
        Log.e("TAG", "getGusn: 1111111111111");
        passModel.landpass(name, password, this);

        landView.showSuccess();
    }

    @Override
    public void getUser(String string) {
        if (string.equals("成功")) {
            landView.showSuccess();
        } else {
            landView.onError();
        }
    }

}
