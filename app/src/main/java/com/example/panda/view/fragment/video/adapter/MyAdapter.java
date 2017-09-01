package com.example.panda.view.fragment.video.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.panda.view.fragment.video.entity.VideoItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<VideoItemBean.VideoBean> list = new ArrayList<>();
    private ViewHolder holder;

    public MyAdapter(Context context, ArrayList<VideoItemBean.VideoBean> list) {
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
        /*    convertView = View.inflate(context, R.layout.item, null);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);*/
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoItemBean.VideoBean videoBean = list.get(position);
     /*   holder.content.setText(videoBean.get());*/
       // holder.time.setText(videoBean.getTime());
        return convertView;
    }

    static class ViewHolder {
        TextView content;
        TextView time;
    }
}