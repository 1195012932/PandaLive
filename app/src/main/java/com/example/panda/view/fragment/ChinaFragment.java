package com.example.panda.view.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.adapter.china.ChinaGvAdapter1;
import com.example.panda.adapter.china.ChinaGvAdapter2;
import com.example.panda.adapter.china.ChinaVpAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.chian.ChianBean;
import com.example.panda.model.live.NoScrollViewPager;
import com.example.panda.presenter.china.ChinaPreImpl;
import com.example.panda.view.ChianView;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.fragment.chinafragment.AttractionsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChinaFragment extends BaseFragment implements ChianView, View.OnClickListener {

    private View vv;
    private TabLayout live_china_indicator;
    private NoScrollViewPager live_china_viewPager;
    private ImageView live_china_add_channel;
    private ImageView china_sign;
    private ChinaPreImpl cp;
    private ArrayList<Fragment> feaglist = new ArrayList<>();
    private String title;
    private ArrayList<String> list1 = new ArrayList<>();
    private ChinaVpAdapter adapter;
    private PopupWindow popWnd;
    private View fh;
    private GridView userGridView;
    private GridView otherGridView;
    private TextView live_china_bianji_left;
    private List<ChianBean.AlllistBean> alllistBean = new ArrayList<>();
    private List<ChianBean.TablistBean> tablistBean = new ArrayList<>();
    private ChinaGvAdapter1 adapter1;
    private ChinaGvAdapter2 adapter2;

    @Override
    public void onShow(List<ChianBean.AlllistBean> been) {
        //三十条数据的集合
        alllistBean.addAll(been);
    }

    @Override
    public void onShow2(List<ChianBean.TablistBean> been) {
        //五条数据的集合
        tablistBean.addAll(been);

        tianjia();
        LinearLayout linearLayout = (LinearLayout) live_china_indicator.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));
        adapter = new ChinaVpAdapter(getActivity().getSupportFragmentManager(), feaglist, list1);
        live_china_viewPager.setAdapter(adapter);
        live_china_indicator.setupWithViewPager(live_china_viewPager);
    }


    @Override
    public void onhidden() {
        Toast.makeText(getActivity(), "数据请求失败!!!", Toast.LENGTH_SHORT).show();
    }

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
        preimpl();
        live_china_indicator = view.findViewById(R.id.live_china_indicator);
        live_china_viewPager = view.findViewById(R.id.live_china_viewPager);
        live_china_add_channel = view.findViewById(R.id.live_china_add_channel);
        china_sign = view.findViewById(R.id.china_sign);
        china_sign.setOnClickListener(this);
        vv = view.findViewById(R.id.view);
        live_china_add_channel.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_china;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.china_sign:
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.live_china_add_channel:
                popup();
                break;
            case R.id.live_chinnal_select_channel_cancel:
                feaglist.clear();
                list1.clear();
                tianjia();
                popWnd.dismiss();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void popup() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuplayout, null);
        fh = contentView.findViewById(R.id.live_chinnal_select_channel_cancel);
        userGridView = contentView.findViewById(R.id.userGridView);
        otherGridView = contentView.findViewById(R.id.otherGridView);
        live_china_bianji_left = contentView.findViewById(R.id.live_china_bianji_left);
        live_china_bianji_left.setOnClickListener(this);
        popWnd = new PopupWindow(getActivity());
        popWnd.setContentView(contentView);
        popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWnd.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popWnd.setTouchable(true);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popWnd.showAsDropDown(vv, 0, 0);
        fh.setOnClickListener(this);
        adapter1 = new ChinaGvAdapter1(getActivity(), tablistBean);
        userGridView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        userGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(tablistBean.size()>=5){
                    String title = tablistBean.get(i).getTitle();
                    String order = tablistBean.get(i).getOrder();
                    String url = tablistBean.get(i).getUrl();
                    String type = tablistBean.get(i).getType();
                    alllistBean.add(new ChianBean.AlllistBean(title, url, type, order));
                    tablistBean.remove(i);
                    adapter2.notifyDataSetChanged();
                    adapter1.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "至少四条数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        adapter2 = new ChinaGvAdapter2(getActivity(), alllistBean);
        otherGridView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        otherGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = alllistBean.get(i).getTitle();
                String order = alllistBean.get(i).getOrder();
                String url = alllistBean.get(i).getUrl();
                String type = alllistBean.get(i).getType();
                tablistBean.add(new ChianBean.TablistBean(title, url, type, order));
                alllistBean.remove(i);
                adapter2.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });

    }

    private void tianjia() {
        for (int i = 0; i < tablistBean.size(); i++) {
            AttractionsFragment bdl = new AttractionsFragment(tablistBean.get(i).getUrl());
            title = tablistBean.get(i).getTitle();
            list1.add(title);
            feaglist.add(bdl);
            Log.e("ASD",list1.toString());
            Log.e("ASD","tablist"+tablistBean.size());
            Log.e("ASD","feaglist"+feaglist.size());
            live_china_indicator.addTab(live_china_indicator.newTab().setText(list1.get(i)));
        }

    }

    private void preimpl() {
        cp = new ChinaPreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        cp.getData(map);
    }
}
