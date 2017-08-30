package com.example.panda.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView common_title_left;
    private TextView tv_zhuce;
    private LinearLayout llweixinlogin;
    private LinearLayout llqqlogin;
    private LinearLayout llsinalogin;
    private EditText edit_account;
    private EditText edit_password;
    private TextView personal_login_forget_pwd;
    private Button loding_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        llweixinlogin = (LinearLayout) findViewById(R.id.llweixinlogin);
        llqqlogin = (LinearLayout) findViewById(R.id.llqqlogin);
        llsinalogin = (LinearLayout) findViewById(R.id.llsinalogin);
        edit_account = (EditText) findViewById(R.id.edit_account);
        edit_password = (EditText) findViewById(R.id.edit_password);
        personal_login_forget_pwd = (TextView) findViewById(R.id.personal_login_forget_pwd);
        loding_btn = (Button) findViewById(R.id.loding_btn);
        personal_login_forget_pwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        personal_login_forget_pwd.getPaint().setAntiAlias(true);//抗锯齿
        personal_login_forget_pwd.setTextColor(Color.parseColor("#1f539e"));
        initListener();
    }

    private void initListener() {
        loding_btn.setOnClickListener(this);
        tv_zhuce.setOnClickListener(this);

        personal_login_forget_pwd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loding_btn:

                break;
            case R.id.tv_zhuce:
                startActivity(new Intent(LoginActivity.this, Register.class));
                break;
            case R.id.personal_login_forget_pwd:
                startActivity(new Intent(LoginActivity.this, Forget.class));
                break;
        }
    }

    private void submit() {
        // validate
        String account = edit_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "account不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
