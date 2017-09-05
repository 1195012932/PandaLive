package com.example.panda.view.fragment.livefragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.live.bean.LookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/29.
 */

class LookAdapter extends BaseAdapter{
    private Context context;
    List<LookBean> list = new ArrayList<>();
    private ViewHoudler viewHoudler;

    public LookAdapter(Context context, List<LookBean> list) {
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
            convertView=View.inflate(context, R.layout.look_item1,null);
            viewHoudler = new ViewHoudler();
            viewHoudler.textView= (TextView) convertView.findViewById(R.id.look_text);
            viewHoudler.textView1= (TextView) convertView.findViewById(R.id.look_text1);
            viewHoudler.textView2= (TextView) convertView.findViewById(R.id.look_text4);
            viewHoudler.textView3= (TextView) convertView.findViewById(R.id.look_text5);
            convertView.setTag(viewHoudler);
        }else{
            viewHoudler = (ViewHoudler) convertView.getTag();
        }
        Long id = list.get(position).getId();
        viewHoudler.textView.setText(list.get(position).getName());
        viewHoudler.textView1.setText(id+"楼");
        viewHoudler.textView2.setText(list.get(position).getTitle());
        viewHoudler.textView3.setText(list.get(position).getTime());
        return convertView;
    }
    class ViewHoudler{
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }
}
