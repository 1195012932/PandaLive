package com.example.panda.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.view.activity.PersonActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements View.OnClickListener {


    private ImageView preson_sign;
    private XRecyclerView recycler;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
    preson_sign.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        preson_sign = (ImageView) view.findViewById(R.id.preson_sign);
        recycler = (XRecyclerView) view.findViewById(R.id.recycler);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), PersonActivity.class));
    }
}
