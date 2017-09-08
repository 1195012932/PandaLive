package com.example.panda.adapter.china;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.chian.ChianBean2;

import java.util.List;

/**
 * Created by ASUS on 2017/7/29.
 */

public class BadalingRecyclerviewAdapter extends RecyclerView.Adapter {
    private List<ChianBean2.LiveBean> list;
    private Context context;
    public BadalingRecyclerviewAdapter(List<ChianBean2.LiveBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.china_list_item, null);

        return new ViewHolder(view);
    }

    private boolean flag = true;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewholder = (ViewHolder) holder;
        viewholder.context.setText(list.get(position).getBrief());
        viewholder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(viewholder.img);

        viewholder.radiobt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    viewholder.radiobt.setChecked(flag);
                    viewholder.context.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    viewholder.radiobt.setChecked(flag);
                    viewholder.context.setVisibility(View.GONE);
                    flag = true;
                }
            }
        });
        viewholder.btn_play.setTag(viewholder.rl);
        viewholder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewholder.img.setVisibility(View.GONE);
                viewholder.btn_play.setVisibility(View.GONE);
                RelativeLayout tag = (RelativeLayout) view.getTag();
                //播视频
                dj.setDj(view,position,tag);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView context;
        ImageView img;
        RadioButton radiobt;
        private final ImageView btn_play;
        private final RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.live_china_item_title);
            context = (TextView) itemView.findViewById(R.id.live_china_item_content);
            img = (ImageView) itemView.findViewById(R.id.live_china_item_image);
            radiobt = (RadioButton) itemView.findViewById(R.id.live_china_item_checkbox);
            btn_play = itemView.findViewById(R.id.btn_play);
            rl = itemView.findViewById(R.id.rl);
        }
    }

    public interface Dj {
        void setDj(View view, int position,RelativeLayout layout);

    }
    private Dj dj;

    public void setDj(Dj dj) {
        this.dj = dj;
    }


}
