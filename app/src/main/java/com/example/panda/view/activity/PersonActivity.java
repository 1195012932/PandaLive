package com.example.panda.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    private TextView common_title_left;
    private RelativeLayout person_no_login_layout;
    private RelativeLayout personal_history_layout;
    private RelativeLayout personal_shoucang_layout;
    private RelativeLayout personal_set_layout;
    TextView personal_login_view;
    ImageView personal_img;
    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        common_title_left.setOnClickListener(this);
        person_no_login_layout.setOnClickListener(this);
        personal_history_layout.setOnClickListener(this);
        personal_shoucang_layout.setOnClickListener(this);
        personal_set_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        personal_img= (ImageView) findViewById(R.id.personal_img);
        personal_login_view= (TextView) findViewById(R.id.personal_login_view);
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        person_no_login_layout = (RelativeLayout) findViewById(R.id.person_no_login_layout);
        personal_history_layout = (RelativeLayout) findViewById(R.id.personal_history_layout);
        personal_shoucang_layout = (RelativeLayout) findViewById(R.id.personal_shoucang_layout);
        personal_set_layout = (RelativeLayout) findViewById(R.id.personal_set_layout);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_person;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.person_no_login_layout:

                startActivity(new Intent(PersonActivity.this, LoginActivity.class));

                break;
            case R.id.personal_history_layout:
                startActivity(new Intent(PersonActivity.this, HistoryActivity.class));

                break;
            case R.id.personal_shoucang_layout:
                startActivity(new Intent(PersonActivity.this, CollectActivity.class));

                break;
            case R.id.personal_set_layout:
                startActivity(new Intent(PersonActivity.this, SetActivity.class));
                break;
        }
    }
}
