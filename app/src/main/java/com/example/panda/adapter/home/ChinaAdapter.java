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
import com.example.panda.model.entity.HomeBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class ChinaAdapter extends BaseAdapter {
    private List<HomeBean.DataBean.ChinaliveBean.ListBean> mlist;
    private Context context;

    public ChinaAdapter(List<HomeBean.DataBean.ChinaliveBean.ListBean> mlist, Context context) {
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
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ChinaHolder myHolder;
        if (convertview == null) {
            myHolder = new ChinaHolder();
            convertview = LayoutInflater.from(context).inflate(R.layout.china_grid, null);
            myHolder.imageView = convertview.findViewById(R.id.chinabase_image);
            myHolder.titletext = convertview.findViewById(R.id.chinaitem_titler);
            convertview.setTag(myHolder);
        } else {
            myHolder = (ChinaHolder) convertview.getTag();
        }
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.titletext.setText(mlist.get(position).getTitle());
        return convertview;
    }

    class ChinaHolder {
        private ImageView imageView;
        private TextView titletext;

    }
}
