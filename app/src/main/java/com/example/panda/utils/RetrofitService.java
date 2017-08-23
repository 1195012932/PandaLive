package com.example.panda.utils;

import com.example.panda.model.entity.Bean;

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
    Observable<Bean> getData(@FieldMap Map<String,String> map);

    @GET("PAGE1450172284887217/index.json")
    Observable<Bean> getDataGet();
}
