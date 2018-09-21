package com.hao.m.bridge.retrofit.service

import com.hao.m.bridge.retrofit.ApiConstants
import com.hao.m.entity.CommonResult
import com.hao.m.entity.response.ZhUserEntity
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by HaoBoy
 */
interface AccountService {

    /**
     * 普通用户 -  登录
     */

    @FormUrlEncoded
    @POST(ApiConstants.USER_LOGIN)
    fun login(@Field("userName") userName: String): Observable<CommonResult<ZhUserEntity>>

    /**
     * 获取所有用户
     */
    @POST(ApiConstants.USER_LIST)
    fun getAllUser(): Observable<String>

}