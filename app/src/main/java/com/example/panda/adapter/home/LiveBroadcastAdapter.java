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
 * Created by XXASUS on 2017/8/28.
 */

public class LiveBroadcastAdapter extends RecyclerView.Adapter {
    private List<HomeBean.DataBean.PandaliveBean.ListBeanX> mlist;
    private Context context;

    public LiveBroadcastAdapter(List<HomeBean.DataBean.PandaliveBean.ListBeanX> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.livebroad_adapter, null);
        LiveBrodHolder myHolder = new LiveBrodHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LiveBrodHolder myHolder = (LiveBrodHolder) holder;
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);
        myHolder.titletext.setText(mlist.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class LiveBrodHolder extends RecyclerView.ViewHolder {
        private TextView titletext;
        private ImageView imageView;

        public LiveBrodHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.livebase_image);
            titletext = itemView.findViewById(R.id.liveitem_titler);
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
        void onClickLiseteners(int position);
    }

    public void setOnClickListenerss(OnClickListeners onClickListener) {
        this.onClickListeners = onClickListener;

    }
}
