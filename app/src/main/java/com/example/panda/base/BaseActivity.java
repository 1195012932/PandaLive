package com.example.panda.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.panda.R;
import com.example.panda.app.MyApp;

/**
 * Created by lenovo on 2017/8/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Dialog loadDialog;
    private int dialogNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        MyApp.activity=this;
        initView();
        initData();
        initListener();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp.activity=this;
    }

    protected abstract void loadData();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
    /**
     * 显示正在加载的进度条
     */
    public void showLoadingDialog() {
        dialogNum++;
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        loadDialog = new Dialog(BaseActivity.this, R.style.dialog);
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
