package com.example.panda.utils;

import com.example.panda.model.entity.Bean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.model.live.bean.NotBean;

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

    /**
     * 熊猫直播
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST()
    Observable<LiveStreaing> getLive(@FieldMap Map<String, String> map);

    @GET("PAGE14501769230331752/index.json")
    Observable<LiveStreaing> getLiveStreaing();

    /**
     * 滚滚视频
     */
    @FormUrlEncoded
    @POST()
    Observable<VideoBean> getVideo(@FieldMap Map<String, String> map);
    @GET("video/index.json")
    Observable<VideoBean> getVideo();
//精彩一刻
    @FormUrlEncoded
    @POST()
    Observable<NotBean> getNot(@FieldMap Map<String, String> map);
    @GET(" videolistById?vsid=VSET100237714751&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<NotBean> getNots();

}
