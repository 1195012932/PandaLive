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
 * Created by XXASUS on 2017/8/28.
 */

public class LiveBroadcastAdapter extends BaseAdapter {
    private List<HomeBean.DataBean.PandaliveBean.ListBeanX> mlist;
    private Context context;

    public LiveBroadcastAdapter(List<HomeBean.DataBean.PandaliveBean.ListBeanX> mlist, Context context) {
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
        LiveBrodHolder myHolder;
        if (convertview == null) {
            myHolder = new LiveBrodHolder();
            convertview = LayoutInflater.from(context).inflate(R.layout.livebroad_adapter, null);
            myHolder.imageView = convertview.findViewById(R.id.livebase_image);
            myHolder.titletext = convertview.findViewById(R.id.liveitem_titler);
            convertview.setTag(myHolder);
        } else {
            myHolder = (LiveBrodHolder) convertview.getTag();
        }
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.titletext.setText(mlist.get(position).getTitle());
        return convertview;
    }

    class LiveBrodHolder {
        private TextView titletext;
        private ImageView imageView;
    }
}
