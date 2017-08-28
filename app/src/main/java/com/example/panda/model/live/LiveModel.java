package com.example.panda.model.live;

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

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public interface LiveModel {

    void liveget(Observer<LiveStreaing> observer);
    void livestreaming(Map<String,String> map,Observer<LiveStreaing> observer);
    void Notget(Observer<NotBean> observer);
    void Notsget(Map<String,String> map,Observer<NotBean> observer);
    void Origet(Observer<OriBean> observer);
    void Orisget(Map<String,String> map,Observer<OriBean> observer);
    void Proget(Observer<ProBean> observer);
    void Prosget(Map<String,String> map,Observer<ProBean> observer);
    void Recget(Observer<RecBean> observer);
    void Recsget(Map<String,String> map,Observer<RecBean> observer);
    void Topget(Observer<TopBean> observer);
    void Thoget(Observer<ThoBean> observer);
    void Wonget(Observer<WonBean> observer);
    void Rollget(Observer<RollBean> observer);
}
