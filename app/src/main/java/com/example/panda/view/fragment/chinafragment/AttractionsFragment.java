package com.example.panda.view.fragment.chinafragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.panda.R;
import com.example.panda.adapter.ChianLvAdapter;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.entity.ChianBean2;
import com.example.panda.utils.OkHttpsManner;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/29.
 */

public class AttractionsFragment extends BaseFragment {

    private ListView att_lv;
    private String name;
    private List<ChianBean2.LiveBean> list = new ArrayList<>();
       BroadcastReceiver br = new BroadcastReceiver() {

           @Override
           public void onReceive(Context context, Intent intent) {
               name = intent.getStringExtra("name");
               Log.e("name","guangbo1111"+name);
               OkHttpsManner manner = new OkHttpsManner();
               manner.getNetData(name, new OkHttpsManner.CallBacks() {
                   @Override
                   public void getString(String ss) {
                       Gson gson = new Gson();
                       ChianBean2 chianBean2 = gson.fromJson(ss, ChianBean2.class);
                       list.addAll(chianBean2.getLive());
                       getActivity().runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               adapter = new ChianLvAdapter(getActivity(), list);
                               att_lv.setAdapter(adapter);
                           }
                       });

                   }
               });
           }

       };
    private ChianLvAdapter adapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String s){
        Log.e("HHH",s+"");
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        att_lv = view.findViewById(R.id.att_lv);
        IntentFilter ifr = new IntentFilter("json");
        getActivity().registerReceiver(br, ifr);

    }

    @Override
    protected int getLayout() {
        return R.layout.bdl_item;
    }


    @Override
    public void onResume() {
        super.onResume();
        list.clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
    }

}
