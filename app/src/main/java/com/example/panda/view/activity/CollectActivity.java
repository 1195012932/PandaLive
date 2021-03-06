package com.example.panda.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.view.home.KanDianUtils;
import com.example.panda.view.home.Shoucast;
import com.example.panda.view.home.Shoukandian;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private ImageView shou_img;
    private TabLayout shou_tab;
    private ViewPager shou_pager;
    private TextView shou_text;
    List<String> list = new ArrayList<>();
    List<Fragment> lists = new ArrayList<>();
    private LinearLayout shou_layout;
    boolean flag=true;
    boolean flags=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        initView();
    }

    private void initView() {
        KanDianUtils ss = KanDianUtils.ss();
        KanDianDao look = ss.look(CollectActivity.this);
        List<KanDian> list = look.queryBuilder().build().list();
        Long id = list.get(0).getId();
        shou_img = (ImageView) findViewById(R.id.shou_img);
        shou_tab = (TabLayout) findViewById(R.id.shou_tab);
        shou_text = (TextView) findViewById(R.id.shou_text);
        if (id == null) {
            shou_text.setVisibility(View.GONE);
        } else {
            shou_text.setVisibility(View.VISIBLE);
        }

        shou_pager = (ViewPager) findViewById(R.id.shou_pager);

        shou_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getlist();
        getlists();
        ShouAdapter adapter = new ShouAdapter(getSupportFragmentManager());
        shou_pager.setAdapter(adapter);
        shou_tab.setupWithViewPager(shou_pager);
        shou_layout = (LinearLayout) findViewById(R.id.shou_layout);
        shou_layout.setVisibility(View.GONE);
        shou_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true){
                    shou_layout.setVisibility(View.VISIBLE);
                    shou_text.setText("取消");
                    flag=false;
                }else{
                    shou_layout.setVisibility(View.GONE);
                    shou_text.setText("编辑");
                    flag=true;
                }

            }
        });
    }

    public List<String> getlist() {
        list.add("直播");
        list.add("精彩看点");
        shou_tab.addTab(shou_tab.newTab().setText(list.get(0)));
        shou_tab.addTab(shou_tab.newTab().setText(list.get(1)));
        return list;
    }

    public List<Fragment> getlists() {
        lists.add(new Shoucast());
        lists.add(new Shoukandian());
        return lists;
    }

    class ShouAdapter extends FragmentPagerAdapter {
        public ShouAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return lists.get(position);
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
