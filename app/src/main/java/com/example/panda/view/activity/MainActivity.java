package com.example.panda.view.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;
import com.example.panda.utils.FragmentBuilder;
import com.example.panda.view.fragment.BroadcastFragment;
import com.example.panda.view.fragment.ChinaFragment;
import com.example.panda.view.fragment.HomeFragment;
import com.example.panda.view.fragment.LiveFragment;
import com.example.panda.view.fragment.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout frame;
    private RadioButton rb_home;
    private RadioButton rb_live;
    private RadioButton rb_video;
    private RadioButton rb_broad;
    private RadioButton rb_china;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        rb_home.setOnClickListener(this);
        rb_live.setOnClickListener(this);
        rb_video.setOnClickListener(this);
        rb_broad.setOnClickListener(this);
        rb_china.setOnClickListener(this);
        select(0);
    }

    private void select(int i) {
        switch (i) {
            case 0:
                FragmentBuilder.startFragment(HomeFragment.class,R.id.frame,null,true,true);
                break;
            case 1:
                FragmentBuilder.startFragment(LiveFragment.class,R.id.frame,null,true,true);
                break;
            case 2:
                FragmentBuilder.startFragment(VideoFragment.class,R.id.frame,null,true,true);
                break;
            case 3:
                FragmentBuilder.startFragment(BroadcastFragment.class,R.id.frame,null,true,true);
                break;
            case 4:
                FragmentBuilder.startFragment(ChinaFragment.class,R.id.frame,null,true,true);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_live = (RadioButton) findViewById(R.id.rb_live);
        rb_video = (RadioButton) findViewById(R.id.rb_video);
        rb_broad = (RadioButton) findViewById(R.id.rb_broad);
        rb_china = (RadioButton) findViewById(R.id.rb_china);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                select(0);
                break;
            case R.id.rb_live:
                select(1);
                break;
            case R.id.rb_video:
                select(2);
                break;
            case R.id.rb_broad:
                select(3);
                break;
            case R.id.rb_china:
                select(4);
                break;
        }
    }
}
