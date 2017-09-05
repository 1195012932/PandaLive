package com.example.panda.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.view.activity.BroadActivity;
import com.example.panda.view.fragment.video.activity.VideoItActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public class Shoukandian extends Fragment {
    private ImageView kandian_img;
    private RecyclerView kandian_list;
    List<KanDian> list = new ArrayList<>();
    private KanDianDao look;
    private List<KanDian> lists;
    private Long id;

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
        kandian_list = (RecyclerView) inflate.findViewById(R.id.kandian_list);
//        kandian_img.setVisibility(View.GONE);
        if(id==null){
            kandian_img.setVisibility(View.GONE);
        }else {
            kandian_img.setVisibility(View.VISIBLE);
        }

        getlist();
        kandian_list.setLayoutManager(new GridLayoutManager(getActivity(),1));
        KanDianAdapter adapter=new KanDianAdapter(getActivity(),list);
        RecyclerAdapterWithHF hf = new RecyclerAdapterWithHF(adapter);
        kandian_list.setAdapter(hf);
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
               if(list.get(position).getTime()==null){
                   Intent intent = new Intent(getActivity(), BroadActivity.class);
                   intent.putExtra("name", list.get(position).getUrl());
                   startActivity(intent);
               }else{
                   Intent intent = new Intent(getActivity(), VideoItActivity.class);
                   intent.putExtra("id", list.get(position).getUrl());
                   startActivity(intent);
               }

            }
        });

    }


    public List<KanDian> getlist() {
        lists = look.queryBuilder().build().list();
        list.addAll(lists);
        id = list.get(0).getId();
        return list;
    }
}
