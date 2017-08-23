package com.example.panda.utils;

import com.example.panda.model.entity.Bean;

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
 * Created by lenovo on 2017/8/23.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private RetrofitService service;

    public RetrofitUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        service = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://www.ipanda.com/kehuduan/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService.class);
    }

    public static RetrofitUtils getRetrofitUtils() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }

    public void getPost(Map<String, String> map, Observer observer) {
        Observable<Bean> observable = service.getData(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getGet(Observer observer) {
        Observable<Bean> observable = service.getDataGet();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}