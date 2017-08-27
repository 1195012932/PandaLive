package com.example.panda.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/8/26.
 */

public class LiveUtils {
    private static LiveUtils liveutils;
    private RetrofitService service;

    public LiveUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        service = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api.cntv.cn/video/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService.class);
    }

    public static LiveUtils getRetrofitUtils() {
        if (liveutils == null) {
            liveutils = new LiveUtils();
        }
        return liveutils;
    }

    /*public void getNot(Map<String, String> map, Observer observer) {
        Observable<NotBean> observable = service.getNot(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    public void getNots(Observer observer) {
        Observable<NotBean> observable = service.getNots();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }*/

}
