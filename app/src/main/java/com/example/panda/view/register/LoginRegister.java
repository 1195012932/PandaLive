package com.example.panda.view.register;

import com.example.panda.view.register.utils.UserApi;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XXASUS on 2017/9/6.
 * <p>
 * 注册
 */

public class LoginRegister {

    private static LoginRegister loginRegister;

    private static final int MAXTIME = 50;

    private UserApi userApi;

    public LoginRegister(final String JSESSIONID) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(MAXTIME, TimeUnit.SECONDS)
                .readTimeout(MAXTIME, TimeUnit.SECONDS)
                .writeTimeout(MAXTIME, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "utf-8"))
                                .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .addHeader("Cookie","JSESSIONID=" +JSESSIONID)
                                .build();

                        return chain.proceed(request);
                    }
                }).build();

        userApi = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://reg.cntv.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi.class);
    }


    public static LoginRegister getLoginRegister(String JSESSIONID) {
        if (loginRegister == null) {
            loginRegister = new LoginRegister(JSESSIONID);
        }
        return loginRegister;
    }


    public void getPassutils(Map<String, String> map, Observer observer) {
        Observable<RequestBody> user = userApi.getUser(map, observer);
        user.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
