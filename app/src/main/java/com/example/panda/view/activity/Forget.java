package com.example.panda.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;

public class Forget extends AppCompatActivity implements View.OnClickListener {

    private TextView common_title_left;
    private EditText edit_phonenumber;
    private TextView hint_phonenumber;
    private EditText edit_checkimage;
    private ImageView personal_reg_imgcheck;
    private TextView hint_checkimage;
    private EditText edit_checkphone;
    private Button personal_reg_phonecheck;
    private TextView hint_checkphone;
    private EditText edit_newpssword;
    private TextView hint_newpssword;
    private Button tvfoundpswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        edit_phonenumber = (EditText) findViewById(R.id.edit_phonenumber);
        hint_phonenumber = (TextView) findViewById(R.id.hint_phonenumber);
        edit_checkimage = (EditText) findViewById(R.id.edit_checkimage);
        personal_reg_imgcheck = (ImageView) findViewById(R.id.personal_reg_imgcheck);
        hint_checkimage = (TextView) findViewById(R.id.hint_checkimage);
        edit_checkphone = (EditText) findViewById(R.id.edit_checkphone);
        personal_reg_phonecheck = (Button) findViewById(R.id.personal_reg_phonecheck);
        hint_checkphone = (TextView) findViewById(R.id.hint_checkphone);
        edit_newpssword = (EditText) findViewById(R.id.edit_newpssword);
        hint_newpssword = (TextView) findViewById(R.id.hint_newpssword);
        tvfoundpswd = (Button) findViewById(R.id.tvfoundpswd);

        personal_reg_phonecheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_reg_phonecheck:

                break;
            case R.id.personal_reg_imgcheck:

                break;
            case R.id.tvfoundpswd:

                break;
        }
    }

    private void submit() {
        // validate
        String phonenumber = edit_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "phonenumber不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String checkimage = edit_checkimage.getText().toString().trim();
        if (TextUtils.isEmpty(checkimage)) {
            Toast.makeText(this, "checkimage不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String checkphone = edit_checkphone.getText().toString().trim();
        if (TextUtils.isEmpty(checkphone)) {
            Toast.makeText(this, "checkphone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String newpssword = edit_newpssword.getText().toString().trim();
        if (TextUtils.isEmpty(newpssword)) {
            Toast.makeText(this, "newpssword不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
