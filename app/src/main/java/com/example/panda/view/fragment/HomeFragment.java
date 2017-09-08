package com.example.panda.view.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.panda.utils.OkHttpsManner;
import com.example.panda.view.activity.LivePandaActivity;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.activity.home.Interaction;
import com.example.panda.view.fragment.home.LivePandaBean;
import com.example.panda.view.fragment.video.activity.VideoTop;
import com.example.panda.view.home.HomeView;
import com.example.panda.view.home.MarvellView;
import com.example.panda.view.home.VitmioView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView,
        MarvellView, VitmioView, View.OnClickListener, PandabroadcastAdapter.OnClickLiseteners,
        LiveBroadcastAdapter.OnClickListeners, MarvellousAdpater.OnClickListeners {
    private HomePresenter homePresenter;
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
    private MarvellPresenter marvellPresenter;
    private VitmioPresenter vitmioPresenter;
    private GridView chinalist;
    private ChinaAdapter chinaAdapter;
    private XRecyclerView xRecyclerView;
    private ListView homeb_list;
    private RecyclerView homeb_live_recy;
    private View marvellousheader;
    private RecyclerView homeb_marvellous_recy;
    private ListView homeb_video_list;
    private View header;
    private View broadheader;
    private View livebheader;
    private View videoheader;
    private ImageView interaction;
    private ImageView preson_sign;
    private PandabroadcastAdapter livebroadcastAdapter;
    private LiveBroadcastAdapter liveBroadcastAdapter;
    private MarvellousAdpater marvellousAdpater;
    private VitmioAdapter vitmioAdapter;
    private String cdn_code;
    private String hls1;
    private LivePandaBean livePandaBean;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        showLoadingDialog();
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

        //直播中国
        xRecyclerView = view.findViewById(R.id.home_xrecy);

        header = LayoutInflater.from(getActivity()).inflate(R.layout.home_banner, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        broadheader = LayoutInflater.from(getActivity()).inflate(R.layout.home_broadheader, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        livebheader = LayoutInflater.from(getActivity()).inflate(R.layout.home_livebroadheader, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        marvellousheader = LayoutInflater.from(getActivity()).inflate(R.layout.home_marvellousheader, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        videoheader = LayoutInflater.from(getActivity()).inflate(R.layout.home_videoheader, (ViewGroup) getActivity().findViewById(android.R.id.content), false);

        xRecyclerView.addHeaderView(header);
        xRecyclerView.addHeaderView(broadheader);
        xRecyclerView.addHeaderView(livebheader);
        xRecyclerView.addHeaderView(marvellousheader);
        xRecyclerView.addHeaderView(videoheader);

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress);
        xRecyclerView.setArrowImageView(R.drawable.xlistview_arrowdown);
        xRecyclerView.setLoadingMoreEnabled(false);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chinaAdapter.notifyDataSetChanged();
                        xRecyclerView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.refreshComplete();
                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        //轮播
        frame_home_banner = header.findViewById(R.id.homer_banners);
        //熊猫播报
        homeb_list = broadheader.findViewById(R.id.homeb_list);
        frame_home_iamge = broadheader.findViewById(R.id.homeb_image);
        //直播秀场
        homeb_live_recy = livebheader.findViewById(R.id.homeb_live_recy);
        //精彩一刻
        homeb_marvellous_recy = marvellousheader.findViewById(R.id.homeb_marvellous_recy);
        //滚滚视频
        homeb_video_list = videoheader.findViewById(R.id.homeb_video_list);

    }

    @Override
    protected void initListener() {
        interaction.setOnClickListener(this);
        preson_sign.setOnClickListener(this);
        //
        frame_home_banner.setOnClickListener(this);

    }


    @Override
    protected void loadData() {
        Log.e("TAG", "轮播111111111111111111: ");
        frame_home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        frame_home_banner.setImageLoader(new GlideImageLoader());
        frame_home_banner.setBannerAnimation(Transformer.DepthPage);
        frame_home_banner.isAutoPlay(true);
        frame_home_banner.setDelayTime(1500);
        frame_home_banner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    public void OnSuccess(HomeBean homeBean) {
        HomeBean.DataBean data = homeBean.getData();
        final List<HomeBean.DataBean.BigImgBean> bigImg = data.getBigImg();
        for (HomeBean.DataBean.BigImgBean bannerimages : bigImg) {
            String image = bannerimages.getImage();
            String title = bannerimages.getTitle();
            bannerimage.add(image);
            titleimage.add(title);
            Log.e("TAG", "首页数据: " + bannerimages.getImage());
        }

        frame_home_banner.setImages(bannerimage);
        frame_home_banner.setBannerTitles(titleimage);
        frame_home_banner.start();
        frame_home_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String pid = bigImg.get(position).getPid();
                String title = bigImg.get(position).getTitle();
                Log.e(TAG, "OnBannerClick: " + pid + title);
                Intent bannerintent = new Intent(getActivity(), VideoTop.class);
                bannerintent.putExtra("title", title);
                bannerintent.putExtra("url_top", pid);
                startActivity(bannerintent);
            }
        });
        Log.e("TAG", "轮播22222222222222: ");
        HomeBean.DataBean databean = homeBean.getData();
        HomeBean.DataBean.PandaeyeBean pandaeye = databean.getPandaeye();
        String pandaeyelogo = pandaeye.getPandaeyelogo();
        String title = pandaeye.getTitle();
//        frame_textbroadcast.setText(title);
        Log.e("TAG", "这是标题" + title);
        //熊脑播报
        Glide.with(getActivity()).load(pandaeyelogo).into(frame_home_iamge);
        List<HomeBean.DataBean.PandaeyeBean.ItemsBean> items = pandaeye.getItems();
        livebroadcastAdapter = new PandabroadcastAdapter(items, getActivity());

        homeb_list.setAdapter(livebroadcastAdapter);
        livebroadcastAdapter.notifyDataSetChanged();

        //直播秀场
        final List<HomeBean.DataBean.PandaliveBean.ListBeanX> list = databean.getPandalive().getList();
        liveBroadcastAdapter = new LiveBroadcastAdapter(list, getActivity());
        GridLayoutManager gridmainager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        homeb_live_recy.setAdapter(liveBroadcastAdapter);
        homeb_live_recy.setLayoutManager(gridmainager);
        liveBroadcastAdapter.notifyDataSetChanged();
        liveBroadcastAdapter.setOnClickListenerss(new LiveBroadcastAdapter.OnClickListeners() {
            @Override
            public void onClickLiseteners(int position) {
                final String titless = list.get(position).getTitle();
                String urlsss = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + list.get(position).getId() + "&client=androidapp";
                OkHttpsManner.getInstance().getNetData(urlsss, new OkHttpsManner.CallBacks() {
                    @Override
                    public void getString(String ss) {
                        Gson gson = new Gson();
                        LivePandaBean livePandaBean = gson.fromJson(ss, LivePandaBean.class);
                        cdn_code = livePandaBean.getHls_cdn_info().getCdn_code();
                        hls1 = livePandaBean.getHls_url().getHls1();
                        Log.e(TAG, "getString: " + hls1 + cdn_code);
                        Intent chinaintent = new Intent(getActivity(), LivePandaActivity.class);
                        chinaintent.putExtra("liveurl", hls1 + cdn_code);
                        chinaintent.putExtra("titles", titless);
                        startActivity(chinaintent);
                    }
                });
            }
        });

        //直播中国
        final List<HomeBean.DataBean.ChinaliveBean.ListBean> chinalist1 = databean.getChinalive().getList();
        chinaAdapter = new ChinaAdapter(chinalist1, getActivity());
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setAdapter(chinaAdapter);
        xRecyclerView.setLayoutManager(manager);
        chinaAdapter.notifyDataSetChanged();
        chinaAdapter.setOnClickListeners(new ChinaAdapter.OnClickListeners() {
            @Override
            public void onClickLiseteners(final int pos) {

                Log.e(TAG, "onClickLiseteners: " + chinalist1.get(pos).getTitle());
                String urlsss = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + chinalist1.get(pos).getId() + "&client=androidapp";
                Log.e(TAG, "onClickLiseteners: " + "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + chinalist1.get(pos).getId() + "&client=androidapp");
                OkHttpsManner.getInstance().getNetData(urlsss, new OkHttpsManner.CallBacks() {
                    @Override
                    public void getString(String ss) {
                        Gson gson = new Gson();
                        livePandaBean = gson.fromJson(ss, LivePandaBean.class);
                        cdn_code = livePandaBean.getHls_cdn_info().getCdn_code();
                        hls1 = livePandaBean.getHls_url().getHls1();
                        Log.e(TAG, "getString: " + hls1 + cdn_code);
                        Intent chinaintent = new Intent(getActivity(), LivePandaActivity.class);
                        chinaintent.putExtra("liveurl", hls1 + cdn_code);
                        chinaintent.putExtra("titles", chinalist1.get(pos).getTitle());
                        startActivity(chinaintent);
                    }
                });
            }
        });
        dismissLoadDialog();
    }

    @Override
    public void onError() {

    }

    //滚滚视屏
    @Override
    public void OnvitSucces(VitmioBean vitmioBean) {
        final List<VitmioBean.ListBean> list = vitmioBean.getList();
        vitmioAdapter = new VitmioAdapter(list, getActivity());
        homeb_video_list.setAdapter(vitmioAdapter);
        vitmioAdapter.notifyDataSetChanged();
        homeb_video_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = list.get(i).getTitle();
                String pid = list.get(i).getPid();
                Intent videointent = new Intent(getActivity(), VideoTop.class);
                videointent.putExtra("title", title);
                videointent.putExtra("url_top", pid);
                startActivity(videointent);
            }
        });
    }

    @Override
    public void OnvitErrors() {

    }

    //精彩一刻
    @Override
    public void OnSuccess(MarvellousBean marvellousBean) {
        final List<MarvellousBean.ListBean> list = marvellousBean.getList();
        marvellousAdpater = new MarvellousAdpater(list, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        homeb_marvellous_recy.setLayoutManager(gridLayoutManager);
        homeb_marvellous_recy.setAdapter(marvellousAdpater);
        marvellousAdpater.notifyDataSetChanged();
        marvellousAdpater.setOnClickListeners(new MarvellousAdpater.OnClickListeners() {
            @Override
            public void onClickLiseteners(int pos) {
                String pid = list.get(pos).getPid();
                String title = list.get(pos).getTitle();
                Intent jinIntent = new Intent(getActivity(), VideoTop.class);
                jinIntent.putExtra("title", title);
                jinIntent.putExtra("url_top", pid);
                startActivity(jinIntent);
            }
        });
    }

    @Override
    public void onErrors() {

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
            case R.id.homer_banners:
                Intent bannerintent = new Intent(getActivity(), VideoTop.class);

                startActivity(bannerintent);
                break;
        }
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

    @Override
    public void onclicklistener(int pos) {

    }

    @Override
    public void onClickLiseteners(int position) {

    }
}
