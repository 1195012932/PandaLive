package com.example.panda.view;

import com.example.panda.model.entity.ChianBean;

import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public interface ChianView {
    void onShow(List<ChianBean.AlllistBean> been);
    void onShow2(List<ChianBean.TablistBean> been);
    void onhidden();
}
