package com.example.panda.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by huang on 2017/5/18.
 */

public class ChinaAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private ArrayList<String> list1;
    public ChinaAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> list1) {
        super(fm);
        this.list = list;
        this.list1 = list1;
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
        return list1.get(position);
    }
}
