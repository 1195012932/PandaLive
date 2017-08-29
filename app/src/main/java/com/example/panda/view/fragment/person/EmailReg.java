package com.example.panda.view.fragment.person;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseFragment;
import com.example.panda.view.activity.PersonalAgreePostActivity;
import com.gridsum.mobiledissector.MobileAppTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailReg extends BaseFragment {

    private EditText edit_email;
    private TextView hint_emamil;
    private EditText edit_passwrok;
    private TextView hint_passwork;
    private EditText edit_again_password;
    private TextView hint_again_passord;
    private EditText edit_yanzhengma;
    private ImageView personal_reg_imgcheck;
    private TextView hint_yanzhengma;
    private CheckBox xieyi_check;
    private TextView personal_reg_xieyi_view;
    private TextView hint_xieyi;
    private Button btn_register;
    private String realCode;
    //随机数数组
    private static final char[] CHARS = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'i', 'l',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'o',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'O'
    };
    private static EmailReg bmpCode;
    //default settings
    //验证码默认随机数的个数
    private static final int DEFAULT_CODE_LENGTH = 4;
    //默认字体大小
    private static final int DEFAULT_FONT_SIZE = 25;
    //默认线条的条数
    private static final int DEFAULT_LINE_NUMBER = 5;
    //padding值
    private static final int BASE_PADDING_LEFT = 10, RANGE_PADDING_LEFT = 15, BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;
    //验证码的默认宽高
    private static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 40;

    //settings decided by the layout xml
    //canvas width and height
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    //random word space and pading_top
    private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,
            base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;

    //number of chars, lines; font size
    private int codeLength = DEFAULT_CODE_LENGTH, line_number = DEFAULT_LINE_NUMBER, font_size = DEFAULT_FONT_SIZE;
    private int padding_left, padding_top;
    private Random random = new Random();
    private String code;
    private String mCaptchaEditTextString;
    private String erroType = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
    private TextView mCaptchaImageView;


    //获取验证码图片成功
    private static final int IMG_GET_SUCCES = 1000;
    private static final int MSG_LOGIN_IN_ERROR = 1002;
    //获取邮箱注册成功
    private static final int MSG_LOGIN_IN_SUCCES = 1003;
    public String JSESSIONID = null;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    public static EmailReg getInstance() {
        if (bmpCode == null)
            bmpCode = new EmailReg();
        return bmpCode;
    }

    protected void initView(View view) {
        edit_email = (EditText) view.findViewById(R.id.edit_email);
        hint_emamil = (TextView) view.findViewById(R.id.hint_emamil);
        edit_passwrok = (EditText) view.findViewById(R.id.edit_passwrok);
        hint_passwork = (TextView) view.findViewById(R.id.hint_passwork);
        edit_again_password = (EditText) view.findViewById(R.id.edit_again_password);
        hint_again_passord = (TextView) view.findViewById(R.id.hint_again_passord);
        edit_yanzhengma = (EditText) view.findViewById(R.id.edit_yanzhengma);
        personal_reg_imgcheck = (ImageView) view.findViewById(R.id.personal_reg_imgcheck);
        hint_yanzhengma = (TextView) view.findViewById(R.id.hint_yanzhengma);
        xieyi_check = (CheckBox) view.findViewById(R.id.xieyi_check);
        personal_reg_xieyi_view = (TextView) view.findViewById(R.id.personal_reg_xieyi_view);
        hint_xieyi = (TextView) view.findViewById(R.id.hint_xieyi);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        sendCaptchaHttpMessage();
        btn_register.setOnClickListener(new ViewClick());
        personal_reg_xieyi_view.setOnClickListener(new ViewClick());
        personal_reg_imgcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCaptchaHttpMessage();
            }
        });
    }

    private void sendCaptchaHttpMessage() {
        personal_reg_imgcheck.setImageBitmap(EmailReg.getInstance().createBitmap());
        realCode = EmailReg.getInstance().getCode().toLowerCase();
        Log.v("TAG", "realCode" + realCode);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_email_reg;
    }


    class ViewClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.personal_reg_xieyi_view:
                    Intent xieIntent = new Intent(getActivity(), PersonalAgreePostActivity.class);
                    startActivity(xieIntent);
                    break;

                case R.id.personal_reg_imgcheck:
                    //获取验证码
                    sendCaptchaHttpMessage();
                    break;
                case R.id.btn_register:
                    edit_email.clearFocus();
                    edit_passwrok.clearFocus();
                    edit_again_password.clearFocus();
                    edit_yanzhengma.clearFocus();
                    if (!isConnected()) {
                        Toast.makeText(getActivity(), R.string.network_invalid, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!checkEmail()) {
                        return;
                    }
                    if (!checkPasswork()) {
                        return;
                    }
                    if (!checkAgainPasswork()) {
                        return;
                    }

                    if (!checkCaptcha()) {
                        return;
                    }

                    if (!checkXieyi()) {
                        return;
                    }

                    sendHttpMessageOfMail();

                    break;

            }
        }

    }

    private void sendHttpMessageOfMail() {
        if (!isConnected()) {
            Toast.makeText(getActivity(), R.string.network_invalid, Toast.LENGTH_SHORT).show();
            return;
        }
        showLoadingDialog();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String emailString = edit_email.getText().toString().trim();
                String passwordString = edit_passwrok.getText().toString();
                String from = "iPanda.Android";
                try {
                    String url = "https://reg.cntv.cn/api/register.action" + "?mailAdd="
                            + emailString + "&passWd=" + URLEncoder.encode(passwordString, "UTF-8")
                            + "&verificationCode=" + mCaptchaEditTextString + "&addons="
                            + URLEncoder.encode(from, "UTF-8");
                    try {
                        URL url1 = new URL(url);
                        try {
                            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                            conn.setRequestMethod("POST");
                            conn.connect();
                            int code = conn.getResponseCode();
                            conn.addRequestProperty("Referer", URLEncoder.encode(from, "UTF-8"));
                            conn.setRequestProperty("User-Agent",
                                    URLEncoder.encode("CNTV_APP_CLIENT_CNTV_MOBILE", "UTF-8"));
                            conn.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID);
                            if (code == 200) {
                                InputStream inputSource = conn.getInputStream();
                                InputStreamReader reader = new InputStreamReader(inputSource);
                                BufferedReader reader1 = new BufferedReader(reader);
                                String s = null;
                                StringBuffer strResult = new StringBuffer();
                                while ((s = reader1.readLine()) != null) {
                                    strResult.append(s);
                                }
                                JSONObject jsonObject = new JSONObject(strResult.toString());
                                if (jsonObject.has("errtype")) {
                                    String errtype = jsonObject.getString("errtype");
                                    if ("0".equals(errtype)) { // success
                                        Log.i("TAG", strResult + "-----" + URLEncoder.encode(passwordString, "UTF-8"));
                                        Message msg = mHandler
                                                .obtainMessage(MSG_LOGIN_IN_SUCCES);
                                        mHandler.sendMessage(msg);
                                    } else { // failure
                                        Message msg = mHandler
                                                .obtainMessage(MSG_LOGIN_IN_ERROR);
//								msg.obj = jsonObject.get("errtype");
                                        msg.obj = jsonObject.get("msg");
                                        mHandler.sendMessage(msg);
                                    }
                                }
                                inputSource.close();
                                reader.close();
                                reader1.close();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //验证码图片
    public Bitmap createBitmap() {
        padding_left = 0;

        Bitmap bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bp);

        code = createCode();

        c.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(font_size);
        //画验证码
        for (int i = 0; i < code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
        }

        c.save(Canvas.ALL_SAVE_FLAG);//保存
        c.restore();//
        return bp;
    }


    public String getCode() {
        return code;
    }

    //生成验证码
    private String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    //生成随机颜色
    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    //随机生成文字样式，颜色，粗细，倾斜度
    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
    }

    //随机生成padding值
    private void randomPadding() {
        padding_left += base_padding_left + random.nextInt(range_padding_left);
        padding_top = base_padding_top + random.nextInt(range_padding_top);
    }

    //检查邮箱
    private boolean checkEmail() {
        String emailString = edit_email.getText().toString().trim();
        if (TextUtils.isEmpty(emailString)) {
            hint_emamil.setText("邮箱不能为空");
            // shakeViewToNotifyUser(edit_email);
            return false;
        }
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            hint_emamil.setText("");
            return true;
        } else {
            hint_emamil.setText("邮箱格式不正确");
            return false;
        }
    }

    private boolean checkPasswork() {
        String editpasswordsString = edit_passwrok.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            hint_passwork.setText("密码不能为空");
            // shakeViewToNotifyUser(edit_password);
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            hint_passwork.setText("密码仅限6-16个字符");
            return false;
        } else {
            hint_passwork.setText("");
            return true;
        }
    }

    private boolean checkAgainPasswork() {

        String editagainpasswordsString = edit_again_password.getText().toString();
        if (TextUtils.isEmpty(editagainpasswordsString)) {
            hint_again_passord.setText("确认密码不能为空");
            // shakeViewToNotifyUser(edit_again_password);
            return false;
        } else {
            hint_again_passord.setText("");
            String passwordsString = edit_passwrok.getText().toString();
            if (!passwordsString.equals(editagainpasswordsString)) {
                hint_again_passord.setText("密码不一致");
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 检查验证码
     *
     * @return
     */
    private boolean checkCaptcha() {

        if (realCode == null) {
            Toast.makeText(getActivity(), "未获取验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        mCaptchaEditTextString = edit_yanzhengma.getText().toString().trim();
        if (realCode.contains(" ")) {
            hint_yanzhengma.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            // shakeViewToNotifyUser(edit_yanzhengma);
            hint_yanzhengma.setText("验证码不能为空");
            return false;
        } else {
            hint_yanzhengma.setText("");
            return true;
        }

    }

    /**
     * 检查协议
     *
     * @return
     */
    private boolean checkXieyi() {

        if (!xieyi_check.isChecked()) {
            hint_xieyi.setText("未勾选《央视网网络服务使用协议》");
            return false;
        } else {
            hint_xieyi.setText("");
            return true;
        }

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMG_GET_SUCCES:
                    dismissLoadDialog();
                    sendCaptchaHttpMessage();
                    break;
                case MSG_LOGIN_IN_ERROR:
                    dismissLoadDialog();
                    mCaptchaImageView.setText("图形验证码");
                    sendCaptchaHttpMessage();
                    mCaptchaImageView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    if (msg.obj != null) {
                        if (msg.obj instanceof String) {

                            Toast.makeText(getActivity(), (String) msg.obj, Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                case MSG_LOGIN_IN_SUCCES:
                    dismissLoadDialog();

                    toastRegisterSuccess();
                    MobileAppTracker.trackEvent("注册", null, "个人中心", 0, null, null, getActivity());
                    break;

            }
        }
    };

    public static Drawable byteToDrawable(byte[] byteArray) {
        try {
            String string = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }

    private void toastRegisterSuccess() {
        if (getActivity().isFinishing()) {
            return;
        }

        final String key = edit_email.getText().toString().trim().split("@")[1].toLowerCase();
        if (mEmailAddress.getmEmailAddress().containsKey(key)) {
            View tview = View
                    .inflate(getActivity(), R.layout.dialog_internet_tishi, null);
            TextView tishiContent = (TextView) tview
                    .findViewById(R.id.play_continue_content);
            tishiContent.setText("请到您的邮箱激活账号后登录，是否现在去邮箱激活");
            TextView tishiCancel = (TextView) tview
                    .findViewById(R.id.play_continue_cancel);
            TextView tishiSure = (TextView) tview
                    .findViewById(R.id.play_continue_sure);
            final Dialog registerDialog = new Dialog(getActivity(), R.style.dialog);
            tishiSure.setText("确定");
            tishiCancel.setText("取消");

            registerDialog.setContentView(tview);
            registerDialog.setCanceledOnTouchOutside(true);

            tishiCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerDialog.dismiss();

                }
            });
            tishiSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerDialog.dismiss();
                    String mEmailAdd = mEmailAddress.getmEmailAddress().get(key);
                    Uri uri = Uri.parse(mEmailAdd);
                    Intent it = new Intent();
                    it.setAction("android.intent.action.VIEW");
                    it.setData(uri);
                    startActivity(it);
                    getActivity().finish();
                }
            });
            registerDialog.show();

        } else {
            Toast.makeText(getActivity(), "通行证需激活后才可登录", Toast.LENGTH_SHORT).show();
        }
    }

    private static mEmailAddressMap mEmailAddress;

    public static class mEmailAddressMap implements Serializable {
        private Map<String, String> mEmailAddress;

        public Map<String, String> getmEmailAddress() {
            return mEmailAddress;
        }

        public void setmEmailAddress(Map<String, String> mEmailAddress) {
            this.mEmailAddress = mEmailAddress;
        }

    }

    static {
        mEmailAddress = new mEmailAddressMap();
        mEmailAddress.setmEmailAddress(new HashMap<String, String>());
        mEmailAddress.getmEmailAddress().put("qq.com", "http://mail.qq.com");
        mEmailAddress.getmEmailAddress().put("gmail.com", "http://mail.google.com");
        mEmailAddress.getmEmailAddress().put("sina.com", "http://mail.sina.com.cn");
        mEmailAddress.getmEmailAddress().put("163.com", "http://mail.163.com");
        mEmailAddress.getmEmailAddress().put("126.com", "http://mail.126.com");
        mEmailAddress.getmEmailAddress().put("yeah.net", "http://www.yeah.net/");
        mEmailAddress.getmEmailAddress().put("tom.com", "http://mail.tom.com/");
        mEmailAddress.getmEmailAddress().put("sohu.com", "http://mail.sohu.com/");
        mEmailAddress.getmEmailAddress().put("sogou.com", "http://mail.sogou.com/");
        mEmailAddress.getmEmailAddress().put("139.com", "http://mail.10086.cn/");
        mEmailAddress.getmEmailAddress().put("hotmail.com", "http://www.hotmail.com");
        mEmailAddress.getmEmailAddress().put("live.com", "http://login.live.com/");
        mEmailAddress.getmEmailAddress().put("live.cn", "http://login.live.cn/");
        mEmailAddress.getmEmailAddress().put("live.com.cn", "http://login.live.com.cn");
        mEmailAddress.getmEmailAddress().put("189.com", "http://webmail16.189.cn/webmail/");
        mEmailAddress.getmEmailAddress().put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
        mEmailAddress.getmEmailAddress().put("yahoo.cn", "http://mail.cn.yahoo.com/");
        mEmailAddress.getmEmailAddress().put("eyou.com", "http://www.eyou.com/");
        mEmailAddress.getmEmailAddress().put("21cn.com", "http://mail.21cn.com/");
        mEmailAddress.getmEmailAddress().put("188.com", "http://www.188.com/");
        mEmailAddress.getmEmailAddress().put("foxmail.coom", "http://www.foxmail.com");
    }
}
