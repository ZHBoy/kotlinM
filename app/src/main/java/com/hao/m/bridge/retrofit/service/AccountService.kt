package com.hao.m.bridge.retrofit.service

import com.hao.m.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by HaoBoy
 */
interface AccountService {

    /**
     * 普通用户 -  登录
     */
    @FormUrlEncoded
    @POST(ApiConstants.USER_LOGIN)
    fun login(
            @Field("userAccount") code: String,
            @Field("password") password: String
    ): Observable<String>

}