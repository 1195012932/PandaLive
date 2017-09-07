package com.example.panda.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    private TextView common_title_left;
    private ImageView pe_set_push_view;
    private ImageView pe_set_play_view;
    private RelativeLayout personal_set_delete_cache_layout;
    private RelativeLayout personal_set_fankui_layout;
    private RelativeLayout personal_set_udpate_layout;
    private RelativeLayout personal_set_ping_layout;
    private RelativeLayout personal_set_about_layout;
    private boolean flag = false;
    private boolean flag2 = false;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        common_title_left.setOnClickListener(this);
        pe_set_push_view.setOnClickListener(this);
        pe_set_play_view.setOnClickListener(this);
        personal_set_delete_cache_layout.setOnClickListener(this);
        personal_set_fankui_layout.setOnClickListener(this);
        personal_set_udpate_layout.setOnClickListener(this);
        personal_set_ping_layout.setOnClickListener(this);
        personal_set_about_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //返回键
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        //是否推送
        pe_set_push_view = (ImageView) findViewById(R.id.pe_set_push_view);
        //是否自动播放下一集
        pe_set_play_view = (ImageView) findViewById(R.id.pe_set_play_view);
        //清除缓存
        personal_set_delete_cache_layout = (RelativeLayout) findViewById(R.id.personal_set_delete_cache_layout);
        //反馈
        personal_set_fankui_layout = (RelativeLayout) findViewById(R.id.personal_set_fankui_layout);
        //检测升级
        personal_set_udpate_layout = (RelativeLayout) findViewById(R.id.personal_set_udpate_layout);
        //评价
        personal_set_ping_layout = (RelativeLayout) findViewById(R.id.personal_set_ping_layout);
        //关于熊猫
        personal_set_about_layout = (RelativeLayout) findViewById(R.id.personal_set_about_layout);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.pe_set_push_view:
                if (flag == false) {
                    pe_set_push_view.setImageResource(R.drawable.switch_close);
                    flag = true;
                } else {
                    pe_set_push_view.setImageResource(R.drawable.switch_open);
                    flag = false;
                }
                break;
            case R.id.pe_set_play_view:
                if (flag2 == false) {
                    pe_set_play_view.setImageResource(R.drawable.switch_close);
                    flag2 = true;
                } else {
                    pe_set_play_view.setImageResource(R.drawable.switch_open);
                    flag2 = false;
                }
                break;
            case R.id.personal_set_delete_cache_layout:
                showDialogs();
                break;
            case R.id.personal_set_fankui_layout:

                break;
            case R.id.personal_set_udpate_layout:

                break;
            case R.id.personal_set_ping_layout:

                break;
            case R.id.personal_set_about_layout:
                startActivity(new Intent(SetActivity.this, AboutPandaActivity.class));
                break;
        }
    }

    private void showDialogs() {
        View viewDialog = (View) LayoutInflater.from(this).inflate(
                R.layout.delete_dialog, null);
        final Dialog dialog = new Dialog(SetActivity.this,
                R.style.loginDialogTheme);
        dialog.setCancelable(false);
        TextView dialogCancel, dialogRightsure, dialog_title, dialog_conTextView;
        dialogRightsure = (TextView) viewDialog
                .findViewById(R.id.login_right_sure);
        dialogCancel = (TextView) viewDialog
                .findViewById(R.id.login_cancel_but);
        dialog_title = (TextView) viewDialog.findViewById(R.id.del_title_tv);
        dialog.setContentView(viewDialog);
        dialog_title.setText("确认清除缓存吗?");
        dialogRightsure.setText("确定");
        dialogCancel.setText("取消");
        dialogRightsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SetActivity.this, "暂时无法清除缓存", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialogCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
