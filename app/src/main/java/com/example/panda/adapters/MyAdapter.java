package com.example.panda.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.panda.R;
import com.example.panda.model.entity.Bean;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    ArrayList<Bean> list = new ArrayList<>();
    private Listener listener;
    private ViewHolder holders;

    public MyAdapter(Context context, ArrayList<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View view1 = View.inflate(context, R.layout.video_item1, null);
            holders = new ViewHolder(view1);
        } else {
            View view2 = View.inflate(context, R.layout.video_item1, null);
            holders = new ViewHolder(view2);
        }


        return holders;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

       /* if (holder.image1 != null) {
            holder.image1.setImageResource(list.get(position).getImg());
        }
        if (holder.image2_1 != null) {
            holder.image2_1.setImageResource(list.get(position).getImg());
        }
        if (holder.text1 != null) {
            holder.text1.setText(list.get(position).getName());
        }
        if (holder.image2_2 != null) {
            holder.image2_2.setImageResource(list.get(position).getImg());
        }
        if (holder.text2 != null) {
            holder.text2.setText(list.get(position).getName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(v, position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getItemViewType(int position) {

       /* int k = list.get(position).getFlag();
        if (k == 1) {
            return 1;
        } else {
            return 2;
        }*/
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Banner image1;

        public ViewHolder(View itemView) {
            super(itemView);
            image1 = (Banner) itemView.findViewById(R.id.banner);

        }
    }

    public void setOnItemClickListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void click(View v, int position);
    }
}