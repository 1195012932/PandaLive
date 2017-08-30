package com.example.panda.view.fragment.livefragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.live.bean.ProBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

class ProAdapter extends BaseAdapter{
    private Context context;
    List<ProBean.VideoBean> list = new ArrayList<>();
    private ViewHoudler viewHoudler;

    public ProAdapter(Context context, List<ProBean.VideoBean> list) {
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
            convertView=View.inflate(context, R.layout.pro_item1,null);
            viewHoudler = new ViewHoudler();
            viewHoudler.imageView= (ImageView) convertView.findViewById(R.id.pro_img1);
            viewHoudler.textView= (TextView) convertView.findViewById(R.id.pro_text);
            viewHoudler.textView1= (TextView) convertView.findViewById(R.id.pro_text1);
            viewHoudler.textView2= (TextView) convertView.findViewById(R.id.pro_text3);
            convertView.setTag(viewHoudler);
        }else{
            viewHoudler= (ViewHoudler) convertView.getTag();
        }
        viewHoudler.textView.setText(list.get(position).getT());
        viewHoudler.textView1.setText(list.get(position).getPtime());
        viewHoudler.textView2.setText(list.get(position).getLen());
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
