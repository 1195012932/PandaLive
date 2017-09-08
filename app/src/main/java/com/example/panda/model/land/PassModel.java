package com.example.panda.model.land;

import android.util.Log;

import com.example.panda.presenter.land.UserPersenter;
import com.example.panda.view.land.PassUtils;
import com.example.panda.view.land.entity.LoginBean;
import com.example.panda.view.land.entity.UserBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class PassModel implements PassModelImpl {
    public final static String LOGIN_URL = "https://reg.cntv.cn/login/login.action";
    public static final String USER_AGENT = "CNTV_APP_CLIENT_CYNTV_MOBILE";
    public static final String CLIENT = "ipanda_mobile";


    @Override
    public void landpass(String name, String password, final UserPersenter userPersenter) {

        Map<String, String> map = new HashMap<>();
        map.put("Referer", LOGIN_URL);
        map.put("User-Agent", USER_AGENT);
        map.put("from", LOGIN_URL);
        map.put("service", "client_transaction");
        map.put("username", name);
        map.put("password", password);

        PassUtils.getPassUtils().getPassutils(map, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                LoginBean loginBean = (LoginBean) value;
                EventBus.getDefault().post(loginBean);

                if ("0".equals(loginBean.getErrType())) {
                    String usrid = loginBean.getUsrid();
                    String user_seq_id = loginBean.getUser_seq_id();
                    Log.e("TAG", "这是登陆请求回来的数据 " + user_seq_id);
                    getUserId(user_seq_id, userPersenter);

                } else {
                    userPersenter.getUser(loginBean.getErrMsg());
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


    private void getUserId(String userid, final UserPersenter userPersenter) {

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
                EventBus.getDefault().post(userbean);
                if (code == 0) {
                    String nickname = userbean.getContent().getNickname();
                    String userface = userbean.getContent().getUserface();

                    Log.e("TAG", "这是生成的名字和，ID " + nickname + "++++" + userface);
                    userPersenter.getUser("成功");
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
