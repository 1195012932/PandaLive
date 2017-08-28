package com.example.panda.view.fragment.livefragment;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.WonBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.WonPtr;
import com.example.panda.view.fragment.livefragment.liveview.WonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Wonderful extends BaseFragment implements WonView {
    private LivePresenter livePresenter;
    List<WonBean.VideoBean> list = new ArrayList<>();
    private ListView won_list;
    private PtrClassicFrameLayout won_ptr;

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
        livePresenter = new WonPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        won_list= (ListView) view.findViewById(R.id.won_list);
        won_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.pro_ptr);
        WonAdapter adapter = new WonAdapter(getActivity(), list);
        won_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.wonderful;
    }

    @Override
    public void WonView(List<WonBean.VideoBean> WonBeen) {
        list.addAll(WonBeen);
    }


}
