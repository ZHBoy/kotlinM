package com.hao.m.bridge.retrofit

import com.hao.m.KotlinMApp

/**
 * Created by HaoBoy
 */
class ApiConstants private constructor() {

    companion object {
        /*控制是否输入日志*/
        const val IS_DEBUG = KotlinMApp.isDebug

//        const val BASE_URL = "http://192.168.43.241:8182/"
        const val BASE_URL = "http://192.168.66.5:8182/"

        //添加一个用户
        const val USER_LOGIN = "/query/one"

        const val USER_LIST = "/query/all"
    }

}