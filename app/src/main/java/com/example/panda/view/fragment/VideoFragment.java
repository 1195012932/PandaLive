package com.example.panda.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.panda.R;
import com.example.panda.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {


    private ImageView preson_sign;
    private RecyclerView recycler;
    private PtrClassicFrameLayout ptrClass;

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
        preson_sign = view.findViewById(R.id.preson_sign);
        recycler = view.findViewById(R.id.recycler);
        ptrClass = view.findViewById(R.id.ptrClass);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }


}
