package com.example.panda.presenter.land.yzm;

import com.example.panda.model.land.yzm.YzmModel;
import com.example.panda.model.land.yzm.YzmModelImpl;
import com.example.panda.view.land.view.HearView;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class YZMPersenterImol implements YZMPersenter {
    private HearView hearView;
    private YzmModel yzmModel;

    public YZMPersenterImol(HearView hearView) {
        this.hearView = hearView;
        yzmModel = new YzmModelImpl();
    }

    @Override
    public void getHear(String name) {
        yzmModel.getUserId(name, this);
        hearView.onSuccess();
    }
}
