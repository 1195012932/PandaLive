package com.example.panda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.ChianBean2;

import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public class ChianLvAdapter extends BaseAdapter {
    boolean isClick = false;
    private Context context;
    private List<ChianBean2.LiveBean> list;
    private ViewHolder holder;

    public ChianLvAdapter(Context context, List<ChianBean2.LiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            holder = new ViewHolder();
            view= View.inflate(context, R.layout.chianlv, null);
            holder.lv_tv= view.findViewById(R.id.lv_tv);
            holder.lv_tv2= view.findViewById(R.id.lv_tv2);
            holder.lv_img= view.findViewById(R.id.lv_img);
            holder.lv_img2= view.findViewById(R.id.lv_img2);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getImage()).into(holder.lv_img);
        holder.lv_tv.setText(list.get(i).getTitle());
        holder.lv_tv2.setText(list.get(i).getBrief());
        holder.lv_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] ints = {R.mipmap.com_facebook_tooltip_blue_topnub,R.mipmap.com_facebook_tooltip_blue_bottomnub};
                if(isClick){
                    holder.lv_tv2.setVisibility(View.GONE);
                    holder.lv_img2.setVisibility(View.VISIBLE);
                }else{
                    holder.lv_tv2.setVisibility(View.VISIBLE);
                }
                isClick = !isClick;
            }
        });
        return view;
    }

    class ViewHolder{
        TextView lv_tv,lv_tv2,lv_img2;
        ImageView lv_img;
    }
}
