package com.hao.m.bridge.retrofit

import com.hao.m.KotlinMApp

/**
 * Created by HaoBoy
 */
class ApiConstants private constructor() {

    companion object {
        /*控制是否输入日志*/
        const val IS_DEBUG = KotlinMApp.isDebug

        const val BASE_URL = "http://192.168.0.34:8888/"

        //登录
        const val USER_LOGIN = "login/login.html"
    }

}