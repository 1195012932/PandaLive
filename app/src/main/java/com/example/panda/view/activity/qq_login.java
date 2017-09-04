package com.example.panda.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import com.example.panda.R;

import java.lang.reflect.Field;

public class qq_login extends AppCompatActivity {
    int  s=1;
    private TextView common_title_left;
    private TextView tv_search_btn;
    private ImageView iv_search;
    private EditText et_search;
    private ImageView iv_clear_searchedit;
    private RelativeLayout layout_search;
    private TextView common_title_center;
    private TextView cctv_common_title_center;
    private TextView common_title_right;
    private TextView common_title_right2;
    private TextView qq_zhuce;
    private WebView wv_qqlogin;
    private String userSeqId;
    private String verifycode;
    private String userNickName;
    private UserManager mUserManager;
    private String userFace;
    private String back_url = "http://my.cntv.cn";
    private String mPath = "http://oauth.passport.cntv.cn/OAuthQzoneClient/OAuthQZoneClientServlet.do?method=login&cntv_callback=my";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_login);
        initView();
    }

    private void initView() {
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        tv_search_btn = (TextView) findViewById(R.id.tv_search_btn);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        et_search = (EditText) findViewById(R.id.et_search);
        iv_clear_searchedit = (ImageView) findViewById(R.id.iv_clear_searchedit);
        layout_search = (RelativeLayout) findViewById(R.id.layout_search);
        common_title_center = (TextView) findViewById(R.id.common_title_center);
        cctv_common_title_center = (TextView) findViewById(R.id.cctv_common_title_center);
        common_title_right = (TextView) findViewById(R.id.common_title_right);
        common_title_right2 = (TextView) findViewById(R.id.common_title_right2);
        qq_zhuce = (TextView) findViewById(R.id.qq_zhuce);
        wv_qqlogin = (WebView) findViewById(R.id.wv_qqlogin);
        String ua = wv_qqlogin.getSettings().getUserAgentString();
        wv_qqlogin.getSettings().setUserAgentString(ua + ";cntv_app_client_cbox_mobile");
        wv_qqlogin.getSettings().setJavaScriptEnabled(true);
        wv_qqlogin.getSettings().setBuiltInZoomControls(true);
        wv_qqlogin.getSettings().setSupportZoom(true);
        wv_qqlogin.getSettings().setUseWideViewPort(true);
        wv_qqlogin.getSettings().setLoadWithOverviewMode(true);
        if (Integer.parseInt(Build.VERSION.SDK) >= 11) {// 用于判断是否为Android 3.0系统,

            wv_qqlogin.getSettings().setDisplayZoomControls(false);

        } else {

            setZoomControlGone(wv_qqlogin);
        }

        wv_qqlogin.setWebViewClient(new MyWebViewClient());
        wv_qqlogin.loadUrl(mPath);

    }
    public void setZoomControlGone(View view) {
        Class classType;
        Field field;
        try {
            classType = WebView.class;
            field = classType.getDeclaredField("mZoomButtonsController");
            field.setAccessible(true);
            ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(
                    view);
            mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
            try {
                field.set(view, mZoomButtonsController);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
//    public void getUserNickName( String seqId) {

//        PandaApi.getNickNameAndFace(seqId).enqueue(new Callback<UserBean>() {
//            @Override
//            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
//                UserBean bean =  response.body();
//                userNickName = bean.content.getNickname();
//                userFace = bean.content.getUserface();
//
//                mUserManager.saveNickName(userNickName);
//                mUserManager.saveUserId(userSeqId);
//                mUserManager.saveVerifycode(verifycode);
//                mUserManager.saveUserFace(userFace);
//
//                MobileAppTracker.trackEvent("登录", null, "个人中心", 0, userSeqId, "登录", QQLogingActivity.this);
//                Log.e("统计","事件名称:"+"登陆"+"***事件类别:"+"个人中心"+"**ID"+userSeqId);
//                Intent intent = new Intent(QQLogingActivity.this, PersonalLoginActivity.class);
//                intent.putExtra(PersonalLoginActivity.PLATFORM, true);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<UserBean> call, Throwable t) {
//                ToastUtil.showShort(QQLogingActivity.this, "登录出错");
//                finish();
//
//            }
//        });

//    }



    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("QQQQ","login_url"+url);
            if (!TextUtils.isEmpty(userSeqId) && !TextUtils.isEmpty(verifycode)) {

                return true;
            }
            try {
                if (url.startsWith(back_url)) {


                    CookieManager cookieManager = CookieManager.getInstance();

                    if (TextUtils.isEmpty(cookieManager.getCookie(url))) {
                        return true;
                    }
                    String CookieStr = cookieManager.getCookie(url).replaceAll(" ", "");

                    if (!TextUtils.isEmpty(CookieStr)) {
                        String[] data = CookieStr.split(";");
                        for (int i = 0; i < data.length; i++) {
                            if (data[i].length() > 0) {
                                String str = data[i];
                                if (str.split("=").length > 1) {
                                    String key = str.split("=")[0];
                                    String value = str.split("=")[1];
                                    if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                                        if (key.equals("userSeqId")) {
                                            userSeqId = value;
                                        }
                                        if (key.equals("verifycode")) {
                                            verifycode = value;
//                                            UserManager.getInstance().saveVerifycode(verifycode);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
//                        Logs.d("malus", "CookieStr null");
                    }
                    // showLoadingDialog();
//                    getUserNickName(userSeqId);
                    return true;
                }
            } catch (Exception e) {
//                ToastUtil.showShort(QQLogingActivity.this, "登录失败！");
                e.printStackTrace();
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.e("malus", "start url:" + url);
            Log.i("AAA","onPageStarted");
            if(s!=4){
                s++;
            }else{
                Intent it=new Intent(qq_login.this,PersonActivity.class);
                startActivity(it);
                finish();
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public void onPageFinished(WebView view, String url) {

            Log.i("AAA","onPageFinished");
            Log.e("sunzn", "onPageFinished url = " + url);


        }
    }

}
