package com.hao.m.bridge.retrofit.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by HaoBoy
 */
class PublicHeaderInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response.newBuilder()
                .header("application/json", "Content-Type")
                .build()
    }

}