package com.example.panda.view.fragment.video.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.view.fragment.video.entity.VideoItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<VideoItemBean.VideoBean> list = new ArrayList<>();
    private ViewHolder holder;

    public MyAdapter(Context context, List<VideoItemBean.VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.videoitem_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.videoitem_name);
            holder.time = (TextView) convertView.findViewById(R.id.videoitem_time);
            holder.icon=(ImageView) convertView.findViewById(R.id.videoitem_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoItemBean.VideoBean videoBean = list.get(position);
        holder.name.setText(videoBean.getT());
        holder.time.setText(videoBean.getLen());
        Glide.with(context).load(videoBean.getImg()).error(R.drawable.def_no_play).into(holder.icon);
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView time;
        ImageView icon;
    }
}