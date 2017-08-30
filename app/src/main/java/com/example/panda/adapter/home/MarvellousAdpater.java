package com.example.panda.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.home.MarvellousBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class MarvellousAdpater extends BaseAdapter {
    private List<MarvellousBean.ListBean> mlist;
    private Context context;

    public MarvellousAdpater(List<MarvellousBean.ListBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View concertview, ViewGroup viewGroup) {
        MarvellousHolder myHolder;
        if (concertview == null) {
            myHolder = new MarvellousHolder();
            concertview = LayoutInflater.from(context).inflate(R.layout.marvell_grid, null);
            myHolder.imageView = concertview.findViewById(R.id.marvell_images);
            myHolder.title = concertview.findViewById(R.id.marvell_title);
            myHolder.playtime = concertview.findViewById(R.id.marvell_playtimes);
            myHolder.time = concertview.findViewById(R.id.marvell_times);
            concertview.setTag(myHolder);
        } else {
            myHolder = (MarvellousHolder) concertview.getTag();
        }
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.title.setText(mlist.get(position).getTitle());
        myHolder.playtime.setText(mlist.get(position).getVideoLength());
        myHolder.time.setText(mlist.get(position).getDaytime());
        return concertview;
    }

    class MarvellousHolder {
        private ImageView imageView;
        private TextView title;
        private TextView time;
        private TextView playtime;
    }
}
