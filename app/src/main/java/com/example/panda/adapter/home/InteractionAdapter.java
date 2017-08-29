package com.example.panda.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class InteractionAdapter extends RecyclerView.Adapter {
    private List<String> mlist;
    private Context context;

    public InteractionAdapter(List<String> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_interaction_recy,null);
        InteraHolder myHolder=new InteraHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InteraHolder myHolder= (InteraHolder) holder;
//        myHolder.nametext.setText();


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class InteraHolder extends RecyclerView.ViewHolder{
        private TextView nametext;
        private ImageView imageView;


        public InteraHolder(View itemView) {
            super(itemView);
        }
    }
}
