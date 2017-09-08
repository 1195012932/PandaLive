package com.example.panda.view.land;

import com.example.panda.view.land.entity.LoginBean;
import com.example.panda.view.land.entity.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by XXASUS on 2017/9/6.
 */

public interface PassApi {
    //登陆
    @POST("login/login.action")
    Observable<LoginBean> getPass(@QueryMap Map<String, String> map);

    //http://my.cntv.cn/intf/napi/api.php?client=ipanda_mobile&method=user.getNickNameAndFace&userid=55615510
    //获取头像
    @GET("http://my.cntv.cn/intf/napi/api.php")
    Observable<UserBean> getUserHeard(@QueryMap Map<String, String> map);
    
}
