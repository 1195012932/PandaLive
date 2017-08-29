package com.example.panda.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.adapter.home.ChinaAdapter;
import com.example.panda.adapter.home.LiveBroadcastAdapter;
import com.example.panda.adapter.home.MarvellousAdpater;
import com.example.panda.adapter.home.PandabroadcastAdapter;
import com.example.panda.adapter.home.VitmioAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.model.home.loader.GlideImageLoader;
import com.example.panda.presenter.home.HomePreImpl;
import com.example.panda.presenter.home.HomePresenter;
import com.example.panda.presenter.home.marvell.MarvellPreImpl;
import com.example.panda.presenter.home.marvell.MarvellPresenter;
import com.example.panda.presenter.home.viomio.VitmioPreImpl;
import com.example.panda.presenter.home.viomio.VitmioPresenter;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.activity.home.Interaction;
import com.example.panda.view.home.HomeView;
import com.example.panda.view.home.MarvellView;
import com.example.panda.view.home.VitmioView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener, MarvellView, VitmioView {

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
    private ScrollView fragmenhome_scroll;
    private MarvellPresenter marvellPresenter;
    private GridView frame_marvellousgrid;
    private ListView vidmiolist;
    private VitmioPresenter vitmioPresenter;
    private GridView chinalist;
    private ChinaAdapter chinaAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        homePresenter = new HomePreImpl(this);
        marvellPresenter = new MarvellPreImpl(this);
        vitmioPresenter = new VitmioPreImpl(this);

        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        Map<String, String> marvell = new HashMap<>();
        marvell.put("param", "http://www.ipanda.com/kehuduan/");
        Map<String, String> vitmiomap = new HashMap<>();
        vitmiomap.put("param", "http://www.ipanda.com/kehuduan/");

        homePresenter.homeurl(map);
        marvellPresenter.marvellousurl(marvell);
        vitmioPresenter.vitmiourl(vitmiomap);


        interaction = view.findViewById(R.id.frame_home_Interaction);
        preson_sign = view.findViewById(R.id.frame_home_preson_sign);
        frame_home_banner = view.findViewById(R.id.frame_home_banner);
        frame_textbroadcast = view.findViewById(R.id.frame_textbroadcast);
        frame_home_iamge = view.findViewById(R.id.frame_brocastiamge);
        homelistview = view.findViewById(R.id.home_frag_listview);

        home_frag_gridview = view.findViewById(R.id.home_frag_gridview);
        fragmenhome_scroll = view.findViewById(R.id.fragment_home_scroll);
        //精彩一刻
        frame_marvellousgrid = view.findViewById(R.id.frame_marvellousgrid);
        //滚滚视频
        vidmiolist = view.findViewById(R.id.frame_vidmio);
        //直播中国
        chinalist = view.findViewById(R.id.frame_china);
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
        frame_home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        frame_home_banner.setImageLoader(new GlideImageLoader());
        frame_home_banner.setImages(bannerimage);
        frame_home_banner.setBannerAnimation(Transformer.DepthPage);
        frame_home_banner.setBannerTitles(titleimage);
        frame_home_banner.isAutoPlay(true);
        frame_home_banner.setDelayTime(1500);
        frame_home_banner.setIndicatorGravity(BannerConfig.RIGHT);
        frame_home_banner.start();

        HomeBean.DataBean databean = homeBean.getData();
        HomeBean.DataBean.PandaeyeBean pandaeye = databean.getPandaeye();
        String pandaeyelogo = pandaeye.getPandaeyelogo();
        String title = pandaeye.getTitle();
        frame_textbroadcast.setText(title);
        //熊猫播报
        Glide.with(getActivity()).load(pandaeyelogo).into(frame_home_iamge);
        List<HomeBean.DataBean.PandaeyeBean.ItemsBean> items = pandaeye.getItems();
        PandabroadcastAdapter livebroadcastAdapter = new PandabroadcastAdapter(items, getActivity());
        homelistview.setAdapter(livebroadcastAdapter);
        livebroadcastAdapter.notifyDataSetChanged();

        //直播秀场
        List<HomeBean.DataBean.PandaliveBean.ListBeanX> list = databean.getPandalive().getList();
        LiveBroadcastAdapter liveBroadcastAdapter = new LiveBroadcastAdapter(list, getActivity());
        home_frag_gridview.setAdapter(liveBroadcastAdapter);
        liveBroadcastAdapter.notifyDataSetChanged();
        //直播中国
        List<HomeBean.DataBean.ChinaliveBean.ListBean> chinalist1 = databean.getChinalive().getList();
        chinaAdapter = new ChinaAdapter(chinalist1, getActivity());
        chinalist.setAdapter(chinaAdapter);
        chinaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frame_home_Interaction:
                Toast.makeText(getActivity(), "原创互动", Toast.LENGTH_SHORT).show();
                Intent intent_interaction = new Intent(getActivity(), Interaction.class);
                startActivity(intent_interaction);
                break;
            case R.id.frame_home_preson_sign:
                Toast.makeText(getActivity(), "个人中心", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                break;
        }
    }

    //精彩一刻
    @Override
    public void OnSuccess(MarvellousBean marvellousBean) {
        List<MarvellousBean.ListBean> list = marvellousBean.getList();
        MarvellousAdpater marvellousAdpater = new MarvellousAdpater(list, getActivity());
        frame_marvellousgrid.setAdapter(marvellousAdpater);
        marvellousAdpater.notifyDataSetChanged();
    }

    @Override
    public void onErrors() {

    }
    //滚滚视频

    @Override
    public void OnvitSucces(VitmioBean vitmioBean) {
        List<VitmioBean.ListBean> list = vitmioBean.getList();
        VitmioAdapter vitmioAdapter = new VitmioAdapter(list, getActivity());
        vidmiolist.setAdapter(vitmioAdapter);
        vitmioAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnvitErrors() {

    }


    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        frame_home_banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        frame_home_banner.stopAutoPlay();
    }
}
