package com.example.panda.adapter.china;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.panda.model.entity.chian.ChianBean;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/7/29.
 */

public class LiveChinaViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> list;
    private ArrayList<ChianBean.TablistBean> tablist;
    public LiveChinaViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<ChianBean.TablistBean> tablist) {
        super(fm);
        this.list=list;
        this.tablist=tablist;
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
        return tablist.get(position).getTitle();
    }
}
