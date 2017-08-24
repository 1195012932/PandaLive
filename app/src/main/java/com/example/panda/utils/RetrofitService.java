package com.example.panda.utils;

import com.example.panda.model.entity.Bean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.live.bean.LiveStreaing;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST()
    Observable<Bean> getData(@FieldMap Map<String, String> map);

    @GET("PAGE1450172284887217/index.json")
    Observable<Bean> getDataGet();

    @FormUrlEncoded
    @POST()
    Observable<LiveStreaing> getLive(@FieldMap Map<String, String> map);

    @GET("PAGE14501769230331752/index.json")
    Observable<LiveStreaing> getLiveStreaing();

    @FormUrlEncoded
    @POST()
    Observable<VideoBean> getVideo(@FieldMap Map<String, String> map);

    @GET("video/index.json")
    Observable<VideoBean> getVideo();

}
