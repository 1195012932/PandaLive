package com.example.panda.view.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.adapter.ChinaAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.NoScrollViewPager;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.fragment.chinafragment.BaDaLingFeagment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChinaFragment extends BaseFragment {
    private ArrayList<Fragment> list = new ArrayList<Fragment>();
    private ArrayList<String> list1 = new ArrayList<>();
    private TabLayout live_china_indicator;
    private NoScrollViewPager live_china_viewPager;
    private int lastPosition;
    private ChinaAdapter adapter;
    private ImageView live_china_add_channel;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 4; i++) {
            BaDaLingFeagment bdl = new BaDaLingFeagment();
            list.add(bdl);
            list1.add("八达岭");
            live_china_indicator.addTab(live_china_indicator.newTab().setText(list1.get(i)));
        }
        LinearLayout linearLayout = (LinearLayout) live_china_indicator.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));
        adapter = new ChinaAdapter(getActivity().getSupportFragmentManager(), list, list1);
        live_china_viewPager.setAdapter(adapter);

        live_china_indicator.setupWithViewPager(live_china_viewPager);
    }

    @Override
    protected void initView(View view) {
        live_china_indicator = view.findViewById(R.id.live_china_indicator);
        live_china_viewPager = view.findViewById(R.id.live_china_viewPager);
        live_china_add_channel = view.findViewById(R.id.live_china_add_channel);
        ImageView china_sign = view.findViewById(R.id.china_sign);
        china_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
            }
        });
        final View vv = view.findViewById(R.id.view);
        live_china_add_channel.setOnClickListener(new View.OnClickListener() {

            private Button fh;

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "asdfghl", Toast.LENGTH_SHORT).show();
                View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuplayout, null);
                fh = contentView.findViewById(R.id.live_chinnal_select_channel_cancel);

                final PopupWindow popWnd = new PopupWindow(getActivity());
                popWnd.setContentView(contentView);
                popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popWnd.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popWnd.setTouchable(true);
                popWnd.setOutsideTouchable(true);
                popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popWnd.showAsDropDown(vv, 0, 0);
                fh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popWnd.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_china;
    }




}
