package com.example.panda.view.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.view.fragment.person.EmailReg;
import com.example.panda.view.fragment.person.PhoneRegi;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ImageView user_back;
    private TextView tvphonereg;
    private TextView tvphonereg_line;
    private TextView tvemailreg;
    private TextView tvemailreg_line;
    private FrameLayout framelayout_register_content;
    private FragmentManager manager;
    private Dialog loadDialog;
    private int dialogNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        initListener();
    }

    private void initListener() {
        user_back.setOnClickListener(this);
        tvphonereg.setOnClickListener(this);
        tvphonereg_line.setOnClickListener(this);
        tvemailreg.setOnClickListener(this);
        tvemailreg_line.setOnClickListener(this);
    }

    private void initView() {
        user_back = (ImageView) findViewById(R.id.user_back);
        tvphonereg = (TextView) findViewById(R.id.tvphonereg);
        tvphonereg_line = (TextView) findViewById(R.id.tvphonereg_line);
        tvemailreg = (TextView) findViewById(R.id.tvemailreg);
        tvemailreg_line = (TextView) findViewById(R.id.tvemailreg_line);
        framelayout_register_content = (FrameLayout) findViewById(R.id.framelayout_register_content);
        manager = getSupportFragmentManager();

        PhoneRegi pRegi = new PhoneRegi();
        manager.beginTransaction().replace(R.id.framelayout_register_content, pRegi).commit();
        tvphonereg_line.setVisibility(View.VISIBLE);
        tvemailreg_line.setVisibility(View.GONE);
        tvphonereg_line.setTextColor(Color.parseColor("#1f539e"));
        tvemailreg_line.setTextColor(Color.parseColor("#ffffff"));
        tvemailreg.setTextColor(Color.parseColor("#1f539e"));
        tvemailreg.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View view) {
        Fragment tfragment;
        switch (view.getId()) {
            case R.id.user_back:
                finish();
                break;
            case R.id.tvphonereg:
                showLoadingDialog();
                tvphonereg_line.setVisibility(View.VISIBLE);
                tvemailreg_line.setVisibility(View.GONE);
                tvphonereg_line.setTextColor(Color.parseColor("#1f539e"));
                tvemailreg_line.setTextColor(Color.parseColor("#ffffff"));
                tvphonereg.setTextColor(Color.parseColor("#1f539e"));
                tvemailreg.setTextColor(Color.parseColor("#000000"));
                tfragment = new PhoneRegi();
                manager.beginTransaction().replace(R.id.framelayout_register_content, tfragment).commit();

                dismissLoadDialog();
                break;
            case R.id.tvemailreg:
                showLoadingDialog();
                tvphonereg_line.setVisibility(View.GONE);
                tvemailreg_line.setVisibility(View.VISIBLE);
                tvphonereg_line.setTextColor(Color.parseColor("#ffffff"));
                tvemailreg_line.setTextColor(Color.parseColor("#1f539e"));
                tvphonereg.setTextColor(Color.parseColor("#000000"));
                tvemailreg.setTextColor(Color.parseColor("#1f539e"));
                tfragment = new EmailReg();
                manager.beginTransaction().replace(R.id.framelayout_register_content, tfragment).commit();
                dismissLoadDialog();
                break;
        }
    }

    /**
     * 显示正在加载的进度条
     */
    public void showLoadingDialog() {
        dialogNum++;
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        loadDialog = new Dialog(Register.this, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.layout_dialog);
        try {
            loadDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 隐藏正在加载的进度条
     */
    public void dismissLoadDialog() {
        dialogNum--;
        if (dialogNum > 0) {
            return;
        }
        if (null != loadDialog && loadDialog.isShowing() == true) {
            loadDialog.dismiss();
            loadDialog = null;
        }
    }

}
