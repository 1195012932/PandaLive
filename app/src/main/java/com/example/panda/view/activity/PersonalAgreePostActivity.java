package com.example.panda.view.activity;

import android.view.View;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;

import java.io.InputStream;

/**
 * Created by lenovo on 2017/8/29.
 */

public class PersonalAgreePostActivity extends BaseActivity {
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
    protected void initView() {
        findViewById(R.id.agree_post_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContent = (TextView) findViewById(R.id.agree_post_text);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_agree_post;
    }
    private static final String sStyleKey = "StyleKey";
    TextView mContent;


    private String inputStream2String(InputStream inputStream, int bufferSize)
            throws Exception {
        if (inputStream == null || bufferSize < 1) {
            return null;
        }
        int i = -1;
        byte[] b = new byte[bufferSize];
        StringBuffer sb = new StringBuffer();
        while ((i = inputStream.read(b)) != -1) {
            sb.append(new String(b, 0, i));
        }
        return sb.toString();
    }
}
