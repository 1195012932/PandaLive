package com.example.panda.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.VideoBean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<VideoBean.ListBean> listBeen;
    private ViewHolder holder;

    public MyAdapter(Context context, List<VideoBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.video_item2, null);
            holder.title = (TextView) convertView.findViewById(R.id.video_name);
            holder.info = (TextView) convertView.findViewById(R.id.video_info);
            holder.time = (TextView) convertView.findViewById(R.id.video_time);
            holder.icon = (ImageView) convertView.findViewById(R.id.video_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoBean.ListBean bean = listBeen.get(position);
        holder.title.setText(bean.getTitle());
        holder.info.setText(bean.getBrief());
        holder.time.setText(bean.getVideoLength());
        Glide.with(context).load(bean.getImage()).error(R.mipmap.panda_sign).into(holder.icon);
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView info;
        TextView time;
        ImageView icon;
    }
}