package com.hao.m.bridge.retrofit.http

import android.content.Context
import com.hao.m.KotlinMApp
import com.hao.m.bridge.retrofit.ApiConstants
import com.hao.m.bridge.retrofit.callback.ApiResponse
import com.hao.m.bridge.retrofit.callback.HttpOnNextListener
import com.hao.m.bridge.retrofit.http.interceptor.CookieInterceptor
import com.hao.m.bridge.retrofit.http.interceptor.PublicHeaderInterceptor
import com.hao.m.entity.CommonResult
import com.hao.m.utils.TLog
import com.xfs.fsyuncai.bridge.retrofit.http.RequestOption
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by HaoBoy
 */
class HttpManager private constructor() {

    //单例模式
    companion object {
        val instance: HttpManager by lazy { HttpManager() }
    }

    private var msg: String = ""

    private var option: RequestOption? = null

    fun showProgress(msg: String): HttpManager {
        this.msg = msg
        return this
    }

    fun setOption(option: RequestOption?): HttpManager {
        this.option = option
        return this
    }

    fun <T>doHttpDeal(context: Context, observable: Observable<CommonResult<T>>, listener: HttpOnNextListener<T>) {
        observable
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    if (it.data == null)
                        error("数据格式错误！")
                    it
                }

                .apply {
                    val apiResponse = if (option == null) {
                        ApiResponse(context, msg, RequestOption(), listener)
                    } else {
                        ApiResponse(context, msg, option!!, listener)
                    }
                    option = null
                    this.subscribe(apiResponse)

                }
    }

    //
    fun <T> createService(serviceClass: Class<T>): T? {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(5, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(5, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(PublicHeaderInterceptor())
        //        httpClientBuilder.addInterceptor(PublicParamsInterceptor())//公共参数添加
        httpClientBuilder.addInterceptor(
                CookieInterceptor(if (option == null) false else option!!.isCache,
                        if (option == null) "" else option!!.getUrl()))
        if (KotlinMApp.isDebug)
            httpClientBuilder.addInterceptor(getHttpLoggingInterceptor())

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)

        return retrofitBuilder.client(httpClientBuilder.build()).build().create(serviceClass)

    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor {
            TLog.i(it, 1000)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}