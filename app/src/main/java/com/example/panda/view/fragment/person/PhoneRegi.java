package com.example.panda.view.fragment.person;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.presenter.register.RegiserPresenterImpl;
import com.example.panda.presenter.register.RegisterPresenter;
import com.example.panda.view.activity.PersonActivity;
import com.example.panda.view.activity.PersonalAgreePostActivity;
import com.example.panda.view.register.RegisterView;
import com.gridsum.mobiledissector.MobileAppTracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.panda.R.id.edit_phone_imageyanzhengma;
import static com.example.panda.view.fragment.person.EmailReg.byteToDrawable;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneRegi extends BaseFragment implements View.OnFocusChangeListener {

    public String JSESSIONID = null;
    public String verifycode = null;
    public String uct = null;
    //My手机注册
    private View view;
    private byte[] mCaptchaBytes;
    private String erroType = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
    private String mUserSeqId;
    private String mUserId;
    private String mNickName;

    //获取验证码图片成功、失败
    private static final int IMG_GET_SUCCES = 101;
    private static final int IMG_GET_ERROR = 102;

    //获取手机验证码成功、失败
    private static final int MSG_GETTING_SUCCESS = 103;
    private static final int MSG_GETTING_ERROR = 104;
    //手机号注册成功、失败
    private static final int MSG_LOGIN_SUCCESS = 105;
    private static final int MSG_LOGIN_ERROR = 106;
    //获取用户ID、昵称
    private static final int MSG_LGOIN_IN_GET_NICKNAME = 107;
    private static final int MSG_LOGIN_IN_ERROR = 108;
    private static final int MSG_GET_NICKNAME_SUCCESS = 109;


    private EditText edit_phone;
    private EditText medit_phone_imageyanzhengma;
    private EditText edit_phone_yanzhengma;
    private EditText edit_phone_passwrod;


    private TextView personalphone_reg_imgcheck;
    private CheckBox xieyi_phone_check;
    private TextView personalphone_reg_xieyi_view;


    private Button personal_reg_phonecheck;
    private Button btn_phone_register;
    //红色字体
    private TextView hint_phone;
    private TextView hint_imagecheck;
    private TextView hint_phonecheck;
    private TextView hint_password;
    private String mCaptchaEditTextString;
    private String phonecheck;
    private TimeCount mTime;


    private void showTishiDialog(String tishiString) {
        try {
            if (getActivity().isFinishing()) {
                return;
            }

            View tview = View.inflate(
                    getActivity(),
                    R.layout.person_register_tishi_dialog, null);
            TextView tishiView = (TextView) tview.findViewById(R.id.register_tishi_txt);
            tishiView.setText(tishiString);
            TextView tishiSure = (TextView) tview.findViewById(R.id.register_tishi_btn_sure);
            final Dialog dialog = new Dialog(getActivity(), R.style.dialog);

            dialog.setContentView(tview);
            dialog.setCanceledOnTouchOutside(true);
            tishiSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {

        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_phone_regi;
    }

    protected void initView(View view) {
        edit_phone = view.findViewById(R.id.edit_phone);
        medit_phone_imageyanzhengma = view.findViewById(R.id.edit_phone_imageyanzhengma);

        edit_phone_yanzhengma = view.findViewById(R.id.edit_phone_yanzhengma);

        edit_phone_passwrod = view.findViewById(R.id.edit_phone_passwrod);

        personalphone_reg_imgcheck = view.findViewById(R.id.personalphone_reg_imgcheck);

        personal_reg_phonecheck = view.findViewById(R.id.personalphone_reg_but);
        personal_reg_phonecheck.setOnClickListener(new ViewClick());
        personalphone_reg_imgcheck.setOnClickListener(new ViewClick());

        xieyi_phone_check = view.findViewById(R.id.xieyi_phone_check);
        personalphone_reg_xieyi_view = view.findViewById(R.id.personalphone_reg_xieyi_view);
        btn_phone_register = view.findViewById(R.id.btn_phone_register);
        mTime = new TimeCount(180000, 1000);//构造CountDownTimer对象

        //红色字体
        hint_phone = view.findViewById(R.id.hint_phone);
        hint_imagecheck = view.findViewById(R.id.hint_imagecheck);
        hint_phonecheck = view.findViewById(R.id.hint_phonecheck);
        hint_password = view.findViewById(R.id.hint_password);

        edit_phone.setOnFocusChangeListener(this);
        medit_phone_imageyanzhengma.setOnFocusChangeListener(this);

        edit_phone_yanzhengma.setOnFocusChangeListener(this);
        edit_phone_passwrod.setOnFocusChangeListener(this);
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMG_GET_SUCCES:
                    dismissLoadDialog();
                    Drawable captchaImage = byteToDrawable(mCaptchaBytes);
                    personalphone_reg_imgcheck.setBackgroundDrawable(captchaImage);
                    personalphone_reg_imgcheck.setText("");
                    break;
                case IMG_GET_ERROR:
                    dismissLoadDialog();
                    personalphone_reg_imgcheck.setText("图形验证码");
                    sendCaptchaHttpMessage();
                    personalphone_reg_imgcheck.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    if (msg.obj != null) {
                        if (msg.obj instanceof String) {
                            showTishiDialog((String) msg.obj);
                        }
                    }
                    break;
                case MSG_LOGIN_SUCCESS:
                    dismissLoadDialog();
                    if (msg.obj != null) {
                        Toast toast = Toast.makeText(getActivity(), (String) msg.obj, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        MobileAppTracker.trackEvent("注册", null, "个人中心", 0, null, null, getActivity());
                        goLogin();
                    }
                    break;
                case MSG_LOGIN_ERROR:
                    dismissLoadDialog();
                    if (msg.obj != null)

                        break;
                case MSG_GETTING_SUCCESS:
                    mTime.start();
                    dismissLoadDialog();
                    if (msg.obj != null)

                        break;
                case MSG_GETTING_ERROR:
                    dismissLoadDialog();
                    if (msg.obj != null)

                        break;
                case MSG_LGOIN_IN_GET_NICKNAME:
                    getUserTicket();
                    break;
                case MSG_LOGIN_IN_ERROR:
                    dismissLoadDialog();
                    if (msg.obj != null) {
                        if (msg.obj instanceof String) {
                            String err = (String) msg.obj;
                            // Toast.makeText(PersonalLoginActivity.this,err,
                            /// 0).show();
                            showTishiDialog(err);
                        }
                    }
                    break;
                case MSG_GET_NICKNAME_SUCCESS:
                    dismissLoadDialog();
                    Intent intent = new Intent(getActivity(), PersonActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
            }
        }
    };

    @Override
    protected void initData() {
        getImCode();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if (hasFocus) {
            switch (view.getId()) {
                case R.id.edit_phone:
                    hint_phone.setText("");
                    break;

                case edit_phone_imageyanzhengma:
                    if (!checkPhone())
                        return;
                    hint_imagecheck.setText("");
                    break;
                case R.id.edit_phone_yanzhengma:
                    if (!checkCaptcha())
                        return;
                    hint_phonecheck.setText("");
                    break;
                case R.id.edit_phone_passwrod:
                    if (!checkPhoneCheck())
                        return;
                    hint_password.setText("");
                    break;
            }
        }
    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = edit_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            hint_phone.setText("手机号码不能为空");
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            hint_phone.setText("");
            return true;
        } else {
            hint_phone.setText("手机格式不正确");
            Toast.makeText(getActivity(), "手机格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 检查验证码
     *
     * @return
     */
    private boolean checkCaptcha() {
        if (mCaptchaBytes == null) {
            Toast.makeText(getActivity(), "未获取验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        mCaptchaEditTextString = medit_phone_imageyanzhengma.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            hint_imagecheck.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            hint_imagecheck.setText("验证码不能为空");
            return false;
        } else {
            hint_imagecheck.setText("");
            return true;
        }
    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        phonecheck = edit_phone_yanzhengma.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            hint_phonecheck.setText("验证码不能为空");
            return false;
        } else {
            hint_phonecheck.setText(" ");
            return true;
        }
    }

    /**
     * 检查协议
     *
     * @return
     */
    private boolean checkXieyi() {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.xieyi_check);
        if (!checkBox.isChecked()) {
            hint_password.setText("未勾选《央视网网络服务使用协议》");
            return false;
        } else {
            hint_password.setText("");
            return true;
        }
    }


    /**
     * 获取图片验证码
     */
    private void sendCaptchaHttpMessage() {
        if (!isConnected()) {
            return;
        }
        showLoadingDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String from = "http://reg.cntv.cn/simple/verificationCode.action";
                HttpGet httpGet = new HttpGet(from);
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {// 读返回数据

                        CookieStore cookieStore = httpClient.getCookieStore();
                        List<Cookie> cookies = cookieStore.getCookies();
                        for (int index = 0, count = cookies.size(); index < count; index++) {
                            Cookie cookie = cookies.get(index);
                            if ("JSESSIONID".equals(cookie.getName())) {
                                JSESSIONID = cookie.getValue();
                                break;
                            }
                        }
                        mCaptchaBytes = EntityUtils.toByteArray(httpResponse
                                .getEntity());

                        mHandler.sendEmptyMessage(IMG_GET_SUCCES);

                    } else {
                        Log.e("TAG", "run:重新获取 ");
                        Message msg = mHandler
                                .obtainMessage(IMG_GET_ERROR);
                        msg.obj = erroType;
                        mHandler.sendMessage(msg);
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 检查密码
     *
     * @return
     */
    private boolean checkPasswork() {
        String editpasswordsString = edit_phone_passwrod.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            hint_password.setText("密码不能为空");
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            hint_password.setText("密码仅限6-16个字符");
            return false;
        } else {
            hint_password.setText("");
            return true;
        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            personal_reg_phonecheck.setText("获取验证码");
            personal_reg_phonecheck.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            personal_reg_phonecheck.setClickable(false);
            personal_reg_phonecheck.setText("重新获取" + "(" + millisUntilFinished / 1000 + ")");
        }
    }


    class ViewClick implements View.OnClickListener, RegisterView {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.personalphone_reg_xieyi_view:
                    Intent xieIntent = new Intent(getActivity(), PersonalAgreePostActivity.class);
                    startActivity(xieIntent);
                    break;

                case R.id.personalphone_reg_imgcheck:
                    if (!isConnected()) {
                        Toast.makeText(getActivity(), R.string.network_invalid, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //获取图片验证码
                    sendCaptchaHttpMessage();

                    break;
                case R.id.personalphone_reg_but:

                    edit_phone.clearFocus();
                    medit_phone_imageyanzhengma.clearFocus();
                    edit_phone_yanzhengma.clearFocus();

                    String phone = edit_phone.getText().toString().trim();
                    String tImageChcek = medit_phone_imageyanzhengma.getText().toString().trim();
                    Log.e("TAG", "输入框里面的图片验证码" + tImageChcek);
                    //获取手机验证码
                    RegisterPresenter registerPresenter = new RegiserPresenterImpl(this);
                    registerPresenter.getRegisterPresen(phone, tImageChcek, JSESSIONID);
                    getImCode();

                    if (TextUtils.isEmpty(tImageChcek)) {
                        hint_imagecheck.setText("图片验证码输入有误");
                    }
                    if (!checkPhone()) {
                        return;
                    }
                    if (!checkCaptcha()) {
                        return;
                    } else {
                        hint_phone.setText("");
                        hint_imagecheck.setText("");
                    }
                    break;

                case R.id.btn_phone_register:

                    edit_phone.clearFocus();
                    medit_phone_imageyanzhengma.clearFocus();
                    edit_phone_yanzhengma.clearFocus();
                    edit_phone_passwrod.clearFocus();
                    String phones = edit_phone.getText().toString().trim();
                    String mageyanzhengma = medit_phone_imageyanzhengma.getText().toString().trim();
                    String phoneyanzhengma = edit_phone_yanzhengma.getText().toString().trim();

                    if (!isConnected()) {
                        Toast.makeText(getActivity(), R.string.network_invalid, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //点击注册
                    if (!checkPhone()) {

                        return;
                    }

                    if (!checkCaptcha()) {
                        return;
                    }

                    if (!checkPhoneCheck()) {
                        return;
                    }
                    if (!checkPasswork()) {
                        return;
                    }

                    if (!checkXieyi()) {
                        return;
                    }
                    break;
            }
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onErrors(String msg) {

        }
    }

    /**
     * 登录
     */
    private void goLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String mUserNameString = edit_phone.getText().toString()
                        .trim();
                Log.e("TAG", "705电话号: " + mUserNameString);
                String mPassWordString = edit_phone_passwrod.getText().toString()
                        .trim();
                Log.e("TAG", "708密码" + mPassWordString);
                try {
                    String from = "https://reg.cntv.cn/login/login.action";
                    String url = from + "?username="
                            + URLEncoder.encode(mUserNameString, "UTF-8")
                            + "&password=" + mPassWordString
                            + "&service=client_transaction" + "&from="
                            + URLEncoder.encode(from, "UTF-8");
                    HttpGet httpRequest = new HttpGet(url);

                    httpRequest.addHeader("Referer",
                            URLEncoder.encode(from, "UTF-8"));
                    httpRequest.addHeader("User-Agent", URLEncoder.encode(
                            "CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"));

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(httpRequest);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {// 读返回数据
                        String strResult = EntityUtils.toString(httpResponse
                                .getEntity());
                        CookieStore cookieStore = httpClient.getCookieStore();
                        List<Cookie> cookies = cookieStore.getCookies();
                        for (int index = 0, count = cookies.size(); index < count; index++) {
                            Cookie cookie = cookies.get(index);
                            if ("JSESSIONID".equals(cookie.getName())) {
                                JSESSIONID = cookie.getValue();
                            } else if ("verifycode".equals(cookie.getName())) {
                                verifycode = cookie.getValue();
                                Log.e("TAG", "登陆: " + verifycode);
                            } else if ("uct".equals(cookie.getName())) {
                                uct = cookie.getValue();
                            }
                        }

                        JSONObject jo = new JSONObject(strResult);
                        if (jo.getString("errType").equals("0")) {
                            if (jo.has("user_seq_id")) {
                                mUserSeqId = jo.getString("user_seq_id");
                            }
                            if (jo.has("usrid")) {
                                mUserId = jo.getString("usrid");
                            }
                            mHandler.sendEmptyMessage(MSG_LGOIN_IN_GET_NICKNAME);
                        } else {
                            // String codeString = jo.getString("errType");
                            String errMsg = jo.getString("errMsg");

                            Message msg = mHandler
                                    .obtainMessage(MSG_LOGIN_IN_ERROR);
                            msg.obj = errMsg;
                            mHandler.sendMessage(msg);
                        }
                    } else {
                        Message msg = mHandler
                                .obtainMessage(MSG_LOGIN_IN_ERROR);
                        //msg.obj = "网络异常";
                        msg.obj = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
                        mHandler.sendMessage(msg);
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    /**
     * 获取昵称
     */
    private void getUserTicket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String client = "http://cbox_mobile.regclientuser.cntv.cn";
                String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                        + "cbox_mobile" + "&method=" + "user.getNickName"
                        + "&userid=" + mUserSeqId;
                HttpGet httpGet = new HttpGet(url);
                try {
                    httpGet.addHeader("Referer",
                            URLEncoder.encode(client, "UTF-8"));
                    httpGet.addHeader("User-Agent", URLEncoder.encode(
                            "CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
                    httpGet.addHeader("Cookie", "verifycode=" + verifycode);

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {// 读返回数据
                        String strResult = EntityUtils.toString(httpResponse
                                .getEntity());
                        JSONObject jo = new JSONObject(strResult);
                        if (jo.getString("code").equals("0")) {
                            if (jo.has("content")) {
                                JSONObject contentJSONObject = jo
                                        .getJSONObject("content");
                                if (contentJSONObject.has("nickname")) {
                                    mNickName = contentJSONObject
                                            .getString("nickname");
                                } else {
                                    mNickName = "default";
                                }
                            }
                            mHandler.sendEmptyMessage(MSG_GET_NICKNAME_SUCCESS);
                        } else {
                            String codeErrorString = jo.getString("error");
                            Message msg = mHandler
                                    .obtainMessage(MSG_LOGIN_IN_ERROR);
                            msg.obj = codeErrorString;
                            mHandler.sendMessage(msg);
                        }
                    } else {
                        Message msg = mHandler
                                .obtainMessage(MSG_LOGIN_IN_ERROR);
                        //msg.obj = "网络错误";
                        msg.obj = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
                        mHandler.sendMessage(msg);
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //获取cookie

    private void getImCode() {

        CookieJar cookieJar = new CookieJar() {

            List<okhttp3.Cookie> cookies_c = new ArrayList<okhttp3.Cookie>();

            @Override
            public void saveFromResponse(HttpUrl url, List<okhttp3.Cookie> cookies) {
                cookies_c = cookies;

                for (okhttp3.Cookie cookie : cookies_c) {
                    Log.e(TAG, "saveFromResponse: 这是cookieName=====" + cookie.name());
                    Log.e(TAG, "saveFromResponse: 这是cookieValue=====" + cookie.value());

                    if ("JSESSIONID".equals(cookie.name())) {
                        JSESSIONID = cookie.value();
                        Log.e(TAG, "---------------------------------------------" + JSESSIONID);
                    }
                }
            }

            @Override
            public List<okhttp3.Cookie> loadForRequest(HttpUrl url) {
                return cookies_c;
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieJar).build();
        final Request request = new Request.Builder().url("http://reg.cntv.cn/simple/verificationCode.action").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("TAG", "图片验证: " + string);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        sendCaptchaHttpMessage();
    }
    //    Handler myHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0x00:
//                    int event = msg.arg1;
//                    int result = msg.arg2;
//                    Object data = msg.obj;
//                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
//                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
//                        if (event == SMSSDK.EVEgetActivity(), "发送验证码成功", Toast.LENGTH_SHORT).show();NT_GET_VERIFICATION_CODE) { //获取验证码
//                            Toast.makeText(
//                            Log.d(TAG, "get verification code successful.");
//                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
//                            Log.d(TAG, "submit code successful");
//                            Toast.makeText(getActivity(), "提交验证码成功", Toast.LENGTH_SHORT).show();
//                            //Intent intent = new Intent(PhoneRegi.this, SuccessActivity.class);
//                            //startActivity(intent);
//                        } else {
//                            Log.d(TAG, data.toString());
//                        }
//                    } else { //进行操作出错，通过下面的信息区分析错误原因
//                        try {
//                            Throwable throwable = (Throwable) data;
//                            throwable.printStackTrace();
//                            JSONObject object = new JSONObject(throwable.getMessage());
//                            String des = object.optString("detail");//错误描述
//                            int status = object.optInt("status");//错误代码
//                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
//                            Log.e(TAG, "status: " + status + ", detail: " + des);
//                            if (status > 0 && !TextUtils.isEmpty(des)) {
//                                Toast.makeText(getActivity(), des, Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                case 0x01:
//                    personal_reg_phonecheck.setText("重新发送(" + msg.arg1 + ")");
//                    break;
//                case 0x02:
//                    personal_reg_phonecheck.setText("获取验证码");
//                    personal_reg_phonecheck.setClickable(true);
//                    break;
//            }
//        }
//    };
//
//    //验证码图片
//    public Bitmap createBitmap() {
//        padding_left = 0;
//
//        Bitmap bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(bp);
//
//        code = createCode();
//
//        c.drawColor(Color.WHITE);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setTextSize(font_size);
//        //画验证码
//        for (int i = 0; i < code.length(); i++) {
//            randomTextStyle(paint);
//            randomPadding();
//            c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
//        }
//
//        c.save(Canvas.ALL_SAVE_FLAG);//保存
//        c.restore();//
//        return bp;
//    }

//
//    public String getCode() {
//        return code;
//    }

//    //生成验证码
//    private String createCode() {
//        StringBuilder buffer = new StringBuilder();
//        for (int i = 0; i < codeLength; i++) {
//            buffer.append(CHARS[random.nextInt(CHARS.length)]);
//        }
//        return buffer.toString();
//    }

//    //生成随机颜色
//    private int randomColor() {
//        return randomColor(1);
//    }
//
//    private int randomColor(int rate) {
//        int red = random.nextInt(256) / rate;
//        int green = random.nextInt(256) / rate;
//        int blue = random.nextInt(256) / rate;
//        return Color.rgb(red, green, blue);
//    }

//    //随机生成文字样式，颜色，粗细，倾斜度
//    private void randomTextStyle(Paint paint) {
//        int color = randomColor();
//        paint.setColor(color);
//        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
//        float skewX = random.nextInt(11) / 10;
//        skewX = random.nextBoolean() ? skewX : -skewX;
//        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
//    }
//
//    //随机生成padding值
//    private void randomPadding() {
//        padding_left += base_padding_left + random.nextInt(range_padding_left);
//        padding_top = base_padding_top + random.nextInt(range_padding_top);
//    }

}
