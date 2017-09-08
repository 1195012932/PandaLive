package com.example.panda.view.land;

import com.example.panda.view.land.entity.LoginBean;
import com.example.panda.view.land.entity.UserBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XXASUS on 2017/9/6.
 */

public class PassUtils {
    private static PassUtils passUtils;
    private final PassApi passApi;


    public static PassUtils getPassUtils() {
        if (passUtils == null) {
            passUtils = new PassUtils();
        }
        return passUtils;
    }


    public PassUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://reg.cntv.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        passApi = retrofit.create(PassApi.class);
    }

    public void getPassutils(Map<String, String> map, Observer observer) {
        Observable<LoginBean> observable = passApi.getPass(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getUserHead(Map<String, String> map, Observer observer) {
        Observable<UserBean> observable = passApi.getUserHeard(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
