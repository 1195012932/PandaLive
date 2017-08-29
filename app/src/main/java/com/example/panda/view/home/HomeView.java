package com.example.panda.view.home;

import com.example.panda.model.entity.HomeBean;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface HomeView {
    void OnSuccess(HomeBean homeBean);

    void onError();
}
