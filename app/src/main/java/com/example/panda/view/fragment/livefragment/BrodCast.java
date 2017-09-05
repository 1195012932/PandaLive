package com.example.panda.view.fragment.livefragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.Brod;
import com.example.panda.presenter.live.BrodPtr;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.view.fragment.livefragment.liveview.BrodView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/28.
 */

public class BrodCast extends BaseFragment implements BrodView {
    private LivePresenter livePresenter;
    List<Brod.ListBean> list = new ArrayList<>();
    private RecyclerView bord_recy;

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
        livePresenter = new BrodPtr(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        bord_recy=view.findViewById(R.id.bord_recy);
        bord_recy.setLayoutManager(new GridLayoutManager(getActivity(),3));
        BordAdapter adapter=new BordAdapter(getActivity(),list);
        RecyclerAdapterWithHF hf = new RecyclerAdapterWithHF(adapter);
        bord_recy.setAdapter(hf);
    }

    @Override
    protected int getLayout() {
        return R.layout.brodcast;
    }

    @Override
    public void BrodView(List<Brod.ListBean> BrodBeen) {
        String title = BrodBeen.get(0).getTitle();
        System.out.println("99999999"+title);
        list.addAll(BrodBeen);
    }


}
