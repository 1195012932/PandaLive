package com.example.panda.view.register.utils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by XXASUS on 2017/9/6.
 */

public interface UserApi {
    @GET("regist/getVerifiCode.action")
    Observable <RequestBody> getUser(@QueryMap Map<String, String> map, Observer observer);
}
