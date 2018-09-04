package com.hao.m.bridge.retrofit.http.interceptor

import com.hao.m.bridge.database.CookieDbUtil
import com.hao.m.entity.data.CookieResult
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

/**
 * Created by kangf on 2018/6/16.
 *
 * 网络请求-数据持久化
 */
class CookieInterceptor(private val cache: Boolean,
                        private val url: String) : Interceptor {


    private var dbUtil: CookieDbUtil? = null

    init {
        dbUtil = CookieDbUtil.instance
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (cache) {
            val body = response.body()
            val source = body!!.source()
            source?.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            var charset = Charset.defaultCharset()
            val contentType = body.contentType()
            charset = contentType?.charset(charset)
            val bodyString = buffer.clone().readString(charset)
            var result = dbUtil!!.queryCookieBy(url)
            val time = System.currentTimeMillis()
            if (result == null) {
                result = CookieResult(url, bodyString, time)
                dbUtil!!.saveCookie(result)
            } else {
                result.result = bodyString
                result.time = time
                dbUtil!!.updateCookie(result)
            }
        }
        return response!!
    }
}