package com.example.panda.view.fragment.livefragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.presenter.live.LivePreImpl;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.view.LiveView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/24.
 */

public class LiveStreaming extends BaseFragment implements LiveView {
    private LivePresenter livePresenter;
    private TabLayout live_tab;
    private ViewPager live_pager;
    private TextView live_text;
    private TextView live_text1;
    private ImageView live_img;
    boolean flag = true;
    List<Fragment> list = new ArrayList<>();
    List<String> lists = new ArrayList<>();
    private ImageView live_img1;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        livePresenter = new LivePreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        getlist();
        getlists();
        live_tab.addTab(live_tab.newTab().setText(lists.get(0)));
        live_tab.addTab(live_tab.newTab().setText(lists.get(1)));
        LivePagerAdapter pager = new LivePagerAdapter(getActivity().getSupportFragmentManager());
        live_pager.setAdapter(pager);
        live_tab.setupWithViewPager(live_pager);
    }

    @Override
    protected void initView(View view) {

        live_tab = (TabLayout) view.findViewById(R.id.live_tab);
        live_pager = (ViewPager) view.findViewById(R.id.live_pager);
        live_text = (TextView) view.findViewById(R.id.live_text);
        live_text1 = (TextView) view.findViewById(R.id.live_text1);
        live_img = (ImageView) view.findViewById(R.id.live_img);
        live_img1=view.findViewById(R.id.live_img1);
        live_text.setVisibility(View.GONE);
        Log.i("s", "wwwwwwwwwwwwwwww");


    }

    @Override
    protected int getLayout() {
        return R.layout.livestreaming;
    }


    @Override
    public void LiveStreaming(final List<LiveStreaing.LiveBean> liveBeen) {
        live_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] ints = {R.mipmap.com_facebook_tooltip_blue_topnub, R.mipmap.com_facebook_tooltip_blue_bottomnub};
                if (flag == true) {
                    live_text.setVisibility(View.VISIBLE);
                    live_text.setText(liveBeen.get(0).getBrief());
                    live_img.setImageResource(ints[0]);
                    flag = false;
                } else {
                    live_img.setImageResource(ints[1]);
                    live_text.setVisibility(View.GONE);
                    flag = true;
                }

            }
        });

    }

    public List<Fragment> getlist() {
        System.out.println("aaaaaaaaaaaaaaaa");
//        list.clear();
        list.add(new BrodCast());
        list.add(new Look());

        return list;
    }

    public List<String> getlists() {
//        lists.clear();
        lists.add("多视角直播");
        lists.add("边看边聊");
        return lists;
    }



    class LivePagerAdapter extends FragmentPagerAdapter {

        public LivePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public long getItemId(int position) {
            return position;
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
