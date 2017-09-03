package com.example.panda.utils;

import com.example.panda.view.fragment.video.entity.VideoTopBean;

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
 * Created by lenovo on 2017/8/29.
 */

public class TopUtils {
    private static TopUtils topUtils;
    private RetrofitService service;

    public TopUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        service = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://115.182.35.91/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService.class);
    }

    public static TopUtils getRetrofitUtils() {
        if (topUtils == null) {
            topUtils = new TopUtils();
        }
        return topUtils;
    }

    /**
     * 滚滚视频顶部
     * @param observer
     */
    public void getVideoTop(Map<String,String> map, Observer observer) {
        Observable<VideoTopBean> observable = service.getVideoTop(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
