package com.example.panda.view.fragment.livefragment;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.presenter.live.ThoPtr;
import com.example.panda.view.fragment.livefragment.liveview.ThoseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class Those extends BaseFragment implements ThoseView {
    private LivePresenter livePresenter;
    List<ThoBean.VideoBean> list = new ArrayList<>();
    private ListView tho_list;
    private PtrClassicFrameLayout tho_ptr;

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
        livePresenter = new ThoPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        tho_list= (ListView) view.findViewById(R.id.tho_list);
        tho_ptr= (PtrClassicFrameLayout) view.findViewById(R.id.tho_ptr);
        ThoAdapter adapter = new ThoAdapter(getActivity(), list);
        tho_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.those;
    }

    @Override
    public void ThoView(List<ThoBean.VideoBean> ThoBeen) {
        list.addAll(ThoBeen);
    }


}
