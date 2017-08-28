package com.example.panda.view.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.NoScrollViewPager;
import com.example.panda.view.fragment.livefragment.LiveStreaming;
import com.example.panda.view.fragment.livefragment.NotLet;
import com.example.panda.view.fragment.livefragment.Original;
import com.example.panda.view.fragment.livefragment.Programs;
import com.example.panda.view.fragment.livefragment.Record;
import com.example.panda.view.fragment.livefragment.Roll;
import com.example.panda.view.fragment.livefragment.TOP;
import com.example.panda.view.fragment.livefragment.Those;
import com.example.panda.view.fragment.livefragment.Wonderful;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends BaseFragment {
    private Toolbar toolbar;
    private TabLayout tab;

    List<Fragment> list = new ArrayList<>();
    List<String> lists = new ArrayList<>();
    private NoScrollViewPager pager;

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
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager= (NoScrollViewPager) view.findViewById(R.id.pager);

        lists.add("直播");
        lists.add("精彩一刻");
        lists.add("当熊不让");
        lists.add("超萌滚滚秀");
        lists.add("熊猫档案");
        lists.add("熊猫TOP榜");
        lists.add("熊猫那些事儿");
        lists.add("特别节目");
        lists.add("原创新闻");
        for (int i = 0; i < lists.size(); i++) {
            tab.addTab(tab.newTab().setText(lists.get(i)));
        }
        getlist();
        PagerAdapters adapter = new PagerAdapters(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_live;
    }
    public List<Fragment> getlist() {
        list.add(new LiveStreaming());
        list.add(new NotLet());
        list.add(new Original());
        list.add(new Programs());
        list.add(new Record());
        list.add(new TOP());
        list.add(new Those());
        list.add(new Wonderful());
        list.add(new Roll());
        return list;
    }


    class PagerAdapters extends FragmentPagerAdapter {
        public PagerAdapters(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return lists.get(position);
        }
    }
}
