package com.example.panda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.entity.HomeBean;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/28.
 */

public class LivebroadcastAdapter extends BaseAdapter {
    private List<HomeBean.DataBean.PandaeyeBean.ItemsBean> mlist;
    private Context context;

    public LivebroadcastAdapter(List<HomeBean.DataBean.PandaeyeBean.ItemsBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        LiebroadHolder myHolder;
        if (convertview == null) {
            myHolder = new LiebroadHolder();
            convertview = LayoutInflater.from(context).inflate(R.layout.livebroadcase_adapter, null);
            myHolder.title = convertview.findViewById(R.id.frame_hometext_follow);
            myHolder.content = convertview.findViewById(R.id.frame_follow);
            convertview.setTag(myHolder);
        } else {
            myHolder = (LiebroadHolder) convertview.getTag();
        }
        myHolder.title.setText(mlist.get(position).getBrief());
        myHolder.content.setText(mlist.get(position).getTitle());
        return convertview;
    }

    class LiebroadHolder {
        private TextView title;
        private TextView content;
    }
}
