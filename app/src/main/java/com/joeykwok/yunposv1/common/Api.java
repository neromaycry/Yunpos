package com.joeykwok.yunposv1.common;

import com.joeykwok.yunposv1.requestbean.rqSkuBean;
import com.joeykwok.yunposv1.responsebean.rpSkuBean;
import com.joeykwok.yunposv1.responsebean.rpUserBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by gjwlg on 2017/3/24.
 */

public interface Api {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("login")
    Observable<rpUserBean> login(@Body RequestBody body);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("sku")
    Observable<rpSkuBean> sku(@Body RequestBody body);

}






















