package com.example.panda.view.fragment.livefragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.presenter.live.LivePreImpl;
import com.example.panda.presenter.live.LivePresenter;
import com.example.panda.view.LiveView;

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
    private ImageView live_img;
    boolean flag=true;

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

        livePresenter = new LivePreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        livePresenter.url(map);
        live_tab = (TabLayout) view.findViewById(R.id.live_tab);
        live_pager = (ViewPager) view.findViewById(R.id.live_pager);
        live_text = (TextView) view.findViewById(R.id.live_text);
        live_img = (ImageView) view.findViewById(R.id.live_img);
        live_text.setVisibility(View.GONE);

    }

    @Override
    protected int getLayout() {
        return R.layout.livestreaming;
    }


    @Override
    public void LiveStreaming(final List<LiveStreaing.LiveBean> liveBeen) {
        String brief = liveBeen.get(0).getBrief();
        System.out.println("1111111" + brief);
        live_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] ints = {R.drawable.com_facebook_tooltip_blue_topnub,R.drawable.com_facebook_tooltip_blue_bottomnub};
                if(flag==true){
                    live_text.setVisibility(View.VISIBLE);
                    live_text.setText(liveBeen.get(0).getBrief());



                    live_img.setImageResource(ints[0]);
                    flag=false;
                }else{
                    live_img.setImageResource(ints[1]);
                    live_text.setVisibility(View.GONE);
                    flag=true;
                }

            }
        });

    }



}
