package com.example.panda.view.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

class KanDianAdapter extends BaseAdapter{
    private Context context;
    List<KanDian> list = new ArrayList<>();
    private ViewHoudler viewHoudler;

    public KanDianAdapter(Context context, List<KanDian> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.kandian_item,null);
            viewHoudler = new ViewHoudler();
            viewHoudler.imageView= (ImageView) convertView.findViewById(R.id.kan_img);
            viewHoudler.textView= (TextView) convertView.findViewById(R.id.kan_name);
            viewHoudler.textView1= (TextView) convertView.findViewById(R.id.kan_data);
            viewHoudler.textView2= (TextView) convertView.findViewById(R.id.kan_time);
            convertView.setTag(viewHoudler);
        }else{
            viewHoudler = (ViewHoudler) convertView.getTag();
        }
        viewHoudler.textView.setText(list.get(position).getTitle());
        viewHoudler.textView1.setText(list.get(position).getData()+"");
        Glide.with(context).load(list.get(position).getImg()).into(viewHoudler.imageView);
        return convertView;
    }
    class ViewHoudler{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        TextView textView2;
    }
}
