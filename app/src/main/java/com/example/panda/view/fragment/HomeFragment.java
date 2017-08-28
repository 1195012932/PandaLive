package com.example.panda.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.adapter.LivebroadcastAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.home.loader.GlideImageLoader;
import com.example.panda.presenter.home.HomePreImpl;
import com.example.panda.presenter.home.HomePresenter;
import com.example.panda.view.HomeView;
import com.example.panda.view.activity.PersonActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {

    private HomePresenter homePresenter;
    private ImageView interaction, preson_sign;
    private Banner frame_home_banner;
    private TextView frame_textbroadcast;
    private ImageView frame_home_iamge;
    private TextView frame_hometext_follow;
    private TextView frame_follow;
    private TextView frag_newborn;
    private TextView frame_home_newborn;
    private ListView homelistview;
    private ArrayList<String> bannerimage = new ArrayList<>();
    private ArrayList<String> titleimage = new ArrayList<>();
    private GridView home_frag_gridview;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        homePresenter = new HomePreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        homePresenter.homeurl(map);
        interaction = view.findViewById(R.id.frame_home_Interaction);
        preson_sign = view.findViewById(R.id.frame_home_preson_sign);
        frame_home_banner = view.findViewById(R.id.frame_home_banner);
        frame_textbroadcast = view.findViewById(R.id.frame_textbroadcast);
        frame_home_iamge = view.findViewById(R.id.frame_brocastiamge);
        homelistview = view.findViewById(R.id.home_frag_listview);

        home_frag_gridview = view.findViewById(R.id.home_frag_gridview);

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        interaction.setOnClickListener(this);
        preson_sign.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void OnSuccess(HomeBean homeBean) {
        HomeBean.DataBean data = homeBean.getData();
        List<HomeBean.DataBean.BigImgBean> bigImg = data.getBigImg();
        for (HomeBean.DataBean.BigImgBean bannerimages : bigImg) {
            String image = bannerimages.getImage();
            String title = bannerimages.getTitle();
            bannerimage.add(image);
            titleimage.add(title);
        }
        frame_home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        frame_home_banner.setImageLoader(new GlideImageLoader());
        frame_home_banner.setImages(bannerimage);
        frame_home_banner.setBannerAnimation(Transformer.DepthPage);
        frame_home_banner.setBannerTitles(titleimage);
        frame_home_banner.isAutoPlay(true);
        frame_home_banner.setDelayTime(1500);
        frame_home_banner.setIndicatorGravity(BannerConfig.CENTER);
        frame_home_banner.start();
        HomeBean.DataBean.PandaeyeBean pandaeye = homeBean.getData().getPandaeye();
        String pandaeyelogo = pandaeye.getPandaeyelogo();
        String title = pandaeye.getTitle();
        frame_textbroadcast.setText(title);
        Glide.with(getActivity()).load(pandaeyelogo).into(frame_home_iamge);
        List<HomeBean.DataBean.PandaeyeBean.ItemsBean> items = pandaeye.getItems();
        LivebroadcastAdapter livebroadcastAdapter = new LivebroadcastAdapter(items, getActivity());
        homelistview.setAdapter(livebroadcastAdapter);
        livebroadcastAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frame_home_Interaction:
                Toast.makeText(getActivity(), "原创互动", Toast.LENGTH_SHORT).show();

                break;
            case R.id.frame_home_preson_sign:
                Toast.makeText(getActivity(), "个人中心", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                break;
        }
    }
}
