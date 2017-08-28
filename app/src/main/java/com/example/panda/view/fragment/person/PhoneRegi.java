package com.example.panda.view.fragment.person;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneRegi extends Fragment implements View.OnClickListener {

    private EditText edit_phone;
    private TextView hint_phone;
    private EditText edit_imgyanzhengma;
    private TextView personal_reg_imgcheck;
    private TextView hint_imagecheck;
    private EditText edit_phoneyanzhengma;
    private Button personal_reg_phonecheck;
    private TextView hint_phonecheck;
    private EditText edit_inputpasswrod;
    private TextView hint_password;
    private CheckBox xieyi_check;
    private TextView personal_reg_xieyi_view;
    private TextView hint_xieyi;
    private Button btn_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_regi, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        edit_phone = (EditText) view.findViewById(R.id.edit_phone);
        hint_phone = (TextView) view.findViewById(R.id.hint_phone);
        edit_imgyanzhengma = (EditText) view.findViewById(R.id.edit_imgyanzhengma);
        personal_reg_imgcheck = (TextView) view.findViewById(R.id.personal_reg_imgcheck);
        hint_imagecheck = (TextView) view.findViewById(R.id.hint_imagecheck);
        edit_phoneyanzhengma = (EditText) view.findViewById(R.id.edit_phoneyanzhengma);
        personal_reg_phonecheck = (Button) view.findViewById(R.id.personal_reg_phonecheck);
        hint_phonecheck = (TextView) view.findViewById(R.id.hint_phonecheck);
        edit_inputpasswrod = (EditText) view.findViewById(R.id.edit_inputpasswrod);
        hint_password = (TextView) view.findViewById(R.id.hint_password);
        xieyi_check = (CheckBox) view.findViewById(R.id.xieyi_check);
        personal_reg_xieyi_view = (TextView) view.findViewById(R.id.personal_reg_xieyi_view);
        hint_xieyi = (TextView) view.findViewById(R.id.hint_xieyi);
        btn_register = (Button) view.findViewById(R.id.btn_register);

        personal_reg_phonecheck.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_reg_phonecheck:

                break;
            case R.id.btn_register:

                break;
        }
    }

    private void submit() {
        // validate
        String phone = edit_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getContext(), "phone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String imgyanzhengma = edit_imgyanzhengma.getText().toString().trim();
        if (TextUtils.isEmpty(imgyanzhengma)) {
            Toast.makeText(getContext(), "imgyanzhengma不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneyanzhengma = edit_phoneyanzhengma.getText().toString().trim();
        if (TextUtils.isEmpty(phoneyanzhengma)) {
            Toast.makeText(getContext(), "phoneyanzhengma不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String inputpasswrod = edit_inputpasswrod.getText().toString().trim();
        if (TextUtils.isEmpty(inputpasswrod)) {
            Toast.makeText(getContext(), "inputpasswrod不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
