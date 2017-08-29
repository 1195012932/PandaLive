package com.example.panda.view.fragment.livefragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.model.live.bean.LookBean;
import com.example.panda.model.live.bean.LookBeanDao;
import com.example.panda.view.fragment.livefragment.liveview.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/24.
 */

public class Look extends BaseFragment implements View.OnClickListener {
    private EditText look_edit;
    private Button look_but;
    private ListView look_list;
    private LookBeanDao look;
    List<LookBean> list = new ArrayList<>();
    private LookAdapter adapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        look_but=view.findViewById(R.id.look_but);
        look_edit=view.findViewById(R.id.look_edit);
        look_list=view.findViewById(R.id.look_list);
        look_but.setOnClickListener(this);
        Utils ss = Utils.ss();
        look = ss.look(getActivity());
        getlist();
        adapter = new LookAdapter(getActivity(),list);
        look_list.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.looks;
    }


    @Override
    public void onClick(View view) {
        String edit = look_edit.getText().toString().trim();
        if(TextUtils.isEmpty(edit)){
            Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
        }else{
            look.insert(new LookBean(null,"央视网网友",edit,"08-29-2017"));
            Toast.makeText(getActivity(), "审核通过后显示你的评论", Toast.LENGTH_SHORT).show();
            look_edit.setText("");

        }
    }

    public List<LookBean> getlist() {
        List<LookBean> lists = look.queryBuilder().build().list();
        list.addAll(lists);

//        adapter.notifyDataSetChanged();
        return list;
    }
}
