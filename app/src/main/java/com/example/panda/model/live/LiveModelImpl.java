package com.example.panda.model.live;

import com.example.panda.model.live.bean.Brod;
import com.example.panda.model.live.bean.LiveStreaing;
import com.example.panda.model.live.bean.NotBean;
import com.example.panda.model.live.bean.OriBean;
import com.example.panda.model.live.bean.ProBean;
import com.example.panda.model.live.bean.RecBean;
import com.example.panda.model.live.bean.RollBean;
import com.example.panda.model.live.bean.ThoBean;
import com.example.panda.model.live.bean.TopBean;
import com.example.panda.model.live.bean.WonBean;
import com.example.panda.utils.LiveUtils;
import com.example.panda.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/8/23.
 */

public class LiveModelImpl implements LiveModel{
    @Override
    public void liveget(Observer<LiveStreaing> observer) {
        RetrofitUtils.getRetrofitUtils().getLives(observer);
    }

    @Override
    public void brodget(Observer<Brod> observer) {
        RetrofitUtils.getRetrofitUtils().getBroads(observer);
    }

    @Override
    public void livestreaming(Map<String, String> map, Observer<LiveStreaing> observer) {
        RetrofitUtils.getRetrofitUtils().getLive(map,observer);
    }

    @Override
    public void Notget(Observer<NotBean> observer) {
        LiveUtils.getRetrofitUtils().getNots(observer);
    }

    @Override
    public void Notsget(Map<String, String> map, Observer<NotBean> observer) {
        LiveUtils.getRetrofitUtils().getNot(map,observer);
    }

    @Override
    public void Origet(Observer<OriBean> observer) {
        LiveUtils.getRetrofitUtils().getOris(observer);
    }

    @Override
    public void Orisget(Map<String, String> map, Observer<OriBean> observer) {
        LiveUtils.getRetrofitUtils().getOri(map,observer);
    }

    @Override
    public void Proget(Observer<ProBean> observer) {
        LiveUtils.getRetrofitUtils().getPros(observer);
    }

    @Override
    public void Prosget(Map<String, String> map, Observer<ProBean> observer) {
        LiveUtils.getRetrofitUtils().getPro(map,observer);
    }

    @Override
    public void Recget(Observer<RecBean> observer) {
        LiveUtils.getRetrofitUtils().getRecs(observer);
    }

    @Override
    public void Recsget(Map<String, String> map, Observer<RecBean> observer) {
        LiveUtils.getRetrofitUtils().getRec(map,observer);
    }
///////////////////////////////////////////////////////////////
    @Override
    public void Topget(Observer<TopBean> observer) {
        LiveUtils.getRetrofitUtils().getTops(observer);
    }

    @Override
    public void Thoget(Observer<ThoBean> observer) {
        LiveUtils.getRetrofitUtils().getThos(observer);
    }

    @Override
    public void Wonget(Observer<WonBean> observer) {
        LiveUtils.getRetrofitUtils().getWons(observer);
    }

    @Override
    public void Rollget(Observer<RollBean> observer) {
        LiveUtils.getRetrofitUtils().getRolls(observer);
    }


}
