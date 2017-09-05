package com.example.panda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.BroadBean2;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyBroadAdapter extends RecyclerView.Adapter {
    private final Context context;
    private List<BroadBean2.ListBean> list ;
    private Listener listener;
    private ViewHordle viewHordle;

    public MyBroadAdapter(Context context, List<BroadBean2.ListBean> list3) {
        this.context = context;
        this.list = list3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.broaditem, null);
        viewHordle = new ViewHordle(view);
        return viewHordle;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHordle = (ViewHordle) holder;
        Glide.with(context).load(list.get(position).getPicurl()).into(viewHordle.img);
        viewHordle.name.setText(list.get(position).getTitle());
        long focus_date = list.get(position).getFocus_date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String ss = sdf.format(focus_date);
        viewHordle.data.setText(ss);
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

        ImageView img;
        TextView name,data;

        public ViewHordle(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
        }
    }

    public void setOnItemClickListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void click(View v, int position);
    }
}