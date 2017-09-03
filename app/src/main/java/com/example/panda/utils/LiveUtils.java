package com.example.panda.utils;


import com.example.panda.model.entity.BroadBean2;
import com.example.panda.model.live.bean.NotBean;
import com.example.panda.model.live.bean.OriBean;
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.model.live.bean.RecBean;
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.model.live.bean.TopBean;
import com.example.panda.model.live.bean.WonBean;
import com.example.panda.view.fragment.video.entity.VideoItemBean;

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

    public void getBroads2(Observer observer) {
        Observable<BroadBean2> observable = service.getBroad2();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getOri(Map<String, String> map, Observer observer) {
        Observable<OriBean> observable = service.getOri(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getOris(Observer observer) {
        Observable<OriBean> observable = service.getOris();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getPro(Map<String, String> map, Observer observer) {
        Observable<ProBean> observable = service.getPro(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getPros(Observer observer) {
        Observable<ProBean> observable = service.getPros();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getRec(Map<String, String> map, Observer observer) {
        Observable<RecBean> observable = service.getRec(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getRecs(Observer observer) {
        Observable<RecBean> observable = service.getRecs();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getTop(Map<String, String> map, Observer observer) {
        Observable<TopBean> observable = service.getTop(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getTops(Observer observer) {
        Observable<TopBean> observable = service.getTops();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getThos(Observer observer) {
        Observable<ThoBean> observable = service.getThos();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getWons(Observer observer) {
        Observable<WonBean> observable = service.getWons();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getRolls(Observer observer) {
        Observable<RollBean> observable = service.getRolls();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getNot(Map<String, String> map, Observer observer) {
        Observable<NotBean> observable = service.getNot(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getNots(Observer observer) {

        Observable<NotBean> observable = service.getNots();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getVideoItem2(Map<String,String> map,Observer observer) {

        Observable<VideoItemBean> observable = service.getItem2(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

}
