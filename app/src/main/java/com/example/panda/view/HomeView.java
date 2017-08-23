package com.example.panda.view;

import com.example.panda.model.entity.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface HomeView {
    void onShow(List<Bean.TabBean> been);
    void onhidden();
}
