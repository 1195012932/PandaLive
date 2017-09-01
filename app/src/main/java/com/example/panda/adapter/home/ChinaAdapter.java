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
import com.example.panda.model.entity.HomeBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class ChinaAdapter extends RecyclerView.Adapter {
    private List<HomeBean.DataBean.ChinaliveBean.ListBean> mlist;
    private Context context;

    public ChinaAdapter(List<HomeBean.DataBean.ChinaliveBean.ListBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.china_grid, null);
        ChinaHolder chinaHolder = new ChinaHolder(view);
        return chinaHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChinaHolder chinaHolder = (ChinaHolder) holder;
        Glide.with(context).load(mlist.get(position).getImage()).into(chinaHolder.imageView);
        chinaHolder.titletext.setText(mlist.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    class ChinaHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titletext;

        public ChinaHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.chinabase_image);
            titletext = itemView.findViewById(R.id.chinaitem_titler);
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
