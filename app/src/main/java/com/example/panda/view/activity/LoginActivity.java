package com.example.panda.view.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.panda.R;
import com.example.panda.presenter.land.UserPersenter;
import com.example.panda.presenter.land.UserPersenterImpl;
import com.example.panda.presenter.land.yzm.YZMPersenter;
import com.example.panda.presenter.land.yzm.YZMPersenterImol;
import com.example.panda.view.land.entity.LoginBean;
import com.example.panda.view.land.entity.UserBean;
import com.example.panda.view.land.view.HearView;
import com.example.panda.view.land.view.LandView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LandView, HearView {

    private ProgressDialog progressDialog;
    private Dialog loadDialog;
    private int dialogNum;
    private TextView common_title_left;
    private TextView tv_zhuce;
    private LinearLayout llweixinlogin;
    private LinearLayout llqqlogin;
    private LinearLayout llsinalogin;
    private EditText edit_account;
    private EditText edit_password;
    private TextView personal_login_forget_pwd;
    private Button loding_btn;
    private TextView hint_account;
    private UserPersenter userPersenter;
    private String emailString;
    private String password;
    private YZMPersenter yzmPersenter;
    private String user_seq_id;
    private String nickname;
    private String userface;
    private LinearLayout activity_login_linear1, activity_login_linear2;
    private ImageView imageView;
    private TextView nick_name;
    private SharedPreferences idshared;
    private SharedPreferences.Editor ideditor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //注册eventBus
        EventBus.getDefault().register(this);


    }


    @Subscribe  //订阅事件
    public void onEventMainThread(LoginBean event) {
        user_seq_id = event.getUser_seq_id();
        idshared = getSharedPreferences("text", MODE_PRIVATE);
        ideditor = idshared.edit();
        ideditor.putString("user_seq_id", user_seq_id);
        ideditor.commit();
        String user_seq_id = idshared.getString("user_seq_id", "");
        Log.e("TAG", "打扫房间爱的疯狂就阿里贷款: " + user_seq_id);
        submit();
    }

    @Subscribe  //订阅事件
    public void onEventUserBean(UserBean userbean) {
        imageView = (ImageView) findViewById(R.id.iv_headicon);
        nick_name = (TextView) findViewById(R.id.nick_name);

        nickname = userbean.getContent().getNickname();
        userface = userbean.getContent().getUserface();
        Log.e("TAG", "名字和图片 " + nickname + userface);
        Glide.with(this).load(userface).into(imageView);
        nick_name.setText(nickname);

        SharedPreferences shared = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("nickname", nickname);
        editor.putString("userface", userface);
        editor.commit();
    }

    private void initView() {

        common_title_left = (TextView) findViewById(R.id.common_title_left);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        llweixinlogin = (LinearLayout) findViewById(R.id.llweixinlogin);
        llqqlogin = (LinearLayout) findViewById(R.id.llqqlogin);
        llsinalogin = (LinearLayout) findViewById(R.id.llsinalogin);
        activity_login_linear1 = (LinearLayout) findViewById(R.id.activity_login_linear1);
        //登录之后的视图
        activity_login_linear2 = (LinearLayout) findViewById(R.id.activity_login_linear2);


        //登录的账号
        edit_account = (EditText) findViewById(R.id.edit_account);
        hint_account = (TextView) findViewById(R.id.hint_account);
        //登录的密码
        edit_password = (EditText) findViewById(R.id.edit_password);

        personal_login_forget_pwd = (TextView) findViewById(R.id.personal_login_forget_pwd);
        loding_btn = (Button) findViewById(R.id.loding_btn);
        personal_login_forget_pwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        personal_login_forget_pwd.getPaint().setAntiAlias(true);//抗锯齿
        personal_login_forget_pwd.setTextColor(Color.parseColor("#1f539e"));

        initListener();
        llqqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it=new Intent(LoginActivity.this,qq_login.class);
                startActivity(it);
            }
        });
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
                showLoadingDialog();

                onEventMainThread(new LoginBean());

                dismissLoadDialog();
                break;
            case R.id.tv_zhuce:
                startActivity(new Intent(LoginActivity.this, Register.class));
                break;
            case R.id.personal_login_forget_pwd:
                startActivity(new Intent(LoginActivity.this, Forget.class));
                break;
        }
    }


//检查邮箱手机号

    private void submit() {
        // validate
        emailString = edit_account.getText().toString().trim();

        password = edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(emailString)) {
            hint_account.setText(getString(R.string.regist_account_null));
            return;
        }
        if (user_seq_id == "0" && "0".equals(user_seq_id)) {
            activity_login_linear1.setVisibility(View.GONE);
            activity_login_linear2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "你的账号或密码错误", Toast.LENGTH_SHORT).show();
            return;
        }
        String tEmail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (emailString.indexOf("@") == -1) {
            tEmail = "^1[3578]\\d{9}$";
        }
        Pattern pattern = Pattern
                .compile(tEmail);
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            userPersenter = new UserPersenterImpl(this);
            userPersenter.getGusn(emailString, password);
            yzmPersenter = new YZMPersenterImol(this);
            yzmPersenter.getHear(user_seq_id);
            hint_account.setText("");
            return;
        } else {
            hint_account.setText(getString(R.string.regist_account_error));
            return;
        }
    }


    @Override
    public void showSuccess() {

        activity_login_linear1.setVisibility(View.GONE);
        activity_login_linear2.setVisibility(View.VISIBLE);
//        Intent intent=new Intent(LoginActivity.this,PersonActivity.class);
//        startActivity(intent);

        Log.e("TAG", "showSuccess: ");
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消event注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess() {
        Log.e("TAG", "完成: ");
    }

    @Override
    public void onErrors() {

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
        loadDialog = new Dialog(LoginActivity.this, R.style.dialog);
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
