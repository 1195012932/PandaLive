package com.example.panda.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.view.activity.BroadActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public class Shoukandian extends Fragment {
    private ImageView kandian_img;
    private ListView kandian_list;
    List<KanDian> list = new ArrayList<>();
    private KanDianDao look;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.kandian, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        KanDianUtils ss = KanDianUtils.ss();
        look = ss.look(getActivity());
        kandian_img = (ImageView) inflate.findViewById(R.id.kandian_img);
        kandian_list = (ListView) inflate.findViewById(R.id.kandian_list);
        kandian_img.setVisibility(View.GONE);
        getlist();
        KanDianAdapter adapter=new KanDianAdapter(getActivity(),list);
        kandian_list.setAdapter(adapter);
        kandian_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), BroadActivity.class);
                intent.putExtra("name", list.get(i).getUrl());
                startActivity(intent);
            }
        });
    }

    public List<KanDian> getlist() {
        List<KanDian> lists = look.queryBuilder().build().list();
        list.addAll(lists);
        return list;
    }
}
