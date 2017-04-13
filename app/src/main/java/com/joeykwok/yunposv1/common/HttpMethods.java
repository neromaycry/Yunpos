package com.joeykwok.yunposv1.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.joeykwok.yunposv1.responsebean.rpSkuBean;
import com.joeykwok.yunposv1.responsebean.rpUserBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by gjwlg on 2017/3/24.
 */

public class HttpMethods {


    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private Api api;
    private Gson gson;

    public HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(GlobalConsts.BASE_URL)
                .build();

        gson = new Gson();

        api = retrofit.create(Api.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void login(Subject<rpUserBean> subject, HashMap<String,String> params) {
        String strEntity = getJsonString(params, "");
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);

        api.login(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subject);
    }

    public void sku(Subject<rpSkuBean> subject, HashMap<String, String> params, String token) {
        String strEntity = getJsonString(params, token);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);

        api.sku(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subject);
    }

    public String getJsonString(HashMap<String,String> params, String token) {
        //追加公共参数
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String timestamp = formater.format(date);
        Log.d("Joey", "timestamp:" + timestamp);

        params.put("timestamp", timestamp);
        params.put("poskey", "001");
        params.put("token", token);
        params.put("sign", "");

        return gson.toJson(params);
    }

}



















