package com.example.panda.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.home.MarvellousBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class MarvellousAdpater extends RecyclerView.Adapter {
    private List<MarvellousBean.ListBean> mlist;
    private Context context;

    public MarvellousAdpater(List<MarvellousBean.ListBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.marvell_grid, null);

        MarvellousHolder myHolder = new MarvellousHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MarvellousHolder myHolder = (MarvellousHolder) holder;
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.title.setText(mlist.get(position).getTitle());
        myHolder.playtime.setText(mlist.get(position).getVideoLength());
        myHolder.time.setText(mlist.get(position).getDaytime());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MarvellousHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView time;
        private TextView playtime;

        public MarvellousHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.marvell_images);
            title = itemView.findViewById(R.id.marvell_title);
            playtime = itemView.findViewById(R.id.marvell_playtimes);
            time = itemView.findViewById(R.id.marvell_times);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListeners.onClickLiseteners(getAdapterPosition());
                }
            });
        }
    }
    private OnClickListeners onClickListeners;

    public interface OnClickListeners {
        void onClickLiseteners(int pos);
    }

    public void setOnClickListeners(OnClickListeners onClickListener) {
        this.onClickListeners = onClickListener;

    }
}
