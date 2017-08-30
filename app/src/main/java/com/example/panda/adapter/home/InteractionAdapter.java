package com.example.panda.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.model.entity.home.InteraBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class InteractionAdapter extends RecyclerView.Adapter {
    private List<InteraBean.InteractiveBean> mlist;
    private Context context;

    public InteractionAdapter(List<InteraBean.InteractiveBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_interaction_recy, null);
        LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(linearLayout);
        InteraHolder myHolder = new InteraHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InteraHolder myHolder = (InteraHolder) holder;
        myHolder.nametext.setText(mlist.get(position).getTitle());
        Glide.with(context).load(mlist.get(position).getImage()).into(myHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class InteraHolder extends RecyclerView.ViewHolder {
        private TextView nametext;
        private ImageView imageView;


        public InteraHolder(View itemView) {
            super(itemView);
            nametext = itemView.findViewById(R.id.intere_recy_name);
            imageView = itemView.findViewById(R.id.intere_recy_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onitemclickListerner(getAdapterPosition());
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onitemclickListerner(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
