package com.example.panda.view.fragment.livefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.live.bean.Brod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/28.
 */

class BordAdapter extends RecyclerView.Adapter{
    private Context context;
    List<Brod.ListBean> list = new ArrayList<>();
    private ViewHoudler viewHoudler;

    public BordAdapter(Context context, List<Brod.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.bord_item, null);
        viewHoudler = new ViewHoudler(inflate);
        return viewHoudler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHoudler= (ViewHoudler) holder;
        viewHoudler.textView.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(viewHoudler.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHoudler extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  textView;
        public ViewHoudler(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.bord_img);
            textView=itemView.findViewById(R.id.bord_text);
        }
    }
}
