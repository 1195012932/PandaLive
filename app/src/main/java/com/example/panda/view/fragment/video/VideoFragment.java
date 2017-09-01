package com.example.panda.view.fragment.video;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.adapter.MyAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.presenter.video.VideoPreImpl;
import com.example.panda.presenter.video.VideoPresenter;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.fragment.video.activity.VideoItActivity;
import com.example.panda.view.fragment.video.activity.VideoTop;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements View.OnClickListener, VideoView {


    private ImageView preson_sign;
    private VideoPresenter vp;
    private ImageView banner;
    private TextView tv_videoTitle;
    private String title;

    private XRecyclerView xrecy;
    private View view2;
    private String pid;
    private String urls;


    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        preson_sign.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        vp = new VideoPreImpl(this);

        preson_sign = (ImageView) view.findViewById(R.id.preson_sign);
        xrecy = (XRecyclerView) view.findViewById(R.id.xrecy);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://www.ipanda.com/kehuduan/");
        vp.getData(map);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), PersonActivity.class));
}

    @Override
    public void onShowBigImage(List<VideoBean.BigImgBean> list) {
        view2 = View.inflate(getActivity(), R.layout.video_header, null);
        banner = view2.findViewById(R.id.banner);
        tv_videoTitle = (TextView) view2.findViewById(R.id.tv_videoTitle);
        pid = list.get(0).getPid();
        title = list.get(0).getTitle();

        urls = "http://115.182.35.91/api/getVideoInfoForCBox.do?pid="+pid;
        tv_videoTitle.setText(title);
        Glide.with(getActivity()).load(list.get(0).getImage()).error(R.mipmap.panda_sign).into(banner);
        list.get(0).getPid();
    }

    @Override
    public void OnShowList(final List<VideoBean.ListBean> been) {

        final MyAdapter myAdapter = new MyAdapter(getActivity(), been);
        xrecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xrecy.setAdapter(myAdapter);
        xrecy.setLoadingMoreEnabled(false);
        xrecy.addHeaderView(view2);
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        myAdapter.notifyDataSetChanged();
                        dismissLoadDialog();
                        xrecy.refreshComplete();
                        xrecy.setLoadingMoreEnabled(true);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        showLoadingDialog();
                        Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                        xrecy.refreshComplete();
                        dismissLoadDialog();
                        xrecy.setLoadingMoreEnabled(false);
                    }
                }, 1);
            }
        });
        myAdapter.setOnItemClickListener(new MyAdapter.Listener() {
            @Override
            public void click(View v, int position) {
                String id=been.get(position).getId();
                Intent starter = new Intent(getActivity(), VideoItActivity.class);
               starter.putExtra("id",id);
                startActivity(starter);
                Log.e(TAG, "click: "+id);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), VideoTop.class);
                intent.putExtra("title", title);
                intent.putExtra("url_top", urls);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(String e) {
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

}
