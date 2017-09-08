package com.example.panda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.panda.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class FenXiangActivity extends AppCompatActivity implements View.OnClickListener {
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(FenXiangActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(FenXiangActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(FenXiangActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    private ImageView fenxiang_weixin;
    private Button fenxiang_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xiang);
        initView();
    }

    private void initView() {
        fenxiang_weixin = (ImageView) findViewById(R.id.fenxiang_weixin);
        fenxiang_but = (Button) findViewById(R.id.fenxiang_but);
        fenxiang_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareText();
            }
        });
        fenxiang_but.setOnClickListener(this);
    }
//        private void ShareWeb(String img){
//            UMImage thumb = new UMImage(FenXiangActivity.this,img);
//            UMWeb web = new UMWeb("熊猫频道");
//            web.setThumb(thumb);
//            web.setDescription("");
//            web.setTitle("");        new ShareAction(FenXiangActivity.this).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(shareListener).share();
//        }
    private void ShareText(){
        new ShareAction(FenXiangActivity.this).withText("11111111111111")
                .setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).share();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fenxiang_but:
               finish();
                break;
        }
    }
}
