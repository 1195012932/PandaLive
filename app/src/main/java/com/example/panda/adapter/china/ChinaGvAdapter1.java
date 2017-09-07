package com.example.panda.adapter.china;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.entity.chian.ChianBean;

import java.util.List;

import static android.R.id.list;

/**
 * Created by admin on 2017/8/28.
 */

public class ChinaGvAdapter1 extends BaseAdapter {
    private Context content;
    private ViewHolder holder;
    private List<ChianBean.TablistBean>  dataSourceList;
    public ChinaGvAdapter1(Context content, List<ChianBean.TablistBean> dataSourceList) {
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

    public void swap(int i, int j) {
        if (j < i) {
            ChianBean.TablistBean s = dataSourceList.get(i);
            dataSourceList.add(j, s);
            dataSourceList.remove(i + 1);
        } else if (i < j) {
            ChianBean.TablistBean s = dataSourceList.get(i);
            dataSourceList.add(j + 1, s);
            dataSourceList.remove(i);
        }
    }

    class ViewHolder {
        TextView gv_name;
    }

}
