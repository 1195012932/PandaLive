package com.example.panda.view.fragment.livefragment;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.ProPtr;
import com.example.panda.view.fragment.livefragment.liveview.ProView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Programs extends BaseFragment implements ProView {
    private LivePresenter livePresenter;
    List<ProBean.VideoBean> list = new ArrayList<>();
    private ListView pro_list;
    private PtrClassicFrameLayout pro_ptr;

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
        livePresenter = new ProPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        pro_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.pro_ptr);
        pro_list= (ListView) view.findViewById(R.id.pro_list);
        ProAdapter adapter = new ProAdapter(getActivity(), list);
        pro_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.programs;
    }

    @Override
    public void ProView(List<ProBean.VideoBean> ProBeen) {
        list.addAll(ProBeen);
    }


}
