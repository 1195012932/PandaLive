package com.example.panda.view.fragment.livefragment;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.RollPtr;
import com.example.panda.view.fragment.livefragment.liveview.RollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Roll extends BaseFragment implements RollView {
    private LivePresenter livePresenter;
    List<RollBean.VideoBean> list = new ArrayList<>();
    private ListView roll_list;
    private PtrClassicFrameLayout roll_ptr;

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
        livePresenter = new RollPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        roll_list= (ListView) view.findViewById(R.id.roll_list);
        roll_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.roll_ptr);
        RollAdapter adapter = new RollAdapter(getActivity(),list);
        roll_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.roll;
    }

    @Override
    public void RollView(List<RollBean.VideoBean> RollBeen) {
        list.addAll(RollBeen);
    }


}
