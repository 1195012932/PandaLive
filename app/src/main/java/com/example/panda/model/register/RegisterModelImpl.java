package com.example.panda.model.register;

import android.util.Log;

import com.example.panda.presenter.register.RegiserPresenterImpl;
import com.example.panda.view.register.LoginRegister;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class RegisterModelImpl implements RegisterModel {
    /**
     * https://reg.cntv.cn/regist/mobileRegist.do?Referer=
     * http://cbox_mobile.regclientuser.cntv.cn&User-Agent=CNTV_APP_CLIENT_CBOX_MOBILE&method=
     * saveMobileRegisterM&mobile=15600279710&verfiCode=4554&passWd=klk&verfiCodeType=1&addons
     * =http://cbox_mobile.regclientuser.cntv.cn
     *
     * @param regiserPresenter
     * @param phone
     * @param imageyanzhengma
     * @param JSESSION
     */
    @Override
    public void GanCode(final RegiserPresenterImpl regiserPresenter, String phone, String imageyanzhengma, String JSESSION) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "getRequestVerifiCodeM");
        map.put("mobile", phone);
        map.put("verfiCodeType", "1");
        map.put("verificationCode", imageyanzhengma);

        LoginRegister.getLoginRegister(JSESSION)
                .getPassutils(map, new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        ResponseBody responseBody = (ResponseBody) value;

                        try {
                            String string = responseBody.string();
                            Log.e("TAG", "onNext: " + string);

                            regiserPresenter.setRegisterPresen(string);

                        } catch (IOException e) {
                            e.printStackTrace();

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
