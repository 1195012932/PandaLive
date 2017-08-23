package com.example.panda.view.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.Bean;
import com.example.panda.presenter.home.HomePreImpl;
import com.example.panda.presenter.home.HomePresenter;
import com.example.panda.view.HomeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView {

    private HomePresenter presenter;
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

        presenter = new HomePreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        presenter.getData(map);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onShow(List<Bean.TabBean> been) {
        for (Bean.TabBean bean : been) {
            title = bean.getTitle();
            Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onhidden() {

    }
}
