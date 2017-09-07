package com.example.panda.adapter.china;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.entity.chian.ChianBean;

import java.util.List;

/**
 * Created by admin on 2017/8/28.
 */

public class ChinaGvAdapter2 extends BaseAdapter {
    private Context content;
    private ViewHolder holder;
    private List<ChianBean.AlllistBean> dataSourceList;
    private int i1;

    public ChinaGvAdapter2(Context content, List<ChianBean.AlllistBean> dataSourceList) {
        this.content = content;
        this.dataSourceList = dataSourceList;
    }

    @Override
    public int getCount() {
        return dataSourceList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSourceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        i1 = i;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(content, R.layout.gv_item1, null);
            holder.gv_name = view.findViewById(R.id.gv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.gv_name.setText( dataSourceList.get(i).getTitle());
        return view;
    }


    class ViewHolder {
        TextView gv_name;
    }

}
