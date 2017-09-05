package com.example.panda.view.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

class KanDianAdapter extends RecyclerView.Adapter{
    private Context context;
    List<KanDian> list = new ArrayList<>();
    private ViewHoudler viewHoudler;

    public KanDianAdapter(Context context, List<KanDian> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
            View inflate = View.inflate(context, R.layout.kandian_item1, null);
            viewHoudler = new ViewHoudler(inflate);
        }else{
            View inflate = View.inflate(context, R.layout.kandian_item, null);
            viewHoudler = new ViewHoudler(inflate);
        }


        return viewHoudler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHoudler= (ViewHoudler) holder;
        if( viewHoudler.textView!=null){
            viewHoudler.textView.setText(list.get(position).getTitle());
            viewHoudler.textView1.setText(list.get(position).getData()+"");
            Glide.with(context).load(list.get(position).getImg()).into(viewHoudler.imageView);
        }
        if(viewHoudler.textView2!=null){
            viewHoudler.textView.setText(list.get(position).getTitle());
            viewHoudler.textView1.setText(list.get(position).getData()+"");
            viewHoudler.textView2.setText(list.get(position).getTime()+"");
            Glide.with(context).load(list.get(position).getImg()).into(viewHoudler.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        String time = list.get(position).getTime();
        if(time==null){
            return 1;
        }else{
            return 2;
        }

    }

    class ViewHoudler extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        TextView textView2;

        public ViewHoudler(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.kan_img);
            textView= (TextView) itemView.findViewById(R.id.kan_name);
            textView1= (TextView) itemView.findViewById(R.id.kan_data);
            textView2= (TextView) itemView.findViewById(R.id.kan_time);
        }
    }
}
