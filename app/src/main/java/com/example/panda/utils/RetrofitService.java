package com.example.panda.utils;

import com.example.panda.model.entity.Bean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.model.live.bean.NotBean;
import com.example.panda.model.live.bean.OriBean;
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.model.live.bean.RecBean;
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.model.live.bean.TopBean;
import com.example.panda.model.live.bean.WonBean;

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
    @GET("videolistById?vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<NotBean> getNots();
    //当熊不让
    @FormUrlEncoded
    @POST()
    Observable<OriBean> getOri(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100332640004&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<OriBean> getOris();
    //超萌滚滚秀
    @FormUrlEncoded
    @POST()
    Observable<ProBean> getPro(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100272959126&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<ProBean> getPros();
    //熊猫档案
    @FormUrlEncoded
    @POST()
    Observable<RecBean> getRec(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100340574858&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<RecBean> getRecs();
    //熊猫Top榜
    @FormUrlEncoded
    @POST()
    Observable<TopBean> getTop(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100284428835&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<TopBean> getTops();
    //熊猫那些事儿
    @FormUrlEncoded
    @POST()
    Observable<ThoBean> getTho(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100237714751&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<ThoBean> getThos();
    //特别节目
    @FormUrlEncoded
    @POST()
    Observable<WonBean> getWon(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100167308855&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<WonBean> getWons();
    //原创新闻
    @FormUrlEncoded
    @POST()
    Observable<RollBean> getRoll(@FieldMap Map<String, String> map);
    @GET("videolistById?vsid=VSET100219009515&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<RollBean> getRolls();

}
