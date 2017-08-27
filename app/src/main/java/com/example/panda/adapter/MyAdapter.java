package com.example.panda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.VideoBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private final Context context;
    private List<VideoBean.ListBean> list = new ArrayList<>();
    private Listener listener;

    public MyAdapter(Context context, List<VideoBean.ListBean> list3) {
        this.context = context;
        this.list = list3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.video_item2, null);
        ViewHordle holder = new ViewHordle(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHordle holder1 = (ViewHordle) holder;
        holder1.time.setText(list.get(position).getVideoLength());
        holder1.name.setText(list.get(position).getTitle());
        holder1.info.setText(list.get(position).getBrief());
        Glide.with(context)
                .load(list.get(position).getImage())
                .error(R.drawable._no_img)
                .into(holder1.tu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHordle extends RecyclerView.ViewHolder {
        ImageView tu;
        TextView time, name, info;

        public ViewHordle(View itemView) {
            super(itemView);
            tu = (ImageView) itemView.findViewById(R.id.video_icon);
            name = (TextView) itemView.findViewById(R.id.video_name);
            info = (TextView) itemView.findViewById(R.id.video_info);
            time = (TextView) itemView.findViewById(R.id.video_time);
        }
    }

    public void setOnItemClickListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void click(View v, int position);
    }
}