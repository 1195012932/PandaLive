package com.example.panda.utils;


import com.example.panda.model.entity.BroadBean2;
import com.example.panda.model.entity.HomeBean;
import com.example.panda.model.entity.VideoBean;
import com.example.panda.model.entity.home.BroadBean;
import com.example.panda.model.entity.home.InteraBean;
import com.example.panda.model.entity.home.MarvellousBean;
import com.example.panda.model.entity.home.VitmioBean;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.view.fragment.video.entity.VideoTopBean;

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
    Observable<HomeBean> getData(@FieldMap Map<String, String> map);
    @GET("shouye/index.json")
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
    @FormUrlEncoded

    @POST()
    Observable<Brod> getBrod(@FieldMap Map<String, String> map);
    @GET("PAGE14501769230331752/PAGE14501787896813312/index.json")
    Observable<Brod> getBrods();

    /**
     * 滚滚视频
     */
    @FormUrlEncoded
    @POST()
    Observable<VideoBean> getVideo(@FieldMap Map<String, String> map);

    @GET("video/index.json")
    Observable<VideoBean> getVideo();
    @FormUrlEncoded
    @POST()
    Observable<BroadBean> getBroad(@FieldMap Map<String, String> map);
    @GET("PAGE14503485387528442/index.json")
    Observable<BroadBean> getBroad();
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

 //修改了熊猫直播

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
    //原创互动
    //http://www.ipanda.com/kehuduan/PAGE14501767715521482/index.json

    @GET("PAGE14501767715521482/index.json")
    Observable<InteraBean> getintera();

    /**
     * 滚滚视频顶部的图片
     */
    @GET("getVideoInfoForCBox.do?pid=7d826d24b4e443ad88dd59ad03d50dfe")
    Observable<VideoTopBean> getVideoTop();

    /**
     *直播中国
     */
    @GET("PAGE14501775094142282/index.json")
    Observable<ChianBean> getChian();


    @FormUrlEncoded
    @POST()
    Observable<BroadBean2> getBroad2(@FieldMap Map<String, String> map);

    @GET("apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1422435191506336")
    Observable<BroadBean2> getBroad2();

}
