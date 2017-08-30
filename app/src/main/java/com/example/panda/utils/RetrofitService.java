package com.example.panda.utils;

import com.example.panda.model.entity.BroadBean;
import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.home.BroadBean;
import com.example.panda.model.entity.BroadBean2;
import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

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
    Observable<HomeBean> getData(@FieldMap Map<String, String> map);

    @GET("PAGE1450172284887217/index.json")
    Observable<HomeBean> getDataGet();

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

/*    //精彩一刻
    @FormUrlEncoded
    @POST()
    Observable<NotBean> getNot(@FieldMap Map<String, String> map);
    @GET(" videolistById?vsid=VSET100237714751&n=7&serviceId=panda&o=desc&of=time&p=1")
    Observable<NotBean> getNots();*/

    /**
     * 熊猫播报
     */
    @FormUrlEncoded
    @POST()
    Observable<BroadBean> getBroad(@FieldMap Map<String, String> map);

    @GET("PAGE14503485387528442/index.json")
    Observable<BroadBean> getBroad();

    @FormUrlEncoded
    @POST()
    Observable<BroadBean2> getBroad2(@FieldMap Map<String, String> map);

    @GET("apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1422435191506336")
    Observable<BroadBean2> getBroad2();

    /**
     * 首页
     */
    @GET("shouye/index.json")
    Observable<HomeBean> getHomeBean();
    //首页精彩一刻
    @GET("shipinliebieye/jingcaiyike/index.json")
    Observable<MarvellousBean> getMarvellous();
    //首页滚滚视频
    @GET("shipinliebieye/video/index.json")
    Observable<VitmioBean> getvio();

    /**
     * 滚滚视频顶部的图片
     */
    @GET("getVideoInfoForCBox.do?pid=7d826d24b4e443ad88dd59ad03d50dfe")
    Observable<VideoTopBean> getVideoTop();
}
