package com.example.panda.utils;

import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.home.BroadBean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.entity.home.InteraBean;
import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.model.live.bean.LiveStreaing;
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
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService.class);
    }

    public static RetrofitUtils getRetrofitUtils() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }

    public void getPost(Map<String, String> map, Observer observer) {
        Observable<HomeBean> observable = service.getData(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getGet(Observer observer) {
        Observable<HomeBean> observable = service.getDataGet();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //熊猫直播
    public void getLive(Map<String, String> map, Observer observer) {
        Observable<LiveStreaing> observable = service.getLive(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getLives(Observer observer) {
        Observable<LiveStreaing> observable = service.getLiveStreaing();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //滚滚视频

    public void getVideo(Map<String, String> map, Observer observer) {
        Observable<VideoBean> observable = service.getVideo(map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getVideos(Observer observer) {
        Observable<VideoBean> observable = service.getVideo();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getBroads(Observer observer) {
        Observable<BroadBean> observable = service.getBroad();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * 滚滚视频顶部
     * @param observer
     */
    public void getVideoTop(Observer observer) {
        Observable<VideoTopBean> observable = service.getVideoTop();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * 首页
     */

    public void getHome(Observer observable) {
        Observable<HomeBean> homeObservable = service.getHomeBean();
        homeObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    //精彩一刻
    public void getmarvell(Observer observable) {
        Observable<MarvellousBean> marvellousBeanObservable = service.getMarvellous();
        marvellousBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    //首页滚滚视频
    public void getvitmio(Observer observables) {
        Observable<VitmioBean> marvellousBeanObservable = service.getvio();
        marvellousBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observables);
    }
    //原创互动
    public void getintera(Observer observa) {
        Observable<InteraBean> marvellousBeanObservable = service.getintera();
        marvellousBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observa);
    }
}
