package com.example.panda.model.land.yzm;

import android.util.Log;

import com.example.panda.presenter.land.yzm.YZMPersenter;
import com.example.panda.view.land.PassUtils;
import com.example.panda.view.land.entity.UserBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class YzmModelImpl implements YzmModel {
    public final static String LOGIN_URL = "https://reg.cntv.cn/login/login.action";
    public static final String USER_AGENT = "CNTV_APP_CLIENT_CYNTV_MOBILE";
    public static final String CLIENT = "ipanda_mobile";


    @Override
    public void getUserId(String userid, final YZMPersenter userPersenter) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client", CLIENT);
        params.put("method", "user.getNickNameAndFace");
        params.put("userid", userid);
        PassUtils.getPassUtils().getUserHead(params, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                UserBean userbean = (UserBean) value;
                int code = userbean.getCode();

                if (code == 0) {
                    String nickname = userbean.getContent().getNickname();
                    String userface = userbean.getContent().getUserface();
                    EventBus.getDefault().post(userbean);
                    Log.e("TAG", "这是生成的名字和，ID " + nickname + "++++" + userface);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
