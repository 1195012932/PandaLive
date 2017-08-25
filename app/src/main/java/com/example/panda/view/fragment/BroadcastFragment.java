package com.example.panda.view.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.Broad_Bean;
import com.example.panda.presenter.broadcast.BroadPreImpl;
import com.example.panda.presenter.broadcast.BroadPresenter;
import com.example.panda.view.BroadView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 这里是熊猫播报
 * ：黄振通
 */
public class BroadcastFragment extends BaseFragment implements BroadView {

    private BroadPresenter presenter;
    private String title;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        presenter = new BroadPreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        presenter.getBroadData(map);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_broadcast;
    }

    @Override
    public void onShow(List<Broad_Bean.DataBean> been) {

    }

    @Override
    public void onhidden() {

    }
}
