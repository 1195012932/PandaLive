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
import com.example.panda.model.entity.home.VitmioBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class VitmioAdapter extends BaseAdapter {
    private List<VitmioBean.ListBean> mlist;
    private Context context;

    public VitmioAdapter(List<VitmioBean.ListBean> mlist, Context context) {
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
        VitHolder myHolder;
        if (convertview == null) {
            myHolder = new VitHolder();
            convertview = LayoutInflater.from(context).inflate(R.layout.vitmio_list, null);
            myHolder.imageView = convertview.findViewById(R.id.vitmio_images);
            myHolder.titlertext = convertview.findViewById(R.id.vitmio_title);
            myHolder.vitlength = convertview.findViewById(R.id.vitmio_playtimes);
            myHolder.timetext = convertview.findViewById(R.id.marvell_times);
            convertview.setTag(myHolder);
        } else {
            myHolder = (VitHolder) convertview.getTag();
        }
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.titlertext.setText(mlist.get(position).getTitle());
        myHolder.vitlength.setText(mlist.get(position).getVideoLength());
        myHolder.timetext.setText(mlist.get(position).getDaytime());

        return convertview;
    }

    class VitHolder {
        private ImageView imageView;
        private TextView titlertext, vitlength, timetext;
    }
}
