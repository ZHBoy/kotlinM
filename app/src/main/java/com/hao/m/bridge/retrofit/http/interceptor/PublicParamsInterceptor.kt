package com.hao.m.bridge.retrofit.http.interceptor

import android.util.Log
import com.hao.m.KotlinMApp
import com.hao.m.utils.DevicesUtils
import com.hao.m.utils.StringUtils
import com.hao.m.utils.TLog
import okhttp3.*
import okio.Buffer
import java.io.IOException


/**
 * Created by HaoBoy on 2018/9/3.
 */
class PublicParamsInterceptor : Interceptor {

    private var TAG = "LogInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {

        val oldRequest = chain.request()
        var newRequestBuild: Request.Builder? = null
        val method = oldRequest.method()
        var postBodyString = ""

        val map = mutableMapOf<String, Any?>()
        map["token"] =                  ""
        map["timestamp"] =              System.currentTimeMillis()
        map["device_platform"] =        "android"
        map["version_code"] =           102
        map["push_token"] =             ""
        map["channel"] =                DevicesUtils.getAppMetaData(KotlinMApp.context(), "UMENG_CHANNEL")
        map["device_id"] =              DevicesUtils.getIMEI(KotlinMApp.context())
        map["network"] =                DevicesUtils.getAPNType(KotlinMApp.context())
        map["device_brand"] =           DevicesUtils.getMobileBrand()
        map["os_version"] =             DevicesUtils.getOsVersion()

        if ("POST" == method) {
            val oldBody = oldRequest.body()
            when (oldBody) {
                is FormBody -> {
                    val formBodyBuilder = FormBody.Builder()
                    for (obj in map) {
                        formBodyBuilder.add(obj.key, "${obj.value}")
                    }
                    newRequestBuild = oldRequest.newBuilder()

                    val formBody = formBodyBuilder.build()
                    postBodyString = bodyToString(oldRequest.body())
                    postBodyString += (if (postBodyString.isNotEmpty()) "&" else "") + bodyToString(formBody)
                    newRequestBuild!!.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString))
                }
                else -> {
                    val oldMap = StringUtils.parseJSON2Map(bodyToString(oldBody))
                    //不传参数的情况
                    if (oldMap == null || oldMap.isEmpty()) {
                        newRequestBuild = oldRequest.newBuilder()
                    } else {
                        val map3 = HashMap<String, Any?>()
                        map3.putAll(map)
                        map3.putAll(oldMap)
                        postBodyString = StringUtils.getIntGson().toJson(map3)
                        val lb = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), postBodyString)
                        newRequestBuild = oldRequest.newBuilder()
                        newRequestBuild.post(lb)
                    }
                    newRequestBuild = oldRequest.newBuilder()
                    TLog.i("oldBody---postBodyString----$postBodyString")
                }
            }
        } else {
            // 添加新的参数
            val commonParamsUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
            for (obj in map) {
                commonParamsUrlBuilder.addQueryParameter(obj.key, "${obj.value}")
            }

            newRequestBuild = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(commonParamsUrlBuilder.build())
        }
        val newRequest = newRequestBuild!!
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "zh")
                .build()

        val startTime = System.currentTimeMillis()
        val response = chain.proceed(newRequest)
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body()!!.contentType()
        val content = response.body()!!.string()
        val httpStatus = response.code()
        val logSB = StringBuilder()
        logSB.append("-------start:$method|")
        logSB.append(newRequest.toString() + "\n|")
        logSB.append(if (method.equals("POST", ignoreCase = true)) "post参数{$postBodyString}\n|" else "")
        logSB.append("httpCode=$httpStatus;Response:$content\n|")
        logSB.append("----------End:" + duration + "毫秒----------")
        Log.d(TAG, logSB.toString())
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build()
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }


}