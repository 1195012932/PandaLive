package com.example.panda.view.fragment.person;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.view.activity.PersonalAgreePostActivity;
import com.mob.MobSDK;

import org.json.JSONObject;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneRegi extends Fragment implements View.OnClickListener {
    //随机数数组
    private static final char[] CHARS = {
            '1','2','3','4','5','6','7','8','9','0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm','i','l',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z','o',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','O'
    };
    private static PhoneRegi bmpCode;
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
    private EditText edit_phone;
    private TextView hint_phone;
    private EditText edit_imgyanzhengma;
    private ImageView personal_reg_imgcheck;
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
    private EventHandler eventHandler;
    private int padding_left, padding_top;
    private Random random = new Random();
    private String code;
    private String realCode;
    private String mCaptchaEditTextString;
    private View view;
    private byte[] mCaptchaBytes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phone_regi, null);
        initView(view);
        return view;
    }

    public static PhoneRegi getInstance() {
        if (bmpCode == null)
            bmpCode = new PhoneRegi();
        return bmpCode;
    }

    private void initView(View view) {
        edit_phone = (EditText) view.findViewById(R.id.edit_phone);
        hint_phone = (TextView) view.findViewById(R.id.hint_phone);
        edit_imgyanzhengma = (EditText) view.findViewById(R.id.edit_imgyanzhengma);
        personal_reg_imgcheck = (ImageView) view.findViewById(R.id.personal_reg_imgcheck);
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
        personal_reg_imgcheck.setImageBitmap(PhoneRegi.getInstance().createBitmap());
        realCode = PhoneRegi.getInstance().getCode().toLowerCase();
        Log.v("TAG", "realCode" + realCode);
        personal_reg_phonecheck.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        personal_reg_imgcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personal_reg_imgcheck.setImageBitmap(PhoneRegi.getInstance().createBitmap());
                realCode = PhoneRegi.getInstance().getCode().toLowerCase();
                Log.v("TAG", "realCode" + realCode);
            }
        });
        //短信验证
        MobSDK.init(getActivity(), "207cfdb2b85f8", "d7c4ba98f09235d1832b09508ab45fa3"); //使用你申请的key 和 secret
        eventHandler = new EventHandler() {

            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };
        personal_reg_xieyi_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent xieIntent = new Intent(getActivity(), PersonalAgreePostActivity.class);
                startActivity(xieIntent);
            }
        });
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        String phone = edit_phone.getText().toString().trim();
        String imgyanzhengma = edit_imgyanzhengma.getText().toString().trim();
        String imgyzm = edit_imgyanzhengma.getText().toString().trim();
        String editpass = edit_inputpasswrod.getText().toString().trim();
        if (v.getId() == R.id.btn_register) {
            String strCode = edit_phoneyanzhengma.getText().toString();
            if (strCode != null && strCode.length() == 4 && imgyzm.equals(realCode) && editpass != null) {
                Log.d(TAG, strCode);
                SMSSDK.submitVerificationCode("86", phone, edit_phoneyanzhengma.getText().toString());
            } else {
                Toast.makeText(getActivity(), "输入有误", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.personal_reg_phonecheck) {
            if (TextUtils.isEmpty(phone) & TextUtils.isEmpty(imgyanzhengma)) {
                hint_phone.setText("手机号码不能为空");
                hint_imagecheck.setText("图片验证码输入有误");

                return;
            } else if (phone.length() != 11 & TextUtils.isEmpty(imgyanzhengma)) {
                hint_phone.setText("手机格式不正确");
                hint_imagecheck.setText("图片验证码输入有误");
                return;
            } else if (phone.length() == 11 & imgyzm.equals(realCode)) {
                hint_phone.setVisibility(View.GONE);
                hint_imagecheck.setVisibility(View.GONE);
            } else if (phone.length() == 11 && imgyzm.equals(realCode)) {
                SMSSDK.getVerificationCode("86", phone);
                personal_reg_phonecheck.setClickable(false);
                //开启线程去更新button的text
                new Thread() {
                    @Override
                    public void run() {
                        int totalTime = 60;
                        for (int i = 0; i < totalTime; i++) {
                            Message message = myHandler.obtainMessage(0x01);
                            message.arg1 = totalTime - i;
                            myHandler.sendMessage(message);
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myHandler.sendEmptyMessage(0x02);
                    }
                }.start();
            }
        }
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(getActivity(), "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                            Toast.makeText(getActivity(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(PhoneRegi.this, SuccessActivity.class);
                            //startActivity(intent);
                        } else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(getActivity(), des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    personal_reg_phonecheck.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    personal_reg_phonecheck.setText("获取验证码");
                    personal_reg_phonecheck.setClickable(true);
                    break;
            }
        }
    };

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
}
